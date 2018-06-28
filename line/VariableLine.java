package line;

import java.util.ArrayList;

/**
 * Represents a line that has a variable declaration and/or assignment
 */
public class VariableLine extends AbstractLine {

    private ArrayList<String> modifiers;
    private ArrayList<String> names;
    private String type;
    private ArrayList<String> values;

    public ArrayList<String> getModifiers() { return modifiers; }
    public String getType() { return type; }
    public ArrayList<String> getValues() { return values; }



    /**
     * Constructs a code line that contains variables from a string
     * @param content a string from which to construct
     */
    VariableLine(String content) { super(content); }

    VariableLine() {}

    VariableLine(ArrayList<String> modifiers, String type, ArrayList<String> names, ArrayList<String> values) {
        this.modifiers = modifiers;
        this.type = type;
        this.names = names;
        this.values = values;

    }
}
