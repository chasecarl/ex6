package line;

import java.util.ArrayList;

public class BooleanVariableLine extends VariableLine {
    public BooleanVariableLine(String fileString) { super(fileString); }

    BooleanVariableLine(ArrayList<String> modifiers, String type, ArrayList<String> names,
                        ArrayList<String> values) {
        super(modifiers, type, names, values);
    }
}
