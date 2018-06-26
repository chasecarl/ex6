package line;

import java.util.regex.Matcher;

/**
 * Factory class that creates different types of Line objects
 */
public class LineFactory {

    /** Stores an instance of a class */
    private static LineFactory instance = new LineFactory();

    /** Represents a delimiter that is used by the regular expressions */
    private final static String REGEX_DELIMITER = "|";

    /** Represents all stored words for all data types that are supported by the program */
    private enum Type {
        INTEGER { public String toString() { return "int"; }},
        DOUBLE { public String toString() { return "double"; }},
        STRING { public String toString() { return "String"; }},
        BOOLEAN { public String toString() { return "boolean"; }},
        CHAR { public String toString() { return "char"; }};

        public abstract String toString();
    }

    /**
     * @return a String concatenation of stored words for all the data types that are supported by the program
     */
    private static String getAllTypesForRegex() {
        StringBuilder result = new StringBuilder();
        for (Type type : Type.values()) {
            result.append(type);
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
        VARIABLE("\\s*+(final\\s++)?(" + getAllTypesForRegex() +
                ")\\s++(_[\\w]++|[A-Za-z]{1}\\w*+)\\s*+(\\s*+=\\s*+\\d++\\s*+)?;\\s*+");

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
    public Line createLine(String fileString) {
        Matcher empty = Pattern.EMPTY.pattern.matcher(fileString);
        if (empty.matches()) return new EmptyLine(fileString);
        Matcher comment = Pattern.COMMENT.pattern.matcher(fileString);
        if (comment.lookingAt()) return new CommentLine(fileString);
        Matcher var = Pattern.VARIABLE.pattern.matcher(fileString);
        // TODO: ADD A SWITCH CASE
        if (var.matches()) return new IntegerVariableLine(fileString);
        return new CodeLine(fileString);
    }

    /** A private constructor */
    private LineFactory() {}

    /**
     * @return an instance of a class
     */
    public static LineFactory getInstance() { return instance; }
}
