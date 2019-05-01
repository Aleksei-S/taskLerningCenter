package Validation.Condition;

import java.util.List;

public interface Rule {
    List<Results> checkCondition(String str);
}
