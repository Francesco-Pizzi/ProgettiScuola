package it.unicam.mdp_mgc_2026.persistence.xml.dom.esercizio;

public class Main {
    public static void main(String[] args) {
        try {
            MenuRistoranteXML menuXML = new MenuRistoranteXML("menu.xml");
            Menu menuCompleto = menuXML.leggiMenuPerCat("Menu Principale");
            Menu menuDelGiorno = menuXML.leggiMenuPerCat("Menu Del Giorno");
            Menu cartaDeiVini = menuXML.leggiMenuPerCat("Carta Dei Vini");

            if (menuCompleto != null) {
                System.out.println(menuCompleto.stampaPerCategoria());
            }
            if (menuDelGiorno != null) {
                System.out.println(menuDelGiorno.stampaPerCategoria());
            }
            if (cartaDeiVini != null) {
                System.out.println(cartaDeiVini.stampaPerCategoria());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}