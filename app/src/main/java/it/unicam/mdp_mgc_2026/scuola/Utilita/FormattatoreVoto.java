package it.unicam.mdp_mgc_2026.scuola.Utilita;

public class FormattatoreVoto {

    public static String formatta (int voto){
        return (voto == 31) ? "30 e lode" : String.valueOf(voto);
    }
}
