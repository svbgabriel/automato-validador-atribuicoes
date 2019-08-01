package br.anhembi.cco.ava.automato;

import java.util.Objects;

/**
 * Representa um estado do autômato.
 * 
 * Um simples POJO, onde o atributo fim indica se o estado é final.
 * 
 * A classe faz o override de <code>equals</code> para a comparação com outro
 * estado.
 * 
 * @author Gabriel Batista
 * @author Henrique Albanese
 * @author Sérgio Umlauf
 */
public class Estado {

    private String nome;
    private boolean fim;
    
    
    public Estado() {
        
    }
    
    public Estado(String nome) {
        this.nome = nome;
    }
    
    public Estado(String nome, boolean fim) {
        this.nome = nome;
        this.fim = fim;
    }
    

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isFim() {
        return fim;
    }

    public void setFim(boolean fim) {
        this.fim = fim;
    }
   
    
    
    
    @Override
    public String toString() {
        return this.nome;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Estado) {
            Estado e = (Estado)o;
            if(e.nome.equals(this.nome)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.nome);
        return hash;
    }

}
