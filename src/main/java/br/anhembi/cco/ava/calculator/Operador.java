package br.anhembi.cco.ava.calculator;

/**
 * Enum de operadores válidos para a calculadora.
 * 
 * O atributo peso determina diretamente proporcional
 * à precedência entre os operadores.
 * 
 * @author Gabriel Batista
 * @author Henrique Albanese
 * @author Sérgio Umlauf
 */
public enum Operador {
    MAIS ("+", 1),
    MENOS ("-", 1),
    MULT ("*", 2),
    DIV ("/", 2);
    
    private final String sinal;
    private final int peso;
    
    Operador(String sinal, int peso) {
        this.sinal = sinal;
        this.peso = peso;
    }
    
    
    /**
     * Retorna o peso (precedência) do operador.
     * 
     * @param sinal O sinal do operador.
     * @return      O peso do operador relacionado ao sinal.
     */
    public static int peso(String sinal) {
        for(Operador t : Operador.values()) {
            if(t.sinal.equals(sinal)) {
                return t.peso;
            }
        }
        return 0;
    }
    

}
