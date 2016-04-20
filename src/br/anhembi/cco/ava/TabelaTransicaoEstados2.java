package br.anhembi.cco.ava;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sérgio Umlauf
 */
public class TabelaTransicaoEstados2 {

    //private final Map<State, Map<String, State>> states;
    //private final Map<Estado, Map<Token, Estado>> estados;
    
    
    private final List<Transicao> estados;
    
    
    
    public TabelaTransicaoEstados2() {
        //this.states = new HashMap<>();
        this.estados = new ArrayList<>();
    }
    
    
//    public void add(State ini, String simbolo, State end) {
//        Map<String, State> estadoFim = new HashMap<>();
//        estadoFim.put(simbolo, end);
//        states.put(ini, estadoFim);
//    }
    
    
//    public State transicao(State ini, String simbolo) {
//        State res = null;
//        Map<String, State> m = states.get(ini);
//        if(m != null) {
//            res = m.get(simbolo);
//        }
//        if(res == null) {
//            return State.LIXO;
//        }
//        return res;
//    }
    
    
    public void add(Estado ini, Token simbolo, Estado end) {
        add(new Transicao(ini, simbolo, end));
    }
    
    public void add(Transicao transicao) {
        for(Transicao t : estados) {
            if(t.getEstadoInicial().equals(transicao.getEstadoInicial()) && 
                    t.getSimbolo().equals(transicao.getSimbolo())) {
                return;
            }
        }
        estados.add(transicao);
    }
    
    
    public Estado transicao(Estado ini, Token simbolo) {
        Estado res = null;
        for(Transicao t : estados) {
            if(t.getEstadoInicial().equals(ini) && t.getSimbolo().equals(simbolo)) {
                res = t.getEstadoFinal();
            }
        }
        return res;
    }
}
