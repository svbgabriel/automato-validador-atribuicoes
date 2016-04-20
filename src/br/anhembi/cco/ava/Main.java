package br.anhembi.cco.ava;

import java.util.ArrayList;
import java.util.List;

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
        
        String input = "bola ";
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
        Estado estadoPL = new Estado("PrimeiraLetra");
        Estado estadoESP = new Estado("Espaco");
        Estado estadoLIXO = new Estado("Lixo");
        Estado estadoSAIDA = new Estado("SAIDA");
        
        
        TabelaTransicaoEstados2 tte = new TabelaTransicaoEstados2();
        
//        tte.add(State.Inicial, "i", State.I);
//        tte.add(State.I, "n", State.IN);
//        tte.add(State.IN, "t", State.INT);

        tte.add(estadoInicial, Token.LETRA_MINUSCULA, estadoPL);
        tte.add(estadoPL, Token.LETRA_MINUSCULA, estadoPL);
        tte.add(estadoPL, Token.DIGITO, estadoPL);
        tte.add(estadoPL, Token.UNDERSCORE, estadoPL);
        tte.add(estadoPL, Token.ESPACO, estadoSAIDA); 
        //tte.add(estadoINT, " ", estadoSAIDA);

        Estado estadoAtual = estadoInicial;
        char[] chars = input.toCharArray();
        for(char c : chars) {
            System.out.println(estadoAtual );
            estadoAtual = tte.transicao(estadoAtual, Token.what(String.valueOf(c)));
            System.out.println(c + " = " + Token.what(String.valueOf(c)) + " -> " + estadoAtual);
        }
        
        System.out.println("Estado atual: " + estadoAtual);
        
    }
      
}
