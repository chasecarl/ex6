package line;

import java.util.regex.Matcher;

/**
 * Factory class that creates different types of Line objects
 */
public class LineFactory {

    /** Represents a delimiter that is used by the regular expressions */
    private final static String REGEX_DELIMITER = "|";

    /** Stores an instance of a class */
    private static LineFactory instance = new LineFactory();

    /** Represents all stored words for all data types that are supported by the program */
    enum Type {
        INTEGER {
            public String toString() { return "int"; }
            public String getTypeSpecifyDataRegex() { return "\\d++"; }
        },
        DOUBLE {
            public String toString() { return "double"; }
            public String getTypeSpecifyDataRegex() { return "\\d*+\\.d*+"; }
        },
        STRING {
            public String toString() { return "String"; }
            public String getTypeSpecifyDataRegex() { return "\".*+\""; }
        },
        BOOLEAN {
            public String toString() { return "boolean"; }
            // TODO: REMEMBER: IT ADDS A CAPTURING GROUP
            public String getTypeSpecifyDataRegex() { return "(true|false|" + INTEGER.getTypeSpecifyDataRegex()
                    + REGEX_DELIMITER + DOUBLE.getTypeSpecifyDataRegex() + ")"; }
        },
        CHAR {
            public String toString() { return "char"; }
            public String getTypeSpecifyDataRegex() { return "\'.?\'"; }
        };

        public abstract String toString();
        public abstract String getTypeSpecifyDataRegex();
    }

    enum Modifier {
        FINAL { public String toString() { return "final"; }};

        public abstract String toString();
    }

    private static String getAllModifiersRegex() {
        StringBuilder result = new StringBuilder();
        for (Type type : Type.values()) {
            result.append(type);
            result.append(REGEX_DELIMITER);
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    /**
     * @return a String concatenation of stored words for all the data types that are supported by the program
     * (WITHOUT round brackets)
     */
    private static String getAllDataTypesRegex() {
        StringBuilder result = new StringBuilder();
        for (Type type : Type.values()) {
            result.append(type);
            result.append(REGEX_DELIMITER);
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    /**
     *
     * @return a String concatenation of regexes where each regex corresponds to a certain data value format
     * of an appropriate type (WITHOUT round brackets)
     */
    private static String getAllValueTypesRegex() {
        StringBuilder result = new StringBuilder();
        for (Type type : Type.values()) {
            result.append(type.getTypeSpecifyDataRegex());
            result.append(REGEX_DELIMITER);
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    /** Stores all available patterns */
    private enum Pattern {
        EMPTY ("\\s*+"),
        COMMENT ("\\\\\\\\"),
        //TODO: CAN NAME BE int OR ANY OTHER DATA TYPE?
        /*
        Capturing groups:
        \1 - an optional final modifier
        \2 - a stored word for the type
        \3 - a variable name - it can start with underscore, but then it MUST have a non-underscore char
            OR
        it starts with a non-underscore (and a non-digit) char and then all chars can be used (though they are optional)
        \4 - an optional variable assignment (an equals sign and digits)
        ends with a semicolon
         */
        VARIABLE("\\s*+(" + getAllModifiersRegex() + "\\s++)?((" + getAllDataTypesRegex() +
                ")\\s++)?(_[\\w]++|[A-Za-z]{1}\\w*+)\\s*+(\\s*+=\\s*+" + getAllValueTypesRegex() +"\\s*+)?;\\s*+");

        private final java.util.regex.Pattern pattern;

        Pattern(String stringPattern) {
            this.pattern = java.util.regex.Pattern.compile(stringPattern);
        }
    }

    /**
     * Creates a Line object according to the given string
     * @param fileString a string to create from
     * @return a new Line object of a given String
     */
    public Line createLine(String fileString) { //throws IllegalLineFormatException {
        Matcher empty = Pattern.EMPTY.pattern.matcher(fileString);
        if (empty.matches()) return new EmptyLine(fileString);
        Matcher comment = Pattern.COMMENT.pattern.matcher(fileString);
        if (comment.lookingAt()) return new CommentLine(fileString);
        Matcher var = Pattern.VARIABLE.pattern.matcher(fileString);
        if (var.matches()) {
            String type = var.group(3);
            if (type == null) { return new VariableAssignmentLine(fileString); }
            if (type.equals(Type.INTEGER.toString())) { return new IntegerVariableLine(fileString); }
            if (type.equals(Type.DOUBLE.toString())) { return new DoubleVariableLine(fileString); }
            if (type.equals(Type.STRING.toString())) { return new StringVariableLine(fileString); }
            if (type.equals(Type.BOOLEAN.toString())) { return new BooleanVariableLine(fileString); }
            if (type.equals(Type.CHAR.toString())) { return new CharVariableLine(fileString); }
        }
        // TODO: UNCOMMENT THIS
//        throw new IllegalLineFormatException();
        return new BrokenLine(fileString);
    }

    /** A private constructor */
    private LineFactory() { }

    /**
     * @return an instance of a class
     */
    public static LineFactory getInstance() { return instance; }
}
