package Validation.Condition;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
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
    public  List<Condition> conditions = new LinkedList<>();
    public String nameSObject;;
    public String nameRule = "CustomObject";
    public static Map<String, List<String>> CLASS_ACCOUNT   = new HashMap<>();

    public sObjectRule(ZipEntry fileXML, ZipFile file){
        this.nameSObject = nameSObject;
//        ZipEntry entry, String className, ZipFile file
        InputStream stream = null;
        try {
            stream = file.getInputStream(fileXML);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
            Document doc = documentBuilder.parse(stream);
            // Получаем все узлы с именем "staff"
            NodeList nodeList = doc.getElementsByTagName("fields");
            for (int i = 0; i < nodeList.getLength(); i++) {
                 // Выводим информацию по каждому из найденных элементов
                     Node node = nodeList.item(i);
                     System.out.println(node.getChildNodes());
                     System.out.println("Текущий элемент: " + node.getNodeName());
                for (int k = 0; k < node.getChildNodes().getLength(); k++) {
                    System.out.println("kkkkkkkkkk");
                    System.out.println(k);
                }
//                         if (Node.ELEMENT_NODE == node.getNodeType()) {
////                             Element element = (Element) node;
////                        }
           }
//            try (BufferedReader br = new BufferedReader(new InputStreamReader(stream))) {
//                while (br.read() != -1) {
//                    String currentLine = br.readLine();
//                    if (!(currentLine == null)) {
//                            System.out.println(". >> Line: " + currentLine);
//                    }
//                }
//            } catch (IOException ex) {
//                System.out.println(Thread.currentThread().getName() + ". >> ioEx.SFHelper.checkMeth.readClass: " + ex.getMessage());
//            }
        } catch (Exception  ioEx) {
            System.out.println(Thread.currentThread().getName() + ". >> ioEx.sfHelper.checkMeth" + ioEx.getMessage());
        }






//        try {
//            // Строим объектную модель исходного XML файла
//            final File xmlFile = new File(fileXML);
//            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            DocumentBuilder db = dbf.newDocumentBuilder();
//             Document doc = db.parse(xmlFile);
//             // Выполнять нормализацию не обязательно, но рекомендуется
//             doc.getDocumentElement().normalize();
//             System.out.println("Корневой элемент: " + doc.getDocumentElement().getNodeName());
//                System.out.println("============================");
//
//             // Получаем все узлы с именем "staff"
//             NodeList nodeList = doc.getElementsByTagName("staff");
//
//                 for (int i = 0; i < nodeList.getLength(); i++) {
//                 // Выводим информацию по каждому из найденных элементов
//                     Node node = nodeList.item(i);
//                     System.out.println();
//                     System.out.println("Текущий элемент: " + node.getNodeName());
//                         if (Node.ELEMENT_NODE == node.getNodeType()) {
//                             Element element = (Element) node;
//                        }
//                 }
//             } catch (ParserConfigurationException | SAXException | IOException ex) {
//                     System.out.println(ex);
//             }
    }

    public Boolean checkCondition(){
        return true;
    }



    //    static {
//        // class **
//        List<String> methods = new LinkedList<>();
//        methods.add("accountsByState");
//        CLASS_ACCOUNT.put("AccountUtils", methods);
//
//        // tests: Test Class => Class
//        TEST_CLASSES.put("WebTest", "IntWebService");
//    }



}
