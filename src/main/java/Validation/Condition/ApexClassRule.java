package Validation.Condition;

import java.util.ArrayList;
import java.util.List;

public class ApexClassRule implements  Rule {

    public String nameClass;
    public List<String> stringsForSearch;

    public ApexClassRule(String name,List<String> stringsForSearch){
        this.nameClass = name;
        this.stringsForSearch = stringsForSearch;
    }

    public  List<Results>  checkCondition(String file){
        List<Results> results = new ArrayList<>();
        for (String str: stringsForSearch){
            results.add(searchString(file, str));
//                System.out.println(searchString(file, str).message);
//                System.out.println(searchString(file, str).user);
//                System.out.println(searchString(file, str).status);
//                System.out.println(searchString(file, str).nameMetadata);
        }
        return results;
    }

    public Results searchString(String file, String strSearch){
        if (file.contains(strSearch) && file != null){
            return new Results(nameClass, "Found method " + strSearch, Thread.currentThread().getName(), true);
        }
        return new Results(nameClass, "NOT Found method " + strSearch, Thread.currentThread().getName(), false);
    }

}
