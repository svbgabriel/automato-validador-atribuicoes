package br.anhembi.cco.ava.automato;

import java.util.Objects;

/**
 * Representa uma transição do autômato.
 * 
 * O método <code>equals</code> é sobrescrito para a comparação com outra transição.
 * 
 * Uma transicao é dita igual a outra se ambas possuem o mesmo estado inicial
 * e símbolo.
 * 
 * @author Gabriel Batista
 * @author Henrique Albanese
 * @author Sérgio Umlauf
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
    
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        
        if (!Transicao.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        
        final Transicao other = (Transicao) obj;
        
        if ((this.estadoInicial == null) ? (other.estadoInicial != null) : !this.estadoInicial.equals(other.estadoInicial)) {
            return false;
        }
        
        if (this.simbolo != other.simbolo) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.estadoInicial);
        hash = 13 * hash + Objects.hashCode(this.simbolo);
        return hash;
    }
}
