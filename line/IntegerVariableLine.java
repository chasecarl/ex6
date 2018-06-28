package line;

import java.util.ArrayList;

public class IntegerVariableLine extends VariableLine {

    IntegerVariableLine(String content) { super(content);}

    IntegerVariableLine(ArrayList<String> modifiers, String type, ArrayList<String> names,
                               ArrayList<String> values) {
        super(modifiers, type, names, values);
    }
}
