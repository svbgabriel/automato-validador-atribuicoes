package br.anhembi.cco.ava;

/**
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
    
    public static int peso(String sinal) {
        for(Operador t : Operador.values()) {
            if(t.sinal.equals(sinal)) {
                return t.peso;
            }
        }
        return 0;
    }
    

}
