package br.anhembi.cco.ava.automato;

import java.util.HashSet;
import java.util.Set;

/**
 * Representa a tabela de transição de estados do autômato.
 * 
 * Um <code>Set</code> é utilizado como container de estados,
 * para que uma mesma <code>Transicao</code> não seja inserida em duplicidade.
 * Uma transicao é dita igual a outra se ambas possuem o mesmo estado inicial
 * e símbolo.
 * 
 * @author Gabriel Batista
 * @author Henrique Albanese
 * @author Sérgio Umlauf
 */
public class TabelaTransicaoEstados {    
    
    private final Set<Transicao> estados;

    
    public TabelaTransicaoEstados() {
        this.estados = new HashSet<>();
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
        estados.add(transicao);
    }


    public Set<Transicao> getEstados() {
        return estados;
    }

}
