package Validation;

import org.hamcrest.Condition;
import Validation.Condition.*;
import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SalesforceHepler {

    public static long startTime = System.currentTimeMillis();

    private String tempUsername;
    private String tempPassword;

    public static String zip_file_for_read = "";
    public static String zip_file_for_CREAD = System.getProperty("user.dir") +  "/tasks.xml";
    private static Map<String, List<String>> mapping = TaskMapping.CLASS_ACCOUNT;

    public SalesforceHepler(String username, String password) {
        this.tempUsername = username;
        this.tempPassword = password;
    }

    public static void checkUsersResults() throws InterruptedException {

        if (GoogleHelper.userCreds.size() == 0) {
            System.out.println("No users creds found in Google File");
            System.exit(1);
        }


        String firstKey = GoogleHelper.userCreds.keySet().iterator().next();
                SalesforceHandlerThread thread = new SalesforceHandlerThread(
                        firstKey,
                        GoogleHelper.userCreds.get(firstKey),
                        "User: " + firstKey // NOT LIKE THIS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                );
                     thread.start();

//            for (String item : GoogleHelper.userCreds.keySet()) {
//                SalesforceHandlerThread thread = new SalesforceHandlerThread(
//                        item,
//                        GoogleHelper.userCreds.get(item),
//                        "User: " + item // NOT LIKE THIS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//                );
//                thread.start();
//        }
    }

    public void processUser() {

        try {
            File xmlFile = new File(zip_file_for_CREAD);
            System.out.println(" ZipFile ZipFile ZipFile ZipFile ZipFile");
            System.out.println(zip_file_for_CREAD);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(xmlFile);
            // Выполнять нормализацию не обязательно, но рекомендуется
            doc.getDocumentElement().normalize();
            Fabrica FABRICA = new Fabrica(doc);


        } catch (ParserConfigurationException | SAXException | IOException ex) {
            System.out.println("ioEx.SFHelper.readZip: " + ex.getMessage());
        }

        DeployRetrieveHelper instance = new DeployRetrieveHelper(tempUsername, tempPassword);
//        instance.getMetadata();
//        instance.retrieveZip();
//        readZipFile();
    }

    private void readZipFile() {

        try {

            ZipFile file = new ZipFile(zip_file_for_read);

            for (ZipEntry e : Collections.list(file.entries())) {
                System.out.println("eeee");
                System.out.println(e);






//                sObjectRule ff = new sObjectRule(e, file);
//                for (String item : mapping.keySet()) {
//                    if (e.getName().contains(item) && !e.getName().contains(".xml")) {
//                        System.out.println(Thread.currentThread().getName() + ". >> Found class: " + item);
//
//                        checkMethodsInFile(e, item, file);
//                    }
//
//
//
//
//                }
            }

        } catch (IOException ex) {
            System.out.println("ioEx.SFHelper.readZip: " + ex.getMessage());
        }
    }

    private void checkMethodsInFile(ZipEntry entry, String className, ZipFile file) {

        InputStream stream = null;

        try {
            stream = file.getInputStream(entry);

            try (BufferedReader br = new BufferedReader(new InputStreamReader(stream))) {

                while (br.read() != -1) {

                    String currentLine = br.readLine();

                    if (!(currentLine == null)) {

                        for (String item : mapping.get(className)) {

                            if (currentLine.contains(item) && currentLine != null) {
                                System.out.println(Thread.currentThread().getName() + ". >> Found Method: " + item);
                                System.out.println(Thread.currentThread().getName() + ". >> Line: " + currentLine);

                                validateTasksByRunTests();
                            }
                        }
                    }
                }

            } catch (IOException ex) {
                System.out.println(Thread.currentThread().getName() + ". >> ioEx.SFHelper.checkMeth.readClass: " + ex.getMessage());
            }

        } catch (IOException ioEx) {
            System.out.println(Thread.currentThread().getName() + ". >> ioEx.sfHelper.checkMeth" + ioEx.getMessage());
        } finally {

            try {
                stream.close();
            } catch (IOException ex) {
                System.out.println(Thread.currentThread().getName() + ". >> Exception in closing ZipEntry Stream");
            }

        }
    }

    private void validateTasksByRunTests() {

        ValidateByTestHelper helper = new ValidateByTestHelper(tempUsername);

    }

}
