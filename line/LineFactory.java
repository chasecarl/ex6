package line;

import java.util.regex.Matcher;

/**
 * Singleton Factory class that creates different types of Line objects
 */
public class LineFactory {

    /** Stores an instance of a class */
    private static LineFactory instance = new LineFactory();

    /** Stores all available patterns */
    private enum Pattern {
        EMPTY ("\\s*");

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
    public static Line createLine(String fileString) {
        Matcher empty = Pattern.EMPTY.pattern.matcher(fileString);
        if (empty.matches()) return new EmptyLine();
        return null;
    }

    private LineFactory() {

    }

    public static LineFactory instance() { return instance; }
}
