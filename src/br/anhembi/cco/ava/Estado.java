package br.anhembi.cco.ava;

import java.util.Objects;

/**
 *
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
