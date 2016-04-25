package br.anhembi.cco.ava;

/**
 *
 * @author lab735
 */
public class Transicao {
    
    private Estado estadoInicial;
    private Simbolo simbolo;
    private Estado estadoFinal;
    private Token tokenAnterior;
    private Token tokenAtual;
    
    
    public Transicao() {
    }
    
    public Transicao(Estado estadoInicial, Simbolo simbolo, Estado estadoFinal) {
        this.estadoInicial = estadoInicial;
        this.simbolo = simbolo;
        this.estadoFinal = estadoFinal;
    }
    
    public Transicao(Estado estadoInicial, Simbolo simbolo, Estado estadoFinal, Token tokenAnterior) {
        this.estadoInicial = estadoInicial;
        this.simbolo = simbolo;
        this.estadoFinal = estadoFinal;
        this.tokenAnterior = tokenAnterior;
    }
    
    
    public Transicao(Estado estadoInicial, Simbolo simbolo, Estado estadoFinal, Token tokenAnterior, Token tokenAtual) {
        this.estadoInicial = estadoInicial;
        this.simbolo = simbolo;
        this.estadoFinal = estadoFinal;
        this.tokenAnterior = tokenAnterior;
        this.tokenAtual = tokenAtual;
    }
    
    

    public Estado getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(Estado estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public Simbolo getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(Simbolo simbolo) {
        this.simbolo = simbolo;
    }

    public Estado getEstadoFinal() {
        return estadoFinal;
    }

    public void setEstadoFinal(Estado estadoFinal) {
        this.estadoFinal = estadoFinal;
    }

    public Token getTokenAnterior() {
        return tokenAnterior;
    }

    public void setTokenAnterior(Token tokenAnterior) {
        this.tokenAnterior = tokenAnterior;
    }

    public Token getTokenAtual() {
        return tokenAtual;
    }

    public void setTokenAtual(Token tokenAtual) {
        this.tokenAtual = tokenAtual;
    }    
}
