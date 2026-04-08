package it.unicam.mdp_mgc_2026.persistence;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Questa classe implementa il Cifrario di Cesare
 *
 * @author Lorenzo Rossi
 */
public class CifrarioDiCesare {

    /**
     * Crittografa o decrittografa un dato messaggio utilizzando una chiave privata specificata.
     * L'operazione eseguita dipende dal parametro della modalità.
     *
     * @param privateKey la chiave utilizzata per il processo di crittografia o decrittografia
     * @param message l'array di caratteri che rappresenta il messaggio da crittografare o decrittografare
     * @param mode la modalità operativa (ad esempio, crittografia o decrittografia)
     * @return l'array di caratteri modificato dopo la crittografia o la decrittografia
     */
    public static char[] encrypt_decript(int privateKey, char[] message, Modality mode) {
        for (int i = 0; i < message.length; i++){
            int enc = message[i];
            switch (mode){
                case CRYPT -> enc += privateKey;
                case DECRYPT -> enc -= privateKey;
            }
            enc = enc%Character.MAX_VALUE;
            if (enc < 0){
                enc += Character.MAX_VALUE;
            }
            message[i] = (char) enc;
        }
        return message;
    }

    public static void main(String[] args) {
        //Scrittura file criptato
        int key = -20000;
        char[] encripted = encrypt_decript(key, "Messaggio segreto!".toCharArray(), Modality.CRYPT);
        char[] decripted = encrypt_decript(key, encripted.clone(), Modality.DECRYPT);
        System.out.println(encripted);
        System.out.println(decripted);
    }

    public enum Modality{
        CRYPT,DECRYPT
    }

}
