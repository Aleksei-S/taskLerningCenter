package Validation.Condition;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.xml.sax.SAXException;


public class sObjectRule implements  Rule {

    public  List<Condition> conditions = new ArrayList<>();
    public String nameFile = "";

    public sObjectRule(String nameFile,  Element element){
        this.nameFile = nameFile;
        NodeList fields = element.getElementsByTagName("fields");
        for (int i = 0; i < fields.getLength(); i++){
            conditions.addAll(setFieldsRule(fields.item(i).getChildNodes()));
        }
    }
//if("HtmlTag".equals(node.getNodeName()))
//    String nodeContent=node.getAttributes().getNamedItem("car").getNodeValue()

    public List<Condition> setFieldsRule (NodeList fieldsChild){
        List<Condition> conds= new ArrayList<>();
        for (int i = 0; i < fieldsChild.getLength(); i++){
            Node node = fieldsChild.item(i);
//            Element e = (Element)node;
//            String name = e.getAttribute("name");
            if (node.getNodeType() != Node.TEXT_NODE) {
//                System.out.println("***************");
//                System.out.println(node.getNodeName() + ":" + node.getChildNodes().item(0).getTextContent());
                conds.add(new Condition( "fields",node.getNodeName(), node.getChildNodes().item(0).getTextContent()));
            }
        }
        return conds;
    }



    public Boolean checkCondition(){
        return true;
    }





}
