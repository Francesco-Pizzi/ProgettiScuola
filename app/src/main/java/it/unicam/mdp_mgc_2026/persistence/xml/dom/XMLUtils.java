package it.unicam.mdp_mgc_2026.persistence.xml.dom;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;

/**
 * A utility class providing various methods for handling XML documents using the DOM and XPath APIs.
 */
public class XMLUtils {

    /**
     * Loads an XML document from the specified file path and returns it as a DOM Document object.
     *
     * @param path The file path to the XML document to be loaded.
     * @return A {@code Document} representing the parsed XML content.
     * @throws ParserConfigurationException If a DocumentBuilder cannot be created which satisfies the configuration requested.
     * @throws IOException If any IO errors occur while reading the file.
     * @throws SAXException If any parse errors occur while parsing the XML file.
     */
    public static Document loadDomDocument(String path) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(path);
    }

    /**
     * Writes a DOM {@code Document} object to an XML file at the specified file path.
     *
     * @param dom The {@code Document} representing the DOM structure to be written to the file.
     * @param path The file path where the XML content should be written.
     * @throws TransformerException If an error occurs during the transformation process.
     */
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

    /**
     * Recursively prints the structure and content of a DOM {@code Node}, including its name,
     * attributes (for {@code Element} nodes), and text content (for {@code Text} nodes).
     *
     * @param n The {@code Node} to be printed. Can be an {@code Element}, a {@code Text}, or other types of nodes.
     * @param spaces A string representing the current indentation level, used to format the output
     *               and visually indicate the node hierarchy.
     */
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

    /**
     * Executes an XPath query on the given DOM {@code Node} and returns the matching nodes as a {@code NodeList}.
     *
     * @param n The {@code Node} to evaluate the XPath query against. Must not be {@code null}.
     * @param xPathQuery The XPath query string used to select nodes. Must not be {@code null}.
     * @return A {@code NodeList} containing the nodes that match the specified XPath query,
     *         or {@code null} if either the input node or the query is {@code null}.
     * @throws XPathExpressionException If an error occurs during the evaluation of the XPath query.
     */
    public static NodeList executeQuery(Node n, String xPathQuery) throws XPathExpressionException {
        if (n == null || xPathQuery == null) {
            return null;
        }

        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xpath = xPathFactory.newXPath();

        return (NodeList) xpath.evaluate(xPathQuery, n, XPathConstants.NODESET);
    }



}