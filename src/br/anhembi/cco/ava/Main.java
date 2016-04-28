package br.anhembi.cco.ava;

import br.anhembi.cco.ava.program.Parser;
import br.anhembi.cco.ava.automato.Automato;
import br.anhembi.cco.ava.calculator.Calculadora;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * @author Gabriel Batista
 * @author Henrique Albanese
 * @author Sérgio Umlauf
 */
public class Main {

    public static void main(String[] args) {      
        BufferedReader br = null;
        try {
            //br = new BufferedReader(new InputStreamReader(System.in));
            br = new BufferedReader(new FileReader("C:\\Users\\sumlauf\\Temp\\atribuicao.txt"));
            
            Parser parser = new Parser();
            
            // Lê o conteúdo do arquivo.
            parser.readSource(br, true);
            
            // Pega a atribuição que o parser encontrou.
            String atribuicao = parser.getAtribuicao();
            
            // Verifica se a atribuição e válida.
            Automato automato = new Automato();
            boolean atribuicaoValida = automato.processa(atribuicao);

            if(atribuicaoValida) {
                System.out.println("Atribuição é válida.");

                // Parseia a atribuição, substituindo as variáveis por seus valores.
                String infixo = parser.parseVariables(automato.getLexemas());

                //  Calcula a atribuição. A calculadora recebe uma expressão infixa.
                Calculadora calc = new Calculadora();
                double res = calc.calcula(infixo);
                System.out.println(parser.getVariavelAtribuicao() + " = " + res);
            } else {
                System.out.println("Atribuição é inválida!");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Erro: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }
      
}
