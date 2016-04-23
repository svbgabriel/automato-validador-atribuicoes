package br.anhembi.cco.ava;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sérgio Umlauf
 */
public class TabelaTransicaoEstados {    
    
    private final List<Transicao> estados;

    
    public TabelaTransicaoEstados() {
        this.estados = new ArrayList<>();
    }
    
    
    
    
    public void add(Estado ini, Token simbolo, Estado end) {
        add(new Transicao(ini, simbolo, end));
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
