package line;

/**
 * Singleton Factory class that creates different types of Line objects
 */
public class LineFactory {

    /** Stores an instance of a class */
    private static LineFactory instance = new LineFactory();

    private enum Pattern {
        EMPTY { String getPattern() { return "\\s*";}};

        abstract String getPattern();
    }

    /**
     * Creates a Line object according to the given string
     * @param fileString a string to create from
     * @return a new Line object of a given String
     */
    public static Line createLine(String fileString) {
        return null;
    }

    private LineFactory() {

    }

    public static LineFactory instance() { return instance; }
}
