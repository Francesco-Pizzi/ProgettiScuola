package it.unicam.mdp_mgc_2026.persistence.xml.dom.esercizio;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MenuRistoranteXML {

    private final String filePath;

    public MenuRistoranteXML(String filePath) {
        this.filePath = filePath;
    }

    private Document caricaDocumento() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(new File(filePath));
    }

    private String getTestoTag(Element elemento, String nomeTag) {
        NodeList lista = elemento.getElementsByTagName(nomeTag);
        if (lista.getLength() > 0) {
            return lista.item(0).getTextContent();
        }
        return null;
    }

    private String getTestoTagObbligatorio(Element elemento, String nomeTag) {
        String valore = getTestoTag(elemento, nomeTag);
        if (valore == null) {
            throw new IllegalArgumentException("Tag mancante: " + nomeTag);
        }
        return valore;
    }

    public Menu leggiMenuPerCat(String nomeMenu) throws Exception {
        Document doc = caricaDocumento();
        Element root = doc.getDocumentElement();
        NodeList listaMenu = root.getElementsByTagName("menu");

        for (int i = 0; i < listaMenu.getLength(); i++) {
            Node nodoMenu = listaMenu.item(i);

            if (nodoMenu.getNodeType() == Node.ELEMENT_NODE) {
                Element elementoMenu = (Element) nodoMenu;
                String nomeTag = elementoMenu.getAttribute("nome");

                if (nomeMenu.equals(nomeTag)) {
                    Menu menu = new Menu(nomeTag);

                    leggiPiatti(elementoMenu, menu);
                    leggiBevande(elementoMenu, menu);
                    leggiVini(elementoMenu, menu);

                    return menu;
                }
            }
        }
        return null;
    }

    public void leggiPiatti(Element elementoMenu, Menu menu) throws Exception {
        NodeList listaCibi = elementoMenu.getElementsByTagName("cibi");
        if (listaCibi.getLength() == 0) return;

        Element elementoCibi = (Element) listaCibi.item(0);
        NodeList listaPiatti = elementoCibi.getElementsByTagName("piatto");

        for (int i = 0; i < listaPiatti.getLength(); i++) {
            Node nodePiatti = listaPiatti.item(i);

            if (nodePiatti.getNodeType() == Node.ELEMENT_NODE) {
                Element elementoPiatto = (Element) nodePiatti;

                String nomePiatto = getTestoTagObbligatorio(elementoPiatto, "nome");
                String descrizione = getTestoTag(elementoPiatto, "descrizione");
                String categoriaPiatto = getTestoTagObbligatorio(elementoPiatto, "categoria");
                double prezzo = Double.parseDouble(getTestoTagObbligatorio(elementoPiatto, "prezzo"));

                Piatto.Categoria categoria = Piatto.Categoria.valueOf(categoriaPiatto);

                Piatto piatto = new Piatto(nomePiatto, descrizione, prezzo, categoria);

                NodeList listaAllergeni = elementoPiatto.getElementsByTagName("allergeni");
                if (listaAllergeni.getLength() > 0) {
                    Element elementoAllergeni = (Element) listaAllergeni.item(0);
                    NodeList allergeniNodes = elementoAllergeni.getElementsByTagName("allergene");

                    for (int j = 0; j < allergeniNodes.getLength(); j++) {
                        Node nodoAllergene = allergeniNodes.item(j);

                        if (nodoAllergene.getNodeType() == Node.ELEMENT_NODE) {
                            int codice = Integer.parseInt(nodoAllergene.getTextContent().trim());
                            Allergene allergene = Allergene.daCodice(codice);
                            piatto.aggiungiAllergene(allergene);
                        }
                    }
                }

                menu.aggiungiPiatto(piatto);
            }
        }
    }

    private void leggiBevande(Element elementoMenu, Menu menu) {
        NodeList listaBevande = elementoMenu.getElementsByTagName("bevande");

        if (listaBevande.getLength() == 0) return;

        Element elementoBevande = (Element) listaBevande.item(0);
        NodeList bevandeNodes = elementoBevande.getElementsByTagName("bevanda");

        for (int i = 0; i < bevandeNodes.getLength(); i++) {
            Node nodoBevanda = bevandeNodes.item(i);

            if (nodoBevanda.getNodeType() == Node.ELEMENT_NODE) {
                Element elementoBevanda = (Element) nodoBevanda;

                String nomeBevanda = getTestoTagObbligatorio(elementoBevanda, "nome");
                String descrizione = getTestoTag(elementoBevanda, "descrizione");
                String tipoTesto = getTestoTagObbligatorio(elementoBevanda, "tipo");

                Bevanda.TipoBevanda tipo = Bevanda.TipoBevanda.valueOf(tipoTesto);

                List<OpzionePrezzo> opzioni = new ArrayList<>();

                NodeList listaOpzioniPrezzo = elementoBevanda.getElementsByTagName("opzioniPrezzo");
                if (listaOpzioniPrezzo.getLength() > 0) {
                    Element elementoOpzioni = (Element) listaOpzioniPrezzo.item(0);
                    NodeList opzioniNodes = elementoOpzioni.getElementsByTagName("opzione");

                    for (int j = 0; j < opzioniNodes.getLength(); j++) {
                        Node nodoOpzione = opzioniNodes.item(j);

                        if (nodoOpzione.getNodeType() == Node.ELEMENT_NODE) {
                            Element elementoOpzione = (Element) nodoOpzione;

                            String formato = getTestoTagObbligatorio(elementoOpzione, "formato");
                            double prezzo = Double.parseDouble(getTestoTagObbligatorio(elementoOpzione, "prezzo"));

                            opzioni.add(new OpzionePrezzo(formato, prezzo));
                        }
                    }
                }

                Bevanda bevanda = new Bevanda(nomeBevanda, descrizione, tipo, opzioni);
                menu.aggiungiBevanda(bevanda);
            }
        }
    }

    private void leggiVini(Element elementoMenu, Menu menu) {
        NodeList viniNodes = elementoMenu.getElementsByTagName("vino");

        if (viniNodes.getLength() == 0) return;

        for (int i = 0; i < viniNodes.getLength(); i++) {
            Node nodoVino = viniNodes.item(i);

            if (nodoVino.getNodeType() == Node.ELEMENT_NODE) {
                Element elementoVino = (Element) nodoVino;

                String nomeVino = getTestoTagObbligatorio(elementoVino, "nome");
                String descrizione = getTestoTag(elementoVino, "descrizione");
                String cantina = getTestoTagObbligatorio(elementoVino, "cantina");
                String coloreTesto = getTestoTagObbligatorio(elementoVino, "colore");

                Vino.ColoreVino colore = Vino.ColoreVino.valueOf(coloreTesto);

                List<OpzionePrezzo> opzioni = new ArrayList<>();

                NodeList listaOpzioniPrezzo = elementoVino.getElementsByTagName("opzioniPrezzo");
                if (listaOpzioniPrezzo.getLength() > 0) {
                    Element elementoOpzioni = (Element) listaOpzioniPrezzo.item(0);
                    NodeList opzioniNodes = elementoOpzioni.getElementsByTagName("opzione");

                    for (int j = 0; j < opzioniNodes.getLength(); j++) {
                        Node nodoOpzione = opzioniNodes.item(j);

                        if (nodoOpzione.getNodeType() == Node.ELEMENT_NODE) {
                            Element elementoOpzione = (Element) nodoOpzione;

                            String formato = getTestoTagObbligatorio(elementoOpzione, "formato");
                            double prezzo = Double.parseDouble(getTestoTagObbligatorio(elementoOpzione, "prezzo"));

                            opzioni.add(new OpzionePrezzo(formato, prezzo));
                        }
                    }
                }

                Vino vino = new Vino(nomeVino, descrizione, Bevanda.TipoBevanda.VINO, opzioni, cantina, colore);
                menu.aggiungiVino(vino);
            }
        }
    }

}
