package it.unicam.mdp_mgc_2026.annotations;

import lombok.NonNull;

import java.io.Serializable;

/**
 * Questa classe implementa il concetto di persona ai soli fini didattici per i corsi di
 * Metodolgie di Programmmazione e Modella zione e Gestione della Conoscenza.
 *
 * @author Lorenzo Rossi
 *
 * @version 1.0
 */
@Revisore(data = "25/03/2026")
public class Persona extends Entita implements Serializable {

    private static final long serialVersionUID = 1L;
    private int eta;
    private String nome, cognome, cf;



    /**
     * Costruisce un oggetto persona a partire da un nome, un cognome e un codice fiscale.
     * @param nome Il nome della persona
     * @param cognome Il cognone della persona
     * @param cf Il codice fiscale della persona
     *
     */
    @Revisore(nome = "Fabrizio Fornari", data ="25/03/2025")
    public Persona(@NonNull String nome, @NonNull String cognome, @NonNull String cf) {
        this.nome = nome;
        this.cognome = cognome;
        this.cf = cf;
    }

    @Override
    public String toString(){
        return nome + " " + cognome + " " + cf;
    }

    @Override
    public int hashCode() {
        return cf.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Persona) {
            Persona other = (Persona) obj;
            return nome.equals(other.nome) && cognome.equals(other.cognome) && eta == other.eta;
        }
        return false;
    }

    /**
     * Imposta l'età della persona
     * @param eta l'età della persona
     * @deprecated Questo metodo può ricevere double invece che interi.
     */
    @Deprecated
    public void setEta(double eta) {
        this.eta = (int) eta;
    }

    /**
     * Imposta l'età della persona
     * @param eta l'età della persona
     */
    public void setEta(int eta){
        this.eta = eta;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getCf() {
        return cf;
    }

    public int getEta() {
        return eta;
    }
}


