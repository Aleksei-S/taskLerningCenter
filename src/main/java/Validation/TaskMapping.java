package Validation;

import java.io.File;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import java.io.StringWriter;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import Validation.Condition.*;

public class TaskMapping {

    public static Map<String, Rule> METADATA_CHECK   = new HashMap<>();
    public static Map<String, String> TEST_CLASSES   = new HashMap<>();
    public static String VERSION  = "45.0";

    static {
        List<String> fields = new ArrayList<>();
        fields.add("Amount__c");
        fields.add("Account__c");
        fields.add("zzt__c");
        METADATA_CHECK.put("Product__c.object", new sObjectRule("Product__c", fields));
        METADATA_CHECK.put("AccountUtils.cls", new ApexClassRule( "AccountUtils", Arrays.asList("accountsByState")));

        // tests: Test Class => Class
        TEST_CLASSES.put("WebTest", "IntWebService");
    }

    public static void generatePackageXML(){
        try {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        // root elements
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("Package");
        doc.appendChild(rootElement);
        Attr attr = doc.createAttribute("xmlns");
        attr.setValue("http://soap.sforce.com/2006/04/metadata");
        rootElement.setAttributeNode(attr);
            // types
            Map<String, List<String>> metadataMembers = createMapForXML();
            for (String item :metadataMembers.keySet()) {
                Element types = doc.createElement("types");
                rootElement.appendChild(types);
                if (metadataMembers.get(item).size() > 0){
                    for(String m :metadataMembers.get(item)){
                        Element members = doc.createElement("members");
                        members.appendChild(doc.createTextNode(m));
                        types.appendChild(members);
                    }
                    Element nameMembers = doc.createElement("name");
                    nameMembers.appendChild(doc.createTextNode(item));
                    types.appendChild(nameMembers);
                }
            }
        Element version = doc.createElement("version");
        version.appendChild(doc.createTextNode(VERSION));
        rootElement.appendChild(version);

// save XML
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
//            StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(doc), new StreamResult(new File(System.getProperty("user.dir") +  "/package.xml")));
//            System.out.println(writer.getBuffer().toString());
        } catch (ParserConfigurationException | TransformerException e ) {
            e.printStackTrace();
        }
    }


    private static Map<String, List<String>> createMapForXML(){
        Map<String, List<String>> results = new HashMap<>();
        List<String> membersSobject = new ArrayList<>();
        List<String> membersApexClass = new ArrayList<>();
        for (String item : METADATA_CHECK.keySet()) {
            if (METADATA_CHECK.get(item) instanceof sObjectRule){
                String member = item.substring(0, item.indexOf('.'));
                membersSobject.add(member);
            } else if (METADATA_CHECK.get(item) instanceof ApexClassRule){
                String member = item.substring(0, item.indexOf('.'));
                membersApexClass.add(member);
            }
        }
        results.put("CustomObject", membersSobject);
        results.put("ApexClass", membersApexClass);
        return results;
    }

    public static void deleteFiles(){

    }


}
