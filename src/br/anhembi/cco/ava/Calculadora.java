package br.anhembi.cco.ava;

import java.util.Stack;
import java.util.StringTokenizer;

/**
 *
 * @author Sérgio Umlauf
 */
public class Calculadora {
    
    public double calcula(String sentenca) {
        String posfixo = infixoParaPosfixo(sentenca);
        
        //System.out.println("posfixo = " + posfixo);
        
        double res = processaPosfixo(posfixo);
        return res;
    }
    
    private String infixoParaPosfixo(String infix) {
        StringTokenizer tokenizer = new StringTokenizer(infix);
        String posfixo = "";
        Stack<String> pilhaOperadores = new Stack<>();
        String token;
        String topo;
        
        while (tokenizer.hasMoreTokens()) {
            token = tokenizer.nextToken();
            char c = token.charAt(0);
            if (Character.isDigit(c)) {
                posfixo += (token + " ");
            } else {
                while (!pilhaOperadores.empty()) {
                    topo = pilhaOperadores.peek();
                    
                    if(Operador.peso(topo) >= Operador.peso(String.valueOf(c))) {
                        posfixo += (topo + " ");
                        pilhaOperadores.pop();
                    } else {
                        break;
                    }
                }

                pilhaOperadores.push(String.valueOf(c));
            }
        }

        while (!pilhaOperadores.empty()) {
            topo = pilhaOperadores.pop();
            posfixo += (topo + " ");
        }

        return posfixo;
    }

    private double processaPosfixo(String posfixo) {
        StringTokenizer tokenizer = new StringTokenizer(posfixo);
        Stack<Double> pilhaValores = new Stack();
        double valorDir;
        double valorEsq;
        double resultado;

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            char c = token.charAt(0);
            if (Character.isDigit(c)) {
                pilhaValores.push(new Double(token));
            } else {
                valorDir = pilhaValores.pop();
                valorEsq = pilhaValores.pop();

                switch (c) {
                    case '+':
                        resultado = valorEsq + valorDir;
                        break;
                    case '-':
                        resultado = valorEsq - valorDir;
                        break;
                    case '*':
                        resultado = valorEsq * valorDir;
                        break;
                    case '/':
                        resultado = valorEsq / valorDir;
                        break;
                    default:
                        throw new IllegalArgumentException("Expressão inválida");
                }

                pilhaValores.push(resultado);
            }
        }

        resultado = pilhaValores.pop();
        if (!pilhaValores.empty()) {
            throw new IllegalArgumentException("Expressão inválida");
        }

        return resultado;
    }

}
