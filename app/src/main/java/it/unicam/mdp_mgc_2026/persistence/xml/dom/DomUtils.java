package it.unicam.mdp_mgc_2026.persistence.xml.dom;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class DomUtils {

    public static Document loadDomDocument(String path) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(path);
    }

    public static void writeDomDocument(Document dom, String path) throws TransformerException {
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();

        // Impostazioni per rendere l'XML leggibile (indentazione)
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        //transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        DOMSource source = new DOMSource(dom);
        StreamResult result = new StreamResult(new File(path));

        transformer.transform(source, result);
    }

    public static void print(Node n, String spaces) {
        if (n == null) return;
        if (n instanceof Element) {
            String s = spaces + n.getNodeName() + " (";
            NamedNodeMap map = n.getAttributes();
            if (map != null)
                for (int i = 0; i < map.getLength(); i++)
                    s += map.item(i).getNodeName() + "=" + map.item(i).getNodeValue();
            s += ")";
            System.out.println(s);
        } else  if (n instanceof Text)
            System.out.println(spaces + n.getNodeValue());
        NodeList children = n.getChildNodes();
        for (int i = 0; i < children.getLength(); i++)
            print(children.item(i), spaces + " – ");
    }

}
