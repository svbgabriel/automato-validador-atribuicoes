package br.anhembi.cco.ava;

/**
 *
 * @author sumlauf
 */
public class Main {

    public static void main(String[] args) {

        String input = "bola = 10;";
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
        
        Estado q0 = new Estado("Inicial");
        Estado q1 = new Estado("PrimeiraLetra");
        Estado q2 = new Estado("Igual");
        Estado q3 = new Estado("LetraMinusculaDepoisDeOpAtrib");
        Estado q4 = new Estado("OperadorDepoisDeIdentificador");
        Estado q5 = new Estado("LetraMinusculaDepoisDeOperador");
        Estado q7 = new Estado("PrimeiroDigito");
        Estado q8 = new Estado("Ponto");
        Estado q9 = new Estado("SegundoDigito");
        Estado q6 = new Estado("Final", true); // Estado final
        
        
        TabelaTransicaoEstados tte = new TabelaTransicaoEstados();
        // q0 -->
        tte.add(q0, Simbolo.LETRA_MINUSCULA, q1);
        // q1 -->
        tte.add(q1, Simbolo.LETRA_MINUSCULA, q1);
        tte.add(q1, Simbolo.DIGITO, q1);
        tte.add(q1, Simbolo.UNDERSCORE, q1);
        tte.add(q1, Simbolo.IGUAL, q2, Token.IDENTIFICADOR, Token.OP_ATRIB);
        // q2 -->
        tte.add(q2, Simbolo.LETRA_MINUSCULA, q3);
        tte.add(q2, Simbolo.DIGITO, q7);
        // q3 -->
        tte.add(q3, Simbolo.LETRA_MINUSCULA, q3);
        tte.add(q3, Simbolo.DIGITO, q3);
        tte.add(q3, Simbolo.UNDERSCORE, q3);
        tte.add(q3, Simbolo.OPERADOR, q4, Token.IDENTIFICADOR);
        // q4 -->
        tte.add(q4, Simbolo.LETRA_MINUSCULA, q5);
        tte.add(q4, Simbolo.DIGITO, q7);
        // q5 -->
        tte.add(q5, Simbolo.LETRA_MINUSCULA, q5);
        tte.add(q5, Simbolo.DIGITO, q5);
        tte.add(q5, Simbolo.UNDERSCORE, q5);
        tte.add(q5, Simbolo.PONTO_VIRGULA, q6, Token.IDENTIFICADOR, Token.PV);
        tte.add(q5, Simbolo.OPERADOR, q4, Token.IDENTIFICADOR);
        // q6 -->
        tte.add(q6, Simbolo.LETRA_MINUSCULA, q1);
        // q7 -->
        tte.add(q7, Simbolo.DIGITO, q7);
        tte.add(q7, Simbolo.OPERADOR, q4, Token.NUMERO);
        tte.add(q7, Simbolo.PONTO, q8);
        tte.add(q7, Simbolo.PONTO_VIRGULA, q6, Token.NUMERO, Token.PV);
        // q8 -->
        tte.add(q8, Simbolo.DIGITO, q9);
        // q9 -->
        tte.add(q9, Simbolo.DIGITO, q9);
        tte.add(q9, Simbolo.OPERADOR, q4, Token.NUMERO);
        tte.add(q9, Simbolo.PONTO_VIRGULA, q6, Token.NUMERO, Token.PV);
        
        
        MaquinaEstados maquina = new MaquinaEstados(tte);
        maquina.processa(input, q0);
        
        maquina.executa();
    }
      
}
