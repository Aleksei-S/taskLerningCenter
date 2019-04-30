package Validation.Condition;

import java.util.zip.ZipEntry;
import java.util.List;

public interface Rule {
    List<Results> checkCondition(ZipEntry e);
}
