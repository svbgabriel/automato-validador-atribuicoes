package br.anhembi.cco.ava;

/**
 *
 * @author sumlauf
 */
public enum Estado2 {
    LETRA_MINUSCULA ("[a-z]"),
    DIGITO ("[0-9]"),
    UNDERSCORE ("_"),
    OP_ATRIB ("="),
    PONTO ("\\."),
    OP_ARIT_MAIS ("\\+"),
    OP_ARIT_MENOS ("\\-"),
    OP_ARIT_MULT ("\\*"),
    OP_ARIT_DIV ("\\/"),
    PV (";"),
    
    IDENTIFICADOR ("[a-z]([a-z]|[0-9]|_)*");
    
    private final String pattern;
    
    Estado2(String pattern) {
        this.pattern = pattern;
    }
    
    public boolean isValid(String simbolo) {
        return simbolo.matches(pattern);
    }
    
    
    
    
    public static void main(String[] args) {
        //System.out.println(IDENTIFICADOR.isValid("a9_s"));
        //System.out.println(OP_ATRIB.isValid("="));
        System.out.println(PV.isValid(";"));
    }
    
    
}
