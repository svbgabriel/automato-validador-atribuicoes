package br.anhembi.cco.ava;

/**
 *
 * @author sumlauf
 */
public enum Token {
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
    ESPACO (" "),
    LIXO ("");
    
    //IDENTIFICADOR ("[a-z]([a-z]|[0-9]|_)*");
    
    private final String pattern;
    
    Token(String pattern) {
        this.pattern = pattern;
    }
    
    public boolean isValid(String simbolo) {
        return simbolo.matches(pattern);
    }
    
    public static Token what(String simbolo) {
        for(Token t : Token.values()) {
            if(t.isValid(simbolo)) {
                return t;
            }
        }
        return LIXO;
    }
    
    
    
    public static void main(String[] args) {
        //System.out.println(IDENTIFICADOR.isValid("a9_s"));
        //System.out.println(OP_ATRIB.isValid("="));
        System.out.println(what("\n"));
    }
    
    
}
