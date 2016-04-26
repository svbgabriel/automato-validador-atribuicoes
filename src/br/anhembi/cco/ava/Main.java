package br.anhembi.cco.ava;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author sumlauf
 */
public class Main {

    public static void main(String[] args) {

        String input = "";  //bola = 10 + tatu * 2;";
        Map<String, String> variaveis = new HashMap<>();
        //variaveis.put("tatu", "4");
        
        /*
        atribuicao.txt:
        -----------------------------
        casa = 2
        batata = 4.8

        x = 3 + casa * batata;
        -----------------------------
        */
        
        String linha;

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("C:\\users\\sumlauf\\Temp\\atribuicao.txt"));
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
                if(linha.contains(";")) {
                    input = linha.trim();
                } else {
                    if(!linha.equals("")) {
                        String[] termos = linha.split(" ");
                        variaveis.put(termos[0], termos[2]);
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
        
        
        Estado q0 = new Estado("Inicial");
        Estado q1 = new Estado("PrimeiraLetra");
        Estado q2 = new Estado("Igual");
        Estado q3 = new Estado("LetraMinuscula");
        Estado q4 = new Estado("OperadorDepoisDeIdentificador");
        Estado q7 = new Estado("PrimeiroDigito");
        Estado q8 = new Estado("Ponto");
        Estado q9 = new Estado("SegundoDigito");
        Estado q5 = new Estado("Final", true); // Estado final
        
        
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
        tte.add(q3, Simbolo.OPERADOR, q4, Token.IDENTIFICADOR, Token.OP_ARIT);
        tte.add(q3, Simbolo.PONTO_VIRGULA, q5, Token.IDENTIFICADOR);
        // q4 -->
        tte.add(q4, Simbolo.LETRA_MINUSCULA, q3);
        tte.add(q4, Simbolo.DIGITO, q7);
        // q7 -->
        tte.add(q7, Simbolo.DIGITO, q7);
        tte.add(q7, Simbolo.OPERADOR, q4, Token.NUMERO, Token.OP_ARIT);
        tte.add(q7, Simbolo.PONTO, q8);
        tte.add(q7, Simbolo.PONTO_VIRGULA, q5, Token.NUMERO);
        // q8 -->
        tte.add(q8, Simbolo.DIGITO, q9);
        // q9 -->
        tte.add(q9, Simbolo.DIGITO, q9);
        tte.add(q9, Simbolo.OPERADOR, q4, Token.NUMERO, Token.OP_ARIT);
        tte.add(q9, Simbolo.PONTO_VIRGULA, q5, Token.NUMERO);
        
        
        MaquinaEstados maquina = new MaquinaEstados(tte);
        boolean atribuicaoValida = maquina.processa(input, q0);
        
        if(atribuicaoValida) {
            System.out.println("Atribuição é válida.");
                       
            String infix = maquina.parseVariables(variaveis);
            
            Calculadora calc = new Calculadora();
            double res = calc.calcula(infix);
            
            System.out.println(maquina.getNomeVariavel() + " = " + res);
            
            //System.out.println("infix = " + infix);
        } else {
            System.out.println("Atribuição é inválida!");
        }
    }
      
}
