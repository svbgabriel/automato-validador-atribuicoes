package br.anhembi.cco.ava;

import java.util.Queue;

/**
 *
 * @author sumlauf
 */
public class Main {


    /**
     * @param args the command line arguments
     */
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
        
        Estado e_inicial = new Estado("Inicial");
        Estado e_primeira_letra = new Estado("PrimeiraLetra");
        Estado e_espaco_depois_de_identificador = new Estado("EspacoDepoisDeIdentificador", true);
        //Estado e_guarda_lexema = new Estado("GuardaLexema");
        Estado e_chegou_um_opatrib = new Estado("Igual");
        Estado e_espaco_depois_de_opatrib = new Estado("EspacoDepoisDeOpAtribuicao", true);
        Estado e_numero = new Estado("Numero");
        Estado e_pv_depois_de_digito = new Estado("PontoVirgulaDepoisDeDigito", true);
        Estado e_espaco_depois_de_digito = new Estado("EspacoDepoisDeDigito", true);
        
        
        TabelaTransicaoEstados tte = new TabelaTransicaoEstados();
        // Identificador
        tte.add(e_inicial, Token.LETRA_MINUSCULA, e_primeira_letra);
        tte.add(e_primeira_letra, Token.LETRA_MINUSCULA, e_primeira_letra);
        tte.add(e_primeira_letra, Token.DIGITO, e_primeira_letra);
        tte.add(e_primeira_letra, Token.UNDERSCORE, e_primeira_letra);
        tte.add(e_primeira_letra, Token.ESPACO, e_espaco_depois_de_identificador);
        
        // Sinal de igual
        tte.add(e_espaco_depois_de_identificador, Token.OP_ATRIB, e_chegou_um_opatrib);
        tte.add(e_chegou_um_opatrib, Token.ESPACO, e_espaco_depois_de_opatrib);
        
        // valor
        tte.add(e_espaco_depois_de_opatrib, Token.DIGITO, e_numero);
        tte.add(e_numero, Token.DIGITO, e_numero);
        tte.add(e_numero, Token.PV, e_pv_depois_de_digito);
        tte.add(e_numero, Token.ESPACO, e_espaco_depois_de_digito);
        
        
        MaquinaEstados maquina = new MaquinaEstados(tte);
        maquina.processa(input, e_inicial);

        
    }
      
}
