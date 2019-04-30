package Validation;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import Validation.Condition.*;

public class TaskMapping {

    public static Map<String, Rule> CLASS_ACCOUNT   = new HashMap<>();
    public static Map<String, String> TEST_CLASSES          = new HashMap<>();

    static {
        List<String> fields = new ArrayList<>();
        fields.add("Amount__c");
        fields.add("Account__c");
        CLASS_ACCOUNT.put("Product__c", new sObjectRule("Product__c", fields));
        CLASS_ACCOUNT.put("AccountUtils", new ApexClassRule());


        // class **
//        List<String> methods = new LinkedList<>();
//        methods.add("accountsByState");
//        CLASS_ACCOUNT.put("AccountUtils", methods);
//        // tests: Test Class => Class
//        TEST_CLASSES.put("WebTest", "IntWebService");
    }

    public static void generateXML(){
//        for (String item : CLASS_ACCOUNT.keySet()) {
//            if(CLASS_ACCOUNT.get(item) instanceof sObject ){
//
//            }
//        }
    }



}
