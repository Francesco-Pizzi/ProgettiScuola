package it.unicam.mdp_mgc_2026.palla8;

import java.util.Scanner;

public class Main {

    public void main(String[] args){
        System.out.println("Benvenuto al gioco della Palla8");
        //boolean finito = false;
        Scanner scanner = new Scanner(System.in);
        Palla8 p = new Palla8();

        while (true){
            System.out.println("Inserisci una domanda o scrivi ESCI per terminare...");
            String domanda = scanner.nextLine();
            if(domanda.equalsIgnoreCase("ESCI")){
                //finito = true;
                break;
            }
            //continuo il programma leggola  domanda e fdo la risposta
            System.out.println(p.getRisposta());

        }

        scanner.close();
    }
}
