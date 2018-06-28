package line;

import java.util.ArrayList;

public class DoubleVariableLine extends VariableLine {

    public DoubleVariableLine(String fileString) { super(fileString); }

    DoubleVariableLine(ArrayList<String> modifiers, String type, ArrayList<String> names,
                        ArrayList<String> values) {
        super(modifiers, type, names, values);
    }
}
