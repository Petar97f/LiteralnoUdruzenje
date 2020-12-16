package upp.backend.model;

import java.util.HashMap;
import java.util.Map;

public enum GenresEnum {
    ;
    private GenresEnum(String label) {
        this.label = label;
    }

    public final String label;

    // ... enum values

    private static final Map<String, GenresEnum> BY_LABEL = new HashMap<>();




    static {
        for (GenresEnum e: values()) {
            BY_LABEL.put(e.label, e);
        }
    }

    // ... fields, constructor, methods

    public static GenresEnum valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }
}