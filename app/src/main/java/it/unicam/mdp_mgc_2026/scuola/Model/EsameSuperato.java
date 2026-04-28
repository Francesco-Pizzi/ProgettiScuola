package it.unicam.mdp_mgc_2026.scuola.Model;

public class EsameSuperato {
    Esame esame;
    int voto;

    public EsameSuperato(Esame esame, int voto) {
        if (esame == null) {
            throw new IllegalArgumentException("Esame non valido");
        }
        if (voto < 18 || voto > 31) {
            throw new IllegalArgumentException("Voto non valido");
        }

        this.esame = esame;
        this.voto = voto;
    }

    public Esame getEsame() {
        return esame;
    }
    public int getVoto(){
        return voto;
    }
}
