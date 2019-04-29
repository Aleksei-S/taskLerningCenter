package Validation.Condition;

import java.util.HashMap;
import java.util.Map;

public class Condition {
    public Map<String, Map<String, String>> nameRuleCondition = new HashMap<>();
    public Condition(String name, String key, String value){
        Map<String, String> condition = new HashMap<>();
        condition.put(key, value);
        nameRuleCondition.put( name, condition );
    }
}

