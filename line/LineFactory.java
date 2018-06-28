package line;

import java.util.ArrayList;
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
            public String getTypeSpecifyDataRegex() { return "(?:\\d++\\.\\d*+|\\d*+\\.\\d++)"; }
        },
        STRING {
            public String toString() { return "String"; }
            public String getTypeSpecifyDataRegex() { return "\".*\""; }
        },
        BOOLEAN {
            public String toString() { return "boolean"; }
            // TODO: REMEMBER: IT ADDS A CAPTURING GROUP
            public String getTypeSpecifyDataRegex() { return "(?:true|false|" + INTEGER.getTypeSpecifyDataRegex()
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
        for (Modifier modifier : Modifier.values()) {
            result.append(modifier);
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

    private static String getVarNameRegex() { return "_\\w++|[A-Za-z]{1}\\w*+"; }
    private static String getVarValueAssignmentRegex() {
        return "=\\s*+(" + getAllValueTypesRegex() + ")\\s*+";
    }
    private static String getVarNameAndPossibleAssignmentRegex() {
        return "(" + getVarNameRegex() + ")\\s*+(?:" + getVarValueAssignmentRegex() + ")?";
    }

    /** Stores all available patterns */
    private enum Pattern {
        EMPTY ("\\s*+"),
        COMMENT ("\\\\\\\\"),
        //TODO: CAN NAME BE int OR ANY OTHER DATA TYPE?
        /*
        Capturing groups:
        \1 - an optional final (or any other if added) modifier
        \2 - a stored word for the type
        \3 - a variable name - it can start with underscore, but then it MUST have a non-underscore char
            OR
        it starts with a non-underscore (and a non-digit) char and then all chars can be used (though they are optional)
        \4 - an optional variable assignment
        \5 - an optional name for the 2nd var
        \6 - an optional value for the 2nd var
        ends with a semicolon
         */
        VARIABLE("\\s*+(?:(" + getAllModifiersRegex() + ")\\s++)?(?:(" + getAllDataTypesRegex() +
                ")\\s++)?" + getVarNameAndPossibleAssignmentRegex() +
                "(?:,\\s*+" + getVarNameAndPossibleAssignmentRegex() + ")*+;\\s*+");

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

            ArrayList<String> modifiers = new ArrayList<>();
            modifiers.add(var.group(1));
            ArrayList<String> names = new ArrayList<>();
            names.add(var.group(3));
            names.add(var.group(5));
            ArrayList<String> values = new ArrayList<>();
            values.add(var.group(4));
            values.add(var.group(6));

            String type = var.group(2);
            if (type == null) { return new VariableAssignmentLine(names, values); }
            if (type.equals(Type.INTEGER.toString())) { return new IntegerVariableLine(modifiers, type, names, values);}
            if (type.equals(Type.DOUBLE.toString())) { return new DoubleVariableLine(modifiers, type, names, values); }
            if (type.equals(Type.STRING.toString())) { return new StringVariableLine(modifiers, type, names, values); }
            if (type.equals(Type.BOOLEAN.toString())) { return new BooleanVariableLine(modifiers, type, names, values);}
            if (type.equals(Type.CHAR.toString())) { return new CharVariableLine(modifiers, type, names, values); }
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
