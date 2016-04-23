package br.anhembi.cco.ava;

import java.util.Queue;

/**
 *
 * @author sumlauf
 */
public class MaquinaEstados {
    
    private final TabelaTransicaoEstados tte;
    private final Queue operacoes;
    
    
    public MaquinaEstados(TabelaTransicaoEstados tte) {
        this.tte = tte;
        this.operacoes = new java.util.LinkedList<>();
    }
    
    
    public Estado transita(Estado ini, Token simbolo) {
        Estado res = null;
        for(Transicao t : tte.getEstados()) {
            if(t.getEstadoInicial().equals(ini) && t.getSimbolo().equals(simbolo)) {
                res = t.getEstadoFinal();
            }
        }
        return res;
    }
    
    
    public void processa(String input, Estado estadoInicial) {
        Queue<String> operacoes = new java.util.LinkedList<>();
        
        String lexema = "";

        Estado estadoAtual = estadoInicial;
        char[] chars = input.toCharArray();
        for(char c : chars) {
            lexema = lexema.concat(String.valueOf(c));
            
            estadoAtual = transita(estadoAtual, Token.what(String.valueOf(c)));
            
            if(estadoAtual != null && estadoAtual.isGuardaLexema()) {
                // Se o último caracter foi um espaço, retira do final do lexema.
                // TODO: verificar se sempre desconsidera o último símbolo.
                if(Token.what(String.valueOf(c)) == Token.ESPACO || Token.what(String.valueOf(c)) == Token.PV) {
                    lexema = lexema.substring(0, lexema.length() - 1);
                }
                // Adiciona o lexema à fila de operações.
                operacoes.add(lexema);
                // Zera o lexema para começar outro.
                lexema = "";
            }
        }
        
        System.out.println("Estado atual: " + estadoAtual);
        
        System.out.println("Operações:");
        for(String o : operacoes) {
            System.out.println(o);
        }
    }
    
}
