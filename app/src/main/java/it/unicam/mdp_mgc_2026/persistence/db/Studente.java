package it.unicam.mdp_mgc_2026.persistence.db;

import jakarta.persistence.*;

@Entity
public class Studente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cognome;
    private int eta;

    // Costruttori
    public Studente() {}

    public Studente(String nome, String cognome, int eta ) {
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta ;
    }

    // Getter e Setter
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCognome() { return cognome; }
    public void setCognome(String cognome) { this.cognome = cognome; }
    public int getEta() { return eta; }
    public void setEta(int eta) { this.eta = eta; }

}
