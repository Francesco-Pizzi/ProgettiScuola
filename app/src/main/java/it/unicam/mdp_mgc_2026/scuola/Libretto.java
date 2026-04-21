package it.unicam.mdp_mgc_2026.scuola;

import java.util.ArrayList;
import java.util.List;

public class Libretto {

    private final Studente studente;
    private final List<Esame> esamiSuperati = new ArrayList<>();
    private final List<Integer> voti = new ArrayList<>();

    public Libretto(Studente studente) {
        if (studente == null) {
            throw new IllegalArgumentException("Studente non valido");
        }
        this.studente = studente;
    }

    public void registraEsameSuperato(Esame esame, int voto) {
        if (esame == null) {
            throw new IllegalArgumentException("Esame non valido");
        }
        if (voto < 18 || voto > 31) {
            throw new IllegalArgumentException("Voto non valido");
        }

        esamiSuperati.add(esame);
        voti.add(voto);
    }

    public double calcolaMedia() {
        if (voti.isEmpty()) {
            return 0.0;
        }

        int somma = 0;
        for (int voto : voti) {
            somma += (voto == 31) ? 30 : voto;
        }

        return (double) somma / voti.size();
    }

    public boolean puoLaurearsi() {
        return voti.size() >= 20 && calcolaMedia() >= 24.0;
    }

    public void stampaEsamiSuperati() {
        System.out.println("Libretto di " + studente.getNomeCompleto());

        for (int i = 0; i < esamiSuperati.size(); i++) {
            String votoString = (voti.get(i) == 31) ? "30 e lode" : String.valueOf(voti.get(i));
            System.out.println(esamiSuperati.get(i).getNome() + " - " + votoString);
        }
    }
}
