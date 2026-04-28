package it.unicam.mdp_mgc_2026.scuola.Model;

import it.unicam.mdp_mgc_2026.scuola.Contratti.CalcolatoreMedia;
import it.unicam.mdp_mgc_2026.scuola.Contratti.VisualizzatoreEsami;

import java.util.ArrayList;
import java.util.List;

import static it.unicam.mdp_mgc_2026.scuola.Utilita.FormattatoreVoto.formatta;

public class Libretto implements CalcolatoreMedia, VisualizzatoreEsami {

    private final Studente studente;
    private final List<EsameSuperato> esameSuperato = new ArrayList<>();

    public Libretto(Studente studente) {
        if (studente == null) {
            throw new IllegalArgumentException("Studente non valido");
        }
        this.studente = studente;
    }

    public void registraEsameSuperato(Esame esame, int voto) {
        esameSuperato.add(new EsameSuperato (esame, voto));
    }

    public double calcolaMedia() {
        if (esameSuperato.isEmpty()) {
            return 0.0;
        }

        int somma = 0;
        for (EsameSuperato es : esameSuperato) {
            int v = es.getVoto();
            somma += (v == 31) ? 30 : v;
        }
        return (double) somma / esameSuperato.size();
    }

    public boolean puoLaurearsi() {
        return esameSuperato.size() >= 20 && calcolaMedia() >= 24.0;
    }

    public void stampaEsamiSuperati(Studente studente) {
        System.out.println("Libretto di " + studente.getNomeCompleto());

        for (EsameSuperato es : esameSuperato) {
            System.out.println(es.getEsame().getNome() + " - " + formatta(es.getVoto()));
        }
    }
}
