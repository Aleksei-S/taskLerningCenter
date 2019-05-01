package Validation;

import org.hamcrest.Condition;
import Validation.Condition.*;
import java.io.*;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class SalesforceHepler {

    public static long startTime = System.currentTimeMillis();

    private String tempUsername;
    private String tempPassword;

    public static String zip_file_for_read = "";
    public static String zip_file_for_CREAD = System.getProperty("user.dir") +  "/tasks.xml";
    private static Map<String, Rule> mapping = TaskMapping.METADATA_CHECK;

    public SalesforceHepler(String username, String password) {
        this.tempUsername = username;
        this.tempPassword = password;
    }

    public static void checkUsersResults() throws InterruptedException {

        if (GoogleHelper.userCreds.size() == 0) {
            System.out.println("No users creds found in Google File");
            System.exit(1);
        }

//        String firstKey = GoogleHelper.userCreds.keySet().iterator().next();
//                SalesforceHandlerThread thread = new SalesforceHandlerThread(
//                        firstKey,
//                        GoogleHelper.userCreds.get(firstKey),
//                        "User: " + firstKey // NOT LIKE THIS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//                );
//                     thread.start();

            for (String item : GoogleHelper.userCreds.keySet()) {
                SalesforceHandlerThread thread = new SalesforceHandlerThread(
                        item,
                        GoogleHelper.userCreds.get(item),
                        "User: " + item // NOT LIKE THIS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                );
                thread.start();
        }

    }

    public void processUser() {

//        try {
//            File xmlFile = new File(zip_file_for_CREAD);
//            System.out.println(" ZipFile ZipFile ZipFile ZipFile ZipFile");
//            System.out.println(zip_file_for_CREAD);
//            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//            DocumentBuilder db = dbf.newDocumentBuilder();
//            Document doc = db.parse(xmlFile);
//            // Выполнять нормализацию не обязательно, но рекомендуется
//            doc.getDocumentElement().normalize();
////            Fabrica FABRICA = new Fabrica(doc);
//
//
//        } catch (ParserConfigurationException | SAXException | IOException ex) {
//            System.out.println("ioEx.SFHelper.readZip: " + ex.getMessage());
//        }

        DeployRetrieveHelper instance = new DeployRetrieveHelper(tempUsername, tempPassword);
//        instance.getMetadata();
        instance.retrieveZip();
        checkZipFile();
    }

    private List<Results> checkZipFile() {

        List<Results> results = new ArrayList<>();
        try {
            ZipFile file = new ZipFile(zip_file_for_read);
            for (String item : mapping.keySet()) {
                Enumeration < ? extends ZipEntry > e = file.entries();
                boolean fileFound = false;
                while (e.hasMoreElements()) {
                    ZipEntry entry = e.nextElement();
                    if (entry.getName().contains(item) && !entry.getName().contains(".xml")) {
                        fileFound = true;
                        BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(entry)));
                        String allFile = "";
                        String line = null;
                        while ((line = br.readLine()) != null){
                            allFile = allFile + line;
                        }
                        results.addAll(mapping.get(item).checkCondition(allFile));
                        break;
                    }
                }
                // not found file
                if (!fileFound){
                    results.add(new Results(item, "NOT FOUND FILE " + item, Thread.currentThread().getName(), false));
                }
            }
        } catch (IOException ex) {
            System.out.println("ioEx.SFHelper.readZip: " + ex.getMessage());
        }
        for (Results res : results) {
                System.out.println(res.user + " " + res.status + " " + res.nameMetadata + " " +  res.message);
        }
        return results;
    }


    private void validateTasksByRunTests() {

        ValidateByTestHelper helper = new ValidateByTestHelper(tempUsername);

    }

}
