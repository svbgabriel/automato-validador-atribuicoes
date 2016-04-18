package br.anhembi.cco.ava;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Sérgio Umlauf
 */
public class TabelaTransicaoEstados {

    //private final Map<State, Map<String, State>> states;
    private final Map<Estado, Map<String, Estado>> estados;
    
    
    public TabelaTransicaoEstados() {
        //this.states = new HashMap<>();
        this.estados = new HashMap<>();
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
    
    
    public void add(Estado ini, String simbolo, Estado end) {
        Map<String, Estado> estadoFim = new HashMap<>();
        estadoFim.put(simbolo, end);
        estados.put(ini, estadoFim);
    }
    
    
    public Estado transicao(Estado ini, String simbolo) {
        Estado res = null;
        Map<String, Estado> m = estados.get(ini);
        if(m != null) {
            res = m.get(simbolo);
        }
        return res;
    }
}
