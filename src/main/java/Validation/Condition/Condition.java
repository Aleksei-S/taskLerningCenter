package Validation.Condition;

import java.util.HashMap;
import java.util.Map;

public class Condition {
    String name;
    public  Map<String, String> matchCondition = new HashMap<>();
    public Condition(String name, String key, String value){
        this.name = name;
        matchCondition.put(key, value);
    }
}

