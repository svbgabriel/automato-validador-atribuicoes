package br.anhembi.cco.ava.automato;

/**
 * Enum de possíveis símbolos processáveis pelo autômato.
 * 
 * Pode ser entendido como o dicionário do autômato.
 * 
 * @author Gabriel Batista
 * @author Henrique Albanese
 * @author Sérgio Umlauf
 */
public enum Simbolo {
    LETRA_MINUSCULA ("[a-z]"),
    DIGITO ("[0-9]"),
    UNDERSCORE ("_"),
    IGUAL ("="),
    PONTO ("\\."),
    OPERADOR ("[\\+|\\-|\\*|\\/]"),
    PONTO_VIRGULA (";"),
    ESPACO (" ");

    
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
}
