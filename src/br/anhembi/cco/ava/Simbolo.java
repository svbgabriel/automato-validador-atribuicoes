package br.anhembi.cco.ava;

/**
 *
 * @author sumlauf
 */
public enum Simbolo {
    LETRA_MINUSCULA ("[a-z]"),
    DIGITO ("[0-9]"),
    UNDERSCORE ("_"),
    IGUAL ("="),
    PONTO ("\\."),
//    OP_ARIT_MAIS ("\\+"),
//    OP_ARIT_MENOS ("\\-"),
//    OP_ARIT_MULT ("\\*"),
//    OP_ARIT_DIV ("\\/"),
    OPERADOR ("[\\+|\\-|\\*|\\/]"),
    PONTO_VIRGULA (";"),
    ESPACO (" ");
    
    //IDENTIFICADOR ("[a-z]([a-z]|[0-9]|_)*");
    
    private final String pattern;
    
    Simbolo(String pattern) {
        this.pattern = pattern;
    }
    
    public boolean isValid(String simbolo) {
        return simbolo.matches(pattern);
    }
    
    public static Simbolo what(String simbolo) {
        for(Simbolo t : Simbolo.values()) {
            if(t.isValid(simbolo)) {
                return t;
            }
        }
        return null;
    }
    
    
    
    public static void main(String[] args) {
        System.out.println(what("\n"));
    }
    
    
}
