package br.anhembi.cco.ava.program;

import br.anhembi.cco.ava.automato.Lexema;
import br.anhembi.cco.ava.automato.Token;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * Faz a verificação de uma expressão de atribuição e parseia suas variáveis.
 * 
 * Utiliza um autômato finito determinístico para a verificação.
 * 
 * @author Gabriel Batista
 * @author Henrique Albanese
 * @author Sérgio Umlauf
 */
public class Parser {

    private String atribuicao;
    private Map<String, String> variaveis;
    private String variavelAtribuicao;
    
    
    public Parser() {
        this.variaveis = new HashMap<>();
    }
    
    
    /**
     * Lê o conteúdo de um arquivo com um programa.
     * 
     * @param br            Reader
     * @param printLines    Imprime ou não as linhas do arquivo
     * @throws IOException  Exceção de arquivo não encontrado
     */
    public void readSource(BufferedReader br, boolean printLines) throws IOException {
        String linha;
        int i = 1;
        
        if(printLines) {
            System.out.println("BOF");
        }
        while ((linha = br.readLine()) != null) {
            if(printLines) {
                System.out.println(i + ":  " + linha);
            }
            if(linha.contains(";")) {
                atribuicao = linha.trim();
            } else {
                if(!linha.equals("")) {
                    String[] termos = linha.split(" ");
                    variaveis.put(termos[0], termos[2]);
                }
            }
            i++;
        }
        if(printLines) {
            System.out.println("EOF\n");
        }
    }
    
    
    /**
     * Lê o conteúdo de um arquivo com um programa.
     * 
     * @param br            Reader
     * @throws IOException 
     */
    public void readSource(BufferedReader br) throws IOException {
        readSource(br, false);
    }
    
    
    /**
     * Parseia as variáveis de uma expressão.
     * 
     * Parseia as variáveis de uma expressão, substituindo por seus valores e
     * retorna a expressão infixa da atribuição.
     * 
     * Exemplo: Para o programa abaixo, este método retorna "3 + 2 * 4.8".
     * 
     *   -----------------------------
     *   casa = 2
     *   batata = 4.8
     *   
     *   x = 3 + casa * batata;
     *   -----------------------------
     * 
     * 
     * @param variaveis Um mapa de variáveis e seus valores.
     * @return A expressão infixa.
     */
    public String parseVariables(Queue<Lexema> lexemas) {    
        Lexema lex;
        Token token;
        String expressaoInfixa = "";
        
        while(!lexemas.isEmpty()) {
            lex = lexemas.poll();
            if(lex.getToken() == Token.IDENTIFICADOR && lexemas.size() > 0 && lexemas.peek().getToken() == Token.OP_ATRIB) {
                variavelAtribuicao = lex.getValor();
                lexemas.poll(); // Retira o OP_ATRIB da fila.
            } else {
                token = lex.getToken();
                if(token == Token.IDENTIFICADOR) {
                    if(variaveis.isEmpty()) {
                        throw new IllegalArgumentException("Variável " + lex.getValor() + " não declarada.");
                    }
                    String v = variaveis.get(lex.getValor());
                    if(v != null) {
                        expressaoInfixa += (v + " ");
                    } else {
                        throw new IllegalArgumentException("Variável " + lex.getValor() + " não declarada.");
                    }
                } else {
                    expressaoInfixa += (lex.getValor() + " ");
                }
            }
        }
        return expressaoInfixa;
    }
    
    
    /**
     * Retorna o nome da variável de atribuição.
     * 
     * Exemplo: na expressão "x = 3 + 4;", este método retorna "x".
     * 
     * @return O nome da variável de atribuição.
     */
    public String getVariavelAtribuicao() {
        return variavelAtribuicao;
    }

    
    /**
     * Retorna a atribuição identificada pelo parser.
     * 
     * @return A atribuição identificada pelo parser.
     */
    public String getAtribuicao() {
        return atribuicao;
    }

    
    /**
     * Retorna o mapa de variáveis identificadas pelo parser.
     * 
     * @return Um mapa de String, String.
     */
    public Map<String, String> getVariaveis() {
        return variaveis;
    }
}
