package xmlWork;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class AddVersion {

    private String filePath;

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }


    //Parsam xml si verificam daca exista tag-ul versiune
    public void addVersion() throws Exception {

        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setIgnoringComments(true);
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        Document doc = builder.parse(new File(filePath));

        NodeList nodes = doc.getElementsByTagName("DocumentTitle");
        NodeList nodes1 = doc.getElementsByTagName("Version");


        if (nodes1.getLength() == 1) {
            System.out.println("Elementul exista in fisierul xml");
        } else {
            Text a = doc.createTextNode("1.0");
            Element p = doc.createElement("Version");
            p.appendChild(a);

            nodes.item(0).getParentNode().insertBefore(p, nodes.item(0));

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            StreamResult result = new StreamResult(new File(filePath));
            DOMSource source = new DOMSource(doc);
            transformer.transform(source, result);

            System.out.println("Succes adding version");
        }
        /*String xmlOutput = result.getWriter().toString();
		System.out.println(xmlOutput);*/
    }

    // Parcugere recursiva de fisierie... Nu functioneaza corect inca
    public void set(String path) {
        setFilePath(path);
        File[] files = new File(path).listFiles();
        if (files == null) {
            try {
                addVersion();
            } catch (Exception e) {
                System.out.println("Fille is null. Adaugare versiune " + e.getMessage());
                // e.printStackTrace();
            }
        } else {
            path = path + "\\";
            for (File file : files) {
                if (file.isDirectory()) {
                    if (file.getName().contains(".xml")) {
                        setFilePath(path + "\\" + file.getName());
                        System.out.println("Directory: " + path + file.getName());
                    }
                } else {
                    if (file.getName().contains(".xml")) {
                        setFilePath(path + file.getName());
                        System.out.println("File: " + path + file.getName());
                    }
                }

                try {
                    addVersion();
                } catch (Exception e) {
                    System.out.println("adaugare versiune " + e.getMessage());
                    // e.printStackTrace();
                }
            }
        }
    }
}
