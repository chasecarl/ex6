package line;

import java.util.ArrayList;

public class StringVariableLine extends VariableLine {
    public StringVariableLine(String fileString) { super(fileString); }

    StringVariableLine(ArrayList<String> modifiers, String type, ArrayList<String> names,
                        ArrayList<String> values) {
        super(modifiers, type, names, values);
    }
}
