package br.anhembi.cco.ava;

import java.util.Objects;

/**
 *
 * @author Sérgio Umlauf
 */
public class Estado {

    private String nome;
    private boolean fim;
    private boolean guardaLexema;
    
    
    
    public Estado() {
        
    }
    
    public Estado(String nome) {
        this.nome = nome;
    }
    
    public Estado(String nome, boolean guardaLexema) {
        this.nome = nome;
        this.guardaLexema = guardaLexema;
    }
    
    public Estado(String nome, boolean guardaLexema, boolean fim) {
        this.nome = nome;
        this.guardaLexema = guardaLexema;
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

    public boolean isGuardaLexema() {
        return guardaLexema;
    }

    public void setGuardaLexema(boolean guardaLexema) {
        this.guardaLexema = guardaLexema;
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
