package br.anhembi.cco.ava;

import java.util.Map;
import java.util.Queue;

/**
 * @author Gabriel Batista
 * @author Henrique Albanese
 * @author Sérgio Umlauf
 */
public class MaquinaEstados {
    
    private final TabelaTransicaoEstados tte;
    private final Queue<Lexema> operacoes;
    private String lexema = "";
    private String nomeVariavel;
    
    
    public MaquinaEstados(TabelaTransicaoEstados tte) {
        this.tte = tte;
        this.operacoes = new java.util.LinkedList<>();
    }
    
    
    public Estado transita(Estado ini, Simbolo simbolo, char c) {
        Estado res = new Estado("Lixo");
        String lexemaAnterior = lexema.substring(0, lexema.length() - 1);
        //String lexemaAtual = lexema.substring(lexema.length() - 1);
        
        for(Transicao t : tte.getEstados()) {
            if(t.getEstadoInicial().equals(ini) && t.getSimbolo().equals(simbolo)) {
                res = t.getEstadoFinal();
                
                if(t.getTokenAnterior() != null) {
                    operacoes.add(new Lexema(t.getTokenAnterior(), lexemaAnterior));
                    lexema = "";
                }
                
                if(t.getTokenAtual() != null) {
                    operacoes.add(new Lexema(t.getTokenAtual(), String.valueOf(c)));
                }
                break;
            }
        }
        return res;
    }
    
    
    public boolean processa(String input, Estado estadoInicial) {
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
    
    
    public String parseVariables(Map<String, String> variaveis) {    
        Lexema lex;
        Token token;
        String infixo = "";
        
        while(!operacoes.isEmpty()) {
            lex = operacoes.poll();
            
            //System.out.println(lex);
                       
            if(lex.getToken() == Token.IDENTIFICADOR && operacoes.size() > 0 && operacoes.peek().getToken() == Token.OP_ATRIB) {
                nomeVariavel = lex.getValor();
                operacoes.poll(); // OP_ATRIB
            } else {
                token = lex.getToken();

                if(token == Token.IDENTIFICADOR) {
                    String v = variaveis.get(lex.getValor());
                    if(v != null) {
                        infixo += (v + " ");
                    }
                } else {
                    infixo += (lex.getValor() + " ");
                }
            }
        }
        return infixo;
    }

    public String getNomeVariavel() {
        return nomeVariavel;
    }
    
}
