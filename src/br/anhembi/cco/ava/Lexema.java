package br.anhembi.cco.ava;

/**
 *
 * @author Sérgio Umlauf
 */
public class Lexema {
    private Token token;
    private String valor;
    
    
    public Lexema() {
    }
    
    public Lexema(Token token, String valor) {
        this.token = token;
        this.valor = valor;
    }
    

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
    
    
    
    @Override
    public String toString() {
        return token.toString() + " : " + valor;
    }
    
}
