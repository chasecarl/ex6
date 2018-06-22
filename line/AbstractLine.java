package line;

public abstract class AbstractLine implements Line {

    /** Stores a content of a Line */
    private String content;

    /**
     * Constructs an empty line from a string
     * @param content a string from which to construct
     */
    AbstractLine(String content) {
        this.content = content;
    }

    /**
     * @return a content of a line
     */
    @Override
    public String getContent() { return content; }

}
