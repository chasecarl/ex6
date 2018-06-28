package line;

import component.Component;
import component.Variable;

import java.util.ArrayList;

public class IntegerVariableLine extends VariableLine {

    IntegerVariableLine(String content) { super(content);}

    IntegerVariableLine(ArrayList<String> modifiers, String type, ArrayList<String> names,
                               ArrayList<String> values) {
        super(modifiers, type, names, values);
    }

    @Override
    public ArrayList<? extends Component> extractComponents() throws IllegalLineFormatException {
        super.extractComponents();

        ArrayList<Variable<Integer>> result = new ArrayList<>();
        for (int i = 0; i < getNames().size(); i++) {
            String name = getNames().get(i);
            if (name == null) break;
            Integer value;
            try {
                value = Integer.parseInt(getValues().get(i));
            }
            catch (NumberFormatException nfe) { throw new IllegalLineFormatException(); }
            result.add(new Variable<>(getModifiers(), getType(), name, value));
        }
        return result;
    }
}
