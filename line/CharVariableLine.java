package line;

import java.util.ArrayList;

public class CharVariableLine extends VariableLine {
    public CharVariableLine(String fileString) { super(fileString); }

    CharVariableLine(ArrayList<String> modifiers, String type, ArrayList<String> names,
                        ArrayList<String> values) {
        super(modifiers, type, names, values);
    }
}
