package br.anhembi.cco.ava;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gabriel Batista
 * @author Henrique Albanese
 * @author Sérgio Umlauf
 */
public class TabelaTransicaoEstados {    
    
    private final List<Transicao> estados;

    
    public TabelaTransicaoEstados() {
        this.estados = new ArrayList<>();
    }
    
    
    
    
    public void add(Estado ini, Simbolo simbolo, Estado end) {
        add(new Transicao(ini, simbolo, end));
    }
    
    public void add(Estado ini, Simbolo simbolo, Estado end, Token tokenAnterior) {
        add(new Transicao(ini, simbolo, end, tokenAnterior));
    }

    public void add(Estado ini, Simbolo simbolo, Estado end, Token tokenAnterior, Token tokenAtual) {
        add(new Transicao(ini, simbolo, end, tokenAnterior, tokenAtual));
    }
    
    public void add(Transicao transicao) {
        for(Transicao t : getEstados()) {
            if(t.getEstadoInicial().equals(transicao.getEstadoInicial()) && 
                    t.getSimbolo().equals(transicao.getSimbolo())) {
                return;
            }
        }
        getEstados().add(transicao);
    }


    public List<Transicao> getEstados() {
        return estados;
    }

}
