package br.anhembi.cco.ava;

/**
 *
 * @author lab735
 */
public class Transicao {
    
    private Estado estadoInicial;
    private Token simbolo;
    private Estado estadoFinal;
    
    
    public Transicao() {
        
    }
    
    public Transicao(Estado estadoInicial, Token simbolo, Estado estadoFinal) {
        this.estadoInicial = estadoInicial;
        this.simbolo = simbolo;
        this.estadoFinal = estadoFinal;
    }
    

    public Estado getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(Estado estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public Token getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(Token simbolo) {
        this.simbolo = simbolo;
    }

    public Estado getEstadoFinal() {
        return estadoFinal;
    }

    public void setEstadoFinal(Estado estadoFinal) {
        this.estadoFinal = estadoFinal;
    }
    
}
