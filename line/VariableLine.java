package line;

import component.Component;

import java.util.ArrayList;

/**
 * Represents a line that has a variable declaration and/or assignment
 */
public class VariableLine extends AbstractLine {

    private ArrayList<String> modifiers;
    private ArrayList<String> names;
    private Type type;
    private ArrayList<String> values;
    private boolean isFinal;

    public ArrayList<String> getModifiers() { return modifiers; }
    public Type getType() { return type; }
    public ArrayList<String> getNames() { return names; }
    public ArrayList<String> getValues() { return values; }

    public ArrayList<? extends Component> extractComponents() throws IllegalLineFormatException {
        if (isFinal) {
            for (int i = 0; i < names.size(); i++) {
                String value = values.get(i);
                String name = names.get(i);
                if (value == null && name != null) throw new IllegalLineFormatException();
            }
        }
        return null;
    }

    private Type convertType(String stringType) {
        for (Type type : Type.values()) {
            if (type.toString().equals(stringType)) return type;
        }
        return null;
    }

    /**
     * Constructs a code line that contains variables from a string
     * @param content a string from which to construct
     */
    VariableLine(String content) { super(content); }

    VariableLine(ArrayList<String> modifiers, String type, ArrayList<String> names, ArrayList<String> values) {
        this.modifiers = modifiers;
        isFinal = modifiers.contains(Modifier.FINAL.toString());
        this.type = convertType(type);
        this.names = names;
        this.values = values;
    }
}
