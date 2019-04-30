package Validation.Condition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipFile;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Element;

public class Fabrica {

    public static Map<String, Map<String,Rule>> CREDI = new HashMap<>();

    public Fabrica(Document doc){

//        NodeList nodeList = doc.getElementsByTagName("names");
//        for (int i = 0; i < nodeList.getLength(); i++) {
//            Node node = nodeList.item(i);
//            Element element = (Element) node;
////            System.out.println("Имя: " + element.getElementsByTagName("userName").item(0).getTextContent());
////            System.out.println("passs: " + element.getElementsByTagName("password").item(0).getTextContent());
//            NodeList customObjects = element.getElementsByTagName("CustomObjects");
//            createCustomObjectsRules(customObjects);
//            NodeList apexClasses = element.getElementsByTagName("ApexClasses");
//            createApexClassesRules(apexClasses);
//        }

    }

//    public Map<String,Rule> createCustomObjectsRules( NodeList customObjects){
//        Map<String, Rule> rulesObjects = new HashMap<>();
//        for (int i = 0; i < customObjects.getLength(); i++){
//            Node node = customObjects.item(i);
//            Element element = (Element) node;
////            System.out.println("fullName customObjects: " + element.getElementsByTagName("fullName").item(0).getTextContent());
//            sObjectRule rulesObj = new sObjectRule(element.getElementsByTagName("fullName").item(0).getTextContent(), element);
//            rulesObjects.put(element.getElementsByTagName("fullName").item(0).getTextContent(), rulesObj);
//        }
//        return rulesObjects;
//    }
//
//        public Map<String,Rule> createApexClassesRules( NodeList apexClasses){
//            Map<String, Rule> rulesApexClasses = new HashMap<>();
//            for (int i = 0; i < apexClasses.getLength(); i++){
//                Node node = apexClasses.item(i);
//                Element element = (Element) node;
////                System.out.println("fullName apexClasses: " + element.getElementsByTagName("fullName").item(0).getTextContent());
//            }
//            return rulesApexClasses;
//        }


}
