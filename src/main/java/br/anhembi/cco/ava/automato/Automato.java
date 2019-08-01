package br.anhembi.cco.ava.automato;

import java.util.Queue;

/**
 * Autômato Determinístico Finito
 * 
 * Verifica uma atribuição do tipo:
 * 
 * identificador op_atrib (identificador | numero)(op_arit (identificador | numero) )* pv 
 * 
 * Sendo que:
 * 
 *   - identificador: inicia por uma letra minúscula, seguido por
 *   uma quantidade qualquer de letras minúsculas, dígitos ou
 *   underline.
 *   - op_atib: é representado pelo caractere ‘=’
 *   - numero: inicia por um dígito, seguido por uma quantidade
 *   qualquer de dígitos, podendo ter opcionalmente a parte
 *   fracionária. Caso ele tenha a parte fracionária, ele
 *   recebe o caractere ‘.’ , seguido de uma sequência de um ou
 *   mais dígitos.
 *   - op_arit: é representado por um dos operadores aritméticos
 *   (‘+’ | ‘-‘ | ‘*’ | ‘/’ )
 *   - pv: é representado pelo caractere ‘;’. 
 * 
 * @author Gabriel Batista
 * @author Henrique Albanese
 * @author Sérgio Umlauf
 */
public class Automato {
    
    private final TabelaTransicaoEstados tte;
    private final Queue<Lexema> lexemas;
    private String lexema = "";
    private Estado estadoInicial;
    
    
    
    public Automato() {
        this.tte = new TabelaTransicaoEstados();
        this.lexemas = new java.util.LinkedList<>();
        this.estadoInicial = new Estado("Inicial");
        
        // Estados
        Estado q1 = new Estado("PrimeiraLetra");
        Estado q2 = new Estado("Igual");
        Estado q3 = new Estado("LetraMinuscula");
        Estado q4 = new Estado("OperadorDepoisDeIdentificador");
        Estado q7 = new Estado("PrimeiroDigito");
        Estado q8 = new Estado("Ponto");
        Estado q9 = new Estado("SegundoDigito");
        Estado q5 = new Estado("Final", true); // Estado final
        
        // Tabela de Transição de Estados
        // q0 -->
        this.tte.add(estadoInicial, Simbolo.LETRA_MINUSCULA, q1);
        // q1 -->
        this.tte.add(q1, Simbolo.LETRA_MINUSCULA, q1);
        this.tte.add(q1, Simbolo.DIGITO, q1);
        this.tte.add(q1, Simbolo.UNDERSCORE, q1);
        this.tte.add(q1, Simbolo.IGUAL, q2, Token.IDENTIFICADOR, Token.OP_ATRIB);
        // q2 -->
        this.tte.add(q2, Simbolo.LETRA_MINUSCULA, q3);
        this.tte.add(q2, Simbolo.DIGITO, q7);
        // q3 -->
        this.tte.add(q3, Simbolo.LETRA_MINUSCULA, q3);
        this.tte.add(q3, Simbolo.DIGITO, q3);
        this.tte.add(q3, Simbolo.UNDERSCORE, q3);
        this.tte.add(q3, Simbolo.OPERADOR, q4, Token.IDENTIFICADOR, Token.OP_ARIT);
        this.tte.add(q3, Simbolo.PONTO_VIRGULA, q5, Token.IDENTIFICADOR);
        // q4 -->
        this.tte.add(q4, Simbolo.LETRA_MINUSCULA, q3);
        this.tte.add(q4, Simbolo.DIGITO, q7);
        // q7 -->
        this.tte.add(q7, Simbolo.DIGITO, q7);
        this.tte.add(q7, Simbolo.OPERADOR, q4, Token.NUMERO, Token.OP_ARIT);
        this.tte.add(q7, Simbolo.PONTO, q8);
        this.tte.add(q7, Simbolo.PONTO_VIRGULA, q5, Token.NUMERO);
        // q8 -->
        this.tte.add(q8, Simbolo.DIGITO, q9);
        // q9 -->
        this.tte.add(q9, Simbolo.DIGITO, q9);
        this.tte.add(q9, Simbolo.OPERADOR, q4, Token.NUMERO, Token.OP_ARIT);
        this.tte.add(q9, Simbolo.PONTO_VIRGULA, q5, Token.NUMERO); 
    }
    
    
//    public Automato(TabelaTransicaoEstados tte) {
//        this.tte = tte;
//        this.lexemas = new java.util.LinkedList<>();
//    }
    
   
    /**
     * Processa uma atribuição.
     * 
     * @param input     Atribuição a ser verificada.
     * @return          Validade da atribuição.
     */
    public boolean processa(String input) {
        Estado estadoAtual = estadoInicial;
        Simbolo simbolo;
        char[] chars = input.toCharArray();
        for(char c : chars) {
            simbolo = Simbolo.what(String.valueOf(c));
            if(simbolo == null) {
                //System.out.println("Símbolo " + c + " inválido.");
                throw new IllegalArgumentException("Símbolo " + c + " inválido.");
            }
            if(simbolo == Simbolo.ESPACO) {
                continue;
            }
            lexema = lexema.concat(String.valueOf(c));
            estadoAtual = transita(estadoAtual, simbolo, c);
        }
        //System.out.println("Estado atual: " + estadoAtual);
        return estadoAtual.isFim();
    }
    
    /**
     * Retorna os lexemas identificados pelo autômato.
     * este método deve ser chamado apenas após o método processa.
     * 
     * @return Uma fila de <code>Lexema</code>s.
     */
    public Queue<Lexema> getLexemas() {
        return lexemas;
    }

    
    /**
     * Transita de um <code>Estado</code> para outro, de acordo com o 
     * <code>Simbolo</code> recebido.
     * 
     * Também é responsável por montar o lexema e guardá-lo na fila de 
     * <code>Lexema</code>s.
     * 
     * @param ini       Estado inicial
     * @param simbolo   Simbolo da transição
     * @param c         Caracter atual sendo processado
     * @return          Um novo <code>Estado</code>
     */
    private Estado transita(Estado ini, Simbolo simbolo, char c) {
        Estado res = new Estado("Lixo");
        String lexemaAnterior = lexema.substring(0, lexema.length() - 1);
        //String lexemaAtual = lexema.substring(lexema.length() - 1);
        
        for(Transicao t : tte.getEstados()) {
            if(t.getEstadoInicial().equals(ini) && t.getSimbolo().equals(simbolo)) {
                res = t.getEstadoFinal();
                if(t.getTokenAnterior() != null) {
                    lexemas.add(new Lexema(t.getTokenAnterior(), lexemaAnterior));
                    lexema = "";
                }
                if(t.getTokenAtual() != null) {
                    lexemas.add(new Lexema(t.getTokenAtual(), String.valueOf(c)));
                }
                break;
            }
        }
        return res;
    }
}
