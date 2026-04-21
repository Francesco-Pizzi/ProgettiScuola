package it.unicam.mdp_mgc_2026.scuola;

public abstract class Persona {
    private String nome;
    private String cognome;

    public Persona(String nome, String cognome) {
        if (nome == null || cognome == null) {
            throw new NullPointerException("Nome o Cognome non validi");
        }
        this.nome = nome;
        this.cognome = cognome;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNomeCompleto(){
        return this.nome + " " + this.cognome;
    }

    public abstract void presentati();
}
