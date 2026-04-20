package it.unicam.mdp_mgc_2026.persistence.xml.dom.esercizio;

public class Main {
    public static void main(String[] args) {
        try {
            MenuRistoranteXML menuXML = new MenuRistoranteXML("menu.xml");
            Menu menuCompleto = menuXML.leggiMenuPerCat("Menu Principale");
            Menu menuDelGiorno = menuXML.leggiMenuPerCat("Menu Del Giorno");
            Menu cartaDeiVini = menuXML.leggiMenuPerCat("Carta Dei Vini");

            Piatto nuovoPiatto = new Piatto("Torta al testo", "Farcita con prosciutto crudo e rucola", 7.50, Piatto.Categoria.ANTIPASTO);
            nuovoPiatto.aggiungiAllergene(Allergene.GLUTINE);

            if (menuXML.aggiungiPiatto("Menu Principale", nuovoPiatto)) {
                System.out.println("Piatto aggiunto con successo");
            } else {
                System.out.println("Aggiunta fallita");
            }

            if (menuXML.rimuoviPiatto("Menu Principale", "Torta al testo")){
                System.out.println("Piatto rimosso con successo");
            } else {
                System.out.println("Piatto non trovato");
            }

            Piatto modificato = new Piatto("Torta al testo SPECIAL", "Con crema di formaggio", 8.50, Piatto.Categoria.ANTIPASTO);
            modificato.aggiungiAllergene(Allergene.GLUTINE);


            if (menuXML.modificaPiatto("Menu Principale", "Torta al testo SPECIAL", modificato)){
                System.out.println("Piatto modificato con successo");
            } else {
                System.out.println("Piatto NON trovato");
            }

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