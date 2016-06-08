package util;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.swing.JRadioButton;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddRadio {
    List<JRadioButton> radios = new ArrayList<JRadioButton>();

    ///Afisare radio box in form
    public List<JRadioButton> showParsers(String path) {
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setIgnoringComments(true);
        DocumentBuilder builder = null;
        try {
            builder = domFactory.newDocumentBuilder();
            Document doc = builder.parse(new File(path));

            NodeList docs = doc.getElementsByTagName("Parser");

            if (docs.getLength() > 0) {
                for (int i = 0; i < docs.getLength(); i++) {
                    NodeList element = docs.item(i).getChildNodes();
                    for (int j = 0; j < element.getLength(); j++) {
                        Node node = element.item(j);
                        if (node.getTextContent().length() > 10)
                            radios.add(new JRadioButton(node.getTextContent()));
                    }
                }
            } else
                System.out.println(docs.getLength());

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return radios;
    }

    //adaugarea unui nou parser in fisierul xml
    public void addRadio(String path, String versionName, String versionTag) {
        try {
            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            domFactory.setIgnoringComments(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document doc = builder.parse(new File(path));

            NodeList nodes = doc.getElementsByTagName("Parser");
            NodeList nodes1 = doc.getElementsByTagName(versionTag);

            if (nodes1.getLength() == 1) {
                System.out.println("Elementul exista in fisierul xml");
            } else {
                Text a = doc.createTextNode(versionName);
                Element p = doc.createElement(versionTag);
                p.appendChild(a);

                nodes.item(0).getParentNode().insertBefore(p, nodes.item(0));

                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");

                StreamResult result = new StreamResult(new File(path));
                DOMSource source = new DOMSource(doc);
                transformer.transform(source, result);

                System.out.println("Succes adding parser");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
