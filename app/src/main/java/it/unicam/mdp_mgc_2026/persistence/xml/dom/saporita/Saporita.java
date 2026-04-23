package it.unicam.mdp_mgc_2026.persistence.xml.dom.saporita;

import it.unicam.mdp_mgc_2026.persistence.xml.dom.XMLUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Saporita {

    private Document dom;
    private final String path = "saporita.xml";
    private Menu menu;

    public Saporita(){
        menu = new Menu();
        loadMenu();
    }


    private void loadMenu() {
        try {
            this.dom = XMLUtils.loadDomDocument(path);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
        NodeList piatti = null;
        try {
            piatti = XMLUtils.executeQuery(dom, "//piatti/*");
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }
        createMenu(piatti);
        Node mdg = null;
        try {
            mdg = XMLUtils.executeQuery(dom, "//menudelgiorno").item(0);
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }
        try {
            piatti = XMLUtils.executeQuery(mdg, "./piatto");
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }
        createMdg(mdg,piatti);
    }

    private void createMdg(Node mdg, NodeList piatti) {
        String data = mdg.getAttributes().getNamedItem("giorno").getNodeValue();
        Set<String> piattiId = new HashSet<>();
        for (int i = 0; i < piatti.getLength(); i++){
            piattiId.add(piatti.item(i).getAttributes().getNamedItem("idRef").getNodeValue());
        }
        menu.setMenuDelGiorno(data,piattiId);
    }

    private void createMenu(NodeList piatti) {
        for (int i = 0; i<piatti.getLength(); i++){
            Node n = piatti.item(i);
            //System.out.println(piatti.item(i).getNodeName());
            if (!(n.getNodeName().equals("primo") || n.getNodeName().equals("secondo"))){
                continue;
            }
            String id = n.getAttributes().getNamedItem("id").getNodeValue();
            String nome = "", descrizione = "";
            double prezzo = 0;
            for (int j = 0; j < n.getChildNodes().getLength(); j++){
                Node child = n.getChildNodes().item(j);
                if (child.getNodeName().equals("nome")){
                    nome = child.getFirstChild().getNodeValue();
                }else if (child.getNodeName().equals("descrizione")){
                    descrizione = child.getFirstChild().getNodeValue();
                }else if (child.getNodeName().equals("prezzo")){
                    prezzo = Double.parseDouble(child.getFirstChild().getNodeValue());
                }
            }
            Piatto piatto = null;
            if (n.getNodeName().equals("primo"))
                piatto = new Primo(nome,descrizione,prezzo,id);
            else if (n.getNodeName().equals("secondo"))
                piatto = new Secondo(nome,descrizione,prezzo,id);
            menu.addPiatto(piatto);
        }
    }

    public static void main(String[] args){
        Saporita s = new Saporita();
        System.out.println(s.menu);

    }
}
