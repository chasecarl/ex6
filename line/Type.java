package line;

/** Represents all stored words for all data types that are supported by the program */
public enum Type {
    INTEGER {
        public String toString() { return "int"; }
        public String getTypeSpecifyDataRegex() { return "\\d++"; }
    },
    DOUBLE {
        public String toString() { return "double"; }
        public String getTypeSpecifyDataRegex() { return "(?:\\d++\\.\\d*+|\\d*+\\.\\d++)"; }
    },
    STRING {
        public String toString() { return "String"; }
        public String getTypeSpecifyDataRegex() { return "\".*\""; }
    },
    BOOLEAN {
        public String toString() { return "boolean"; }
        public String getTypeSpecifyDataRegex() { return "(?:true|false|" + INTEGER.getTypeSpecifyDataRegex()
                + "|" + DOUBLE.getTypeSpecifyDataRegex() + ")"; }
    },
    CHAR {
        public String toString() { return "char"; }
        public String getTypeSpecifyDataRegex() { return "\'.?\'"; }
    };

    public abstract String toString();
    public abstract String getTypeSpecifyDataRegex();
}
