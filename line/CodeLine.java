package line;

import variable.Type;
import variable.Variable;

import java.util.ArrayList;

/**
 * Represents a line that has code instructions
 */
public class CodeLine extends AbstractLine {

    private String possesiveSpace = "\\s+";
    private String or = "|";
    private String name = "\\D";

    /**
     * Constructs a code line from a string
     * @param content a string from which to construct
     */
    CodeLine(String content) { super(content); }

    @Override
    public ArrayList<Variable> getVariables() {
        String content = getContent();
        ArrayList<Variable> result = new ArrayList<>();

        String regex = possesiveSpace + getAllTypesForRegexp() + possesiveSpace;
//        regex +=
        return null;
    }

    private String getAllTypesForRegexp() {
        String result = "[";
        ArrayList<String> types = new ArrayList<>();
        for (Type type : Type.values()) {
            types.add(type.toString());
        }
        String core = String.join(or, types);
        return result + core + "]";
    }

}
