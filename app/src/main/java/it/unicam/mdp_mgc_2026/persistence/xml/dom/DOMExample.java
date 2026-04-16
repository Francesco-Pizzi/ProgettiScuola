package it.unicam.mdp_mgc_2026.persistence.xml.dom;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class DOMExample {

    public static void main(String[] args){
        Document dom = null;
        Element root = null;
        try {
            dom = XMLUtils.loadDomDocument("esempioDOM.xml");
            root = dom.getDocumentElement();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
        XMLUtils.print(root, "");
        addContent(dom);
        XMLUtils.print(root, "");
        try {
            XMLUtils.writeDomDocument(dom,"new.xml");
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }

    }


    public static void addContent(Document doc){
        Element root = doc.getDocumentElement();
        Element cap = doc.createElement("capitolo");
        cap.setAttribute("titolo", "Introduzione");
        cap.appendChild(doc.createTextNode("... testo  ..."));
        Node capitolo = doc.getElementsByTagNameNS("http://www.unicam.it", "capitolo").item(0);
        root.insertBefore(cap, capitolo);
        doc.normalize();
    }
}
