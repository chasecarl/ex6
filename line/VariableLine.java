package line;

/**
 * Represents a line that has a variable declaration and/or assignment
 */
public class VariableLine extends AbstractLine {

    private LineFactory.Modifier[] modifiers;
    private LineFactory.Type type;
    private boolean isInitialized;
    
    /**
     * Constructs a code line from a string
     * @param content a string from which to construct
     */
    VariableLine(String content) { super(content); }

}
