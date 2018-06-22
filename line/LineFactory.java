package line;

import java.util.regex.Matcher;

/**
 * Factory class that creates different types of Line objects
 */
public class LineFactory {

    /** Stores an instance of a class */
    private static LineFactory instance = new LineFactory();

    /** Stores all available patterns */
    private enum Pattern {
        EMPTY ("\\s+"),
        COMMENT ("^\\\\\\\\.*"); // do we need to add .* at the end?

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
        if (comment.matches()) return new CommentLine(fileString);
        return new CodeLine(fileString);
    }

    /** A private constructor */
    private LineFactory() {}

    /**
     * @return an instance of a class
     */
    public static LineFactory getInstance() { return instance; }
}
