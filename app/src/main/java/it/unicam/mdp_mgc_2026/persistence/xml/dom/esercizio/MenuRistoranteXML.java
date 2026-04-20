package it.unicam.mdp_mgc_2026.persistence.xml.dom.esercizio;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

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

    private void salvaDocumento(Document doc) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filePath));

        transformer.transform(source, result);
    }

    public boolean aggiungiPiatto(String nomeMenu, Piatto nuovoPiatto) throws Exception {
        Document doc = caricaDocumento();
        Element root = doc.getDocumentElement();
        NodeList listaMenu = root.getElementsByTagName("menu");

        for (int i = 0; i < listaMenu.getLength(); i++) {
            Node nodoMenu = listaMenu.item(i);

            if (nodoMenu.getNodeType() == Node.ELEMENT_NODE) {
                Element elementoMenu = (Element) nodoMenu;

                if (nomeMenu.equals(elementoMenu.getAttribute("nome"))) {

                    NodeList listaCibi = elementoMenu.getElementsByTagName("cibi");
                    if (listaCibi.getLength() == 0) {
                        return false;
                    }

                    Element elementoCibi = (Element) listaCibi.item(0);

                    Element nuovoElementoPiatto = doc.createElement("piatto");

                    Element nome = doc.createElement("nome");
                    nome.setTextContent(nuovoPiatto.getNome());

                    Element descrizione = doc.createElement("descrizione");
                    descrizione.setTextContent(nuovoPiatto.getDescrizione());

                    Element categoria = doc.createElement("categoria");
                    categoria.setTextContent(nuovoPiatto.getCategoria().name());

                    Element prezzo = doc.createElement("prezzo");
                    prezzo.setTextContent(String.valueOf(nuovoPiatto.getPrezzo()));

                    Element allergeni = doc.createElement("allergeni");
                    for (Allergene a : nuovoPiatto.getAllergeni()) {
                        Element allergene = doc.createElement("allergene");
                        allergene.setTextContent(String.valueOf(a.getCodice()));
                        allergeni.appendChild(allergene);
                    }

                    nuovoElementoPiatto.appendChild(nome);
                    nuovoElementoPiatto.appendChild(descrizione);
                    nuovoElementoPiatto.appendChild(categoria);
                    nuovoElementoPiatto.appendChild(prezzo);
                    nuovoElementoPiatto.appendChild(allergeni);

                    elementoCibi.appendChild(nuovoElementoPiatto);

                    salvaDocumento(doc);
                    return true;
                }
            }
        }

        return false;
    }

    public boolean rimuoviPiatto(String nomeMenu, String nomePiattoDaRimuovere) throws Exception {
        Document doc = caricaDocumento();
        Element root = doc.getDocumentElement();
        NodeList listaMenu = root.getElementsByTagName("menu");

        for (int i = 0; i < listaMenu.getLength(); i++) {
            Node nodoMenu = listaMenu.item(i);

            if (nodoMenu.getNodeType() == Node.ELEMENT_NODE) {
                Element elementoMenu = (Element) nodoMenu;

                if (nomeMenu.equals(elementoMenu.getAttribute("nome"))) {
                    NodeList listaCibi = elementoMenu.getElementsByTagName("cibi");
                    if (listaCibi.getLength() == 0) {
                        return false;
                    }

                    Element elementoCibi = (Element) listaCibi.item(0);
                    NodeList listaPiatti = elementoCibi.getElementsByTagName("piatto");

                    for (int j = 0; j < listaPiatti.getLength(); j++) {
                        Node nodoPiatto = listaPiatti.item(j);

                        if (nodoPiatto.getNodeType() == Node.ELEMENT_NODE) {
                            Element elementoPiatto = (Element) nodoPiatto;

                            String nomePiatto = getTestoTagObbligatorio(elementoPiatto, "nome").trim();
                            String nomeDaRimuovere = nomePiattoDaRimuovere.trim();

                            if (nomePiatto.equalsIgnoreCase(nomeDaRimuovere)) {
                                elementoCibi.removeChild(elementoPiatto);
                                salvaDocumento(doc);
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    public boolean modificaPiatto(String nomeMenu, String nomePiattoDaModificare, Piatto nuovoPiatto) throws Exception {
        Document doc = caricaDocumento();
        Element root = doc.getDocumentElement();
        NodeList listaMenu = root.getElementsByTagName("menu");

        for (int i = 0; i < listaMenu.getLength(); i++) {
            Node nodoMenu = listaMenu.item(i);

            if (nodoMenu.getNodeType() == Node.ELEMENT_NODE) {
                Element elementoMenu = (Element) nodoMenu;

                if (nomeMenu.equals(elementoMenu.getAttribute("nome"))) {
                    NodeList listaPiatti = elementoMenu.getElementsByTagName("piatto");

                    for (int j = 0; j < listaPiatti.getLength(); j++) {
                        Node nodoPiatto = listaPiatti.item(j);

                        if (nodoPiatto.getNodeType() == Node.ELEMENT_NODE) {
                            Element elementoPiatto = (Element) nodoPiatto;

                            String nomePiatto = getTestoTagObbligatorio(elementoPiatto, "nome").trim();
                            String nomeDaModificare = nomePiattoDaModificare.trim();

                            if (nomePiatto.equalsIgnoreCase(nomeDaModificare)) {

                                // aggiorno nome
                                elementoPiatto.getElementsByTagName("nome")
                                        .item(0)
                                        .setTextContent(nuovoPiatto.getNome());

                                // descrizione
                                NodeList descrizioni = elementoPiatto.getElementsByTagName("descrizione");

                                if (descrizioni.getLength() > 0) {
                                    descrizioni.item(0).setTextContent(nuovoPiatto.getDescrizione());
                                } else if (nuovoPiatto.getDescrizione() != null && !nuovoPiatto.getDescrizione().isBlank()) {
                                    Element descrizioneEl = doc.createElement("descrizione");
                                    descrizioneEl.setTextContent(nuovoPiatto.getDescrizione());

                                    Node categoriaNode = elementoPiatto.getElementsByTagName("categoria").item(0);
                                    elementoPiatto.insertBefore(descrizioneEl, categoriaNode);
                                }

                                // categoria
                                elementoPiatto.getElementsByTagName("categoria")
                                        .item(0)
                                        .setTextContent(nuovoPiatto.getCategoria().name());

                                // prezzo
                                elementoPiatto.getElementsByTagName("prezzo")
                                        .item(0)
                                        .setTextContent(String.valueOf(nuovoPiatto.getPrezzo()));

                                // allergeni
                                NodeList listaAllergeni = elementoPiatto.getElementsByTagName("allergeni");
                                Element allergeniEl;

                                if (listaAllergeni.getLength() > 0) {
                                    allergeniEl = (Element) listaAllergeni.item(0);

                                    // svuoto
                                    while (allergeniEl.hasChildNodes()) {
                                        allergeniEl.removeChild(allergeniEl.getFirstChild());
                                    }
                                } else {
                                    allergeniEl = doc.createElement("allergeni");
                                    elementoPiatto.appendChild(allergeniEl);
                                }

                                // aggiungo nuovi allergeni
                                for (Allergene a : nuovoPiatto.getAllergeni()) {
                                    Element allergeneEl = doc.createElement("allergene");
                                    allergeneEl.setTextContent(String.valueOf(a.getCodice()));
                                    allergeniEl.appendChild(allergeneEl);
                                }

                                salvaDocumento(doc);
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

}
