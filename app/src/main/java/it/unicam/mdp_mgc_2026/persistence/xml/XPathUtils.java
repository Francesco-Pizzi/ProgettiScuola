package it.unicam.mdp_mgc_2026.persistence.xml;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

public class XPathUtils {

    /**
     * Esegue una query XPath su un nodo (o documento) e restituisce una NodeList.
     *
     * @param n Il nodo di partenza (context node)
     * @param xPathQuery La stringa contenente la query XPath
     * @return NodeList contenente i risultati della query
     * @throws XPathExpressionException Se la query non è sintatticamente corretta
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