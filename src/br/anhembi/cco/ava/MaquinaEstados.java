package br.anhembi.cco.ava;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 *
 * @author sumlauf
 */
public class MaquinaEstados {
    
    private final TabelaTransicaoEstados tte;
    private final Queue<Lexema> operacoes;
    private String lexema = "";
    
    
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
    
    
    public void processa(String input, Estado estadoInicial) {
        Estado estadoAtual = estadoInicial;
        Simbolo simbolo;
        
        char[] chars = input.toCharArray();
        for(char c : chars) {
            simbolo = Simbolo.what(String.valueOf(c));
            if(simbolo == null) {
                System.out.println("Símbolo " + c + " inválido.");
                return;
            }
            
            if(simbolo == Simbolo.ESPACO) {
                continue;
            }
            
            lexema = lexema.concat(String.valueOf(c));
            
            estadoAtual = transita(estadoAtual, simbolo, c);
            
            //if(estadoAtual != null && estadoAtual.isGuardaLexema()) {
                // Se o último caracter foi um espaço, retira do final do lexema.
                // TODO: verificar se sempre desconsidera o último símbolo.
                //if(Simbolo.what(String.valueOf(c)) == Simbolo.PONTO_VIRGULA) {
                    //lexema = lexema.substring(0, lexema.length() - 1);
                //}
                // Adiciona o lexema à fila de operações.
                //operacoes.add(new Lexema(estadoAtual.getToken(), lexema));
                // Zera o lexema para começar outro.
                //lexema = "";
            //}
        }
        
        System.out.println("Estado atual: " + estadoAtual);
    }
    
    
    public void executa() {    
        float n = 0;
        float res = 0;
        Lexema lex;
        boolean inicio = true;
        Token ultimoToken;
        
        Map<String, Float> variaveis = new HashMap<>();
        
        while(!operacoes.isEmpty()) {
            lex = operacoes.poll();
            System.out.println(lex);
                       
            if(lex.getToken() == Token.IDENTIFICADOR && operacoes.peek().getToken() == Token.OP_ATRIB) {
                // identificador op_atrib

            }
            
            if(lex.getToken() == Token.NUMERO) {
                if(inicio) {
                    res = Float.parseFloat(lex.getValor());
                } else {
                    n = Float.parseFloat(lex.getValor());
                }
            }
            if(lex.getToken() == Token.OP_ARIT) {
                lex = operacoes.poll();
                switch(lex.getValor()) {
                    case "+":
                        res += Float.parseFloat(lex.getValor());
                        break;
                    case "-":
                        res -= Float.parseFloat(lex.getValor());
                        break;
                    case "*":
                        res *= Float.parseFloat(lex.getValor());
                        break;
                    case "/":
                        res /= Float.parseFloat(lex.getValor());
                        break;
                    default:
                        break;
                }
            }
            inicio = false;
            ultimoToken = lex.getToken();
        }
    }
    
}
