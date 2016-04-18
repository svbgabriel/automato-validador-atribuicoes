package br.anhembi.cco.ava;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sumlauf
 */
public class Main {

//    static State estadoAtual = State.Inicial;
    
    
    static List<String> pilha = new ArrayList<>();
      
//    static Map<State, Map<String, State>> estados = new HashMap<>();
    
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String input = "int";
        /*
        
        estado      símbolo     estado
        ------------------------------
        inicial     i           I
        inicial     ^i          LIXO
        I           n           IN
        I           ^n          LIXO
        IN          t           INT
        IN          ^t          LIXO
        INT         ' '         SAIDA
        INT         ^' '        LIXO
        
        */
        
        Estado estadoInicial = new Estado("Inicial");
        Estado estadoI = new Estado("I");
        Estado estadoIN = new Estado("IN");
        Estado estadoINT = new Estado("INT");
        Estado estadoSAIDA = new Estado("SAIDA");
        
        
        TabelaTransicaoEstados tte = new TabelaTransicaoEstados();
        
//        tte.add(State.Inicial, "i", State.I);
//        tte.add(State.I, "n", State.IN);
//        tte.add(State.IN, "t", State.INT);

        tte.add(estadoInicial, "i", estadoI);
        tte.add(estadoI, "n", estadoIN);
        tte.add(estadoIN, "t", estadoINT); 
        tte.add(estadoINT, " ", estadoSAIDA);

        Estado estadoAtual = estadoInicial;
        char[] chars = input.toCharArray();
        for(char c : chars) {
            estadoAtual = tte.transicao(estadoAtual, String.valueOf(c));
        }
        
        System.out.println("Estado atual: " + estadoAtual);
        
    }
    
//    private static void transicao(State ini, String simbolo) {
//        estadoAtual = estados.get(ini).get(simbolo);
//    }
    
//    private static void transicao(State ini, char simbolo) {
//        switch(ini) {
//            case Inicial:
//                if(simbolo == 'i') {
//                    estadoAtual = State.I;
//                } else {
//                    estadoAtual = State.LIXO;
//                }
//                break;
//                
//            case I:
//                if(simbolo == 'n') {
//                    estadoAtual = State.IN;
//                } else {
//                    estadoAtual = State.LIXO;
//                }
//                break; 
//                
//            case IN:
//                if(simbolo == 't') {
//                    estadoAtual = State.INT;
//                } else {
//                    estadoAtual = State.LIXO;
//                }
//                break;
//                
//            case INT:
//                if(simbolo == ' ') {
//                    estadoAtual = State.Inicial;
//                    pilha.add("int");
//                } else {
//                    estadoAtual = State.LIXO;
//                }
//                break;
//        }
//    }
    
}
