package it.unicam.mdp_mgc_2026.scuola;

import java.util.ArrayList;
import java.util.List;

public class Corso {
    private String nome;
    private String docente;
    List<Studente> listaStudenti;

    public Corso(String nome, String docente) {
        if (nome == null || docente == null) {
            throw new IllegalArgumentException("Nome o docente errati");
        }
        this.nome = nome;
        this.docente = docente;
        this.listaStudenti = new ArrayList<Studente>();
    }

    public boolean iscriviStudente(Studente studente) {
        if (studente == null) throw new IllegalArgumentException("Studente errato");
        if (listaStudenti.contains(studente)) return false;
        return listaStudenti.add(studente);
    }

    public void stampaIscritti(){
        if (listaStudenti.isEmpty()) System.out.println("Nessuno studente");
        else for (Studente studente : listaStudenti) {
            System.out.println(studente.getNomeCompleto() + " Iscritto al corso ");
        }
    }
}
