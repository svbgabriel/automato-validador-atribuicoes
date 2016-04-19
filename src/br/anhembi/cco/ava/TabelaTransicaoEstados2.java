package br.anhembi.cco.ava;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Sérgio Umlauf
 */
public class TabelaTransicaoEstados2 {

    //private final Map<State, Map<String, State>> states;
    private final Map<Estado2, Map<String, Estado2>> estados;
    
    
    public TabelaTransicaoEstados2() {
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
    
    
    public void add(Estado2 ini, String simbolo, Estado2 end) {
        Map<String, Estado2> estadoFim = new HashMap<>();
        estadoFim.put(simbolo, end);
        estados.put(ini, estadoFim);
    }
    
    
    public Estado2 transicao(Estado2 ini, String simbolo) {
        Estado2 res = null;
        Map<String, Estado2> m = estados.get(ini);
        if(m != null) {
            res = m.get(simbolo);
        }
        return res;
    }
}
