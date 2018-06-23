package processor;

import line.Line;
import variable.Type;
import variable.Variable;

import java.util.ArrayList;

public class LineProcessor {

    /** Stores an instance of a class */
    private static LineProcessor instance = new LineProcessor();

    private final String POSSESSIVE_SPACE = "\\s+";
    private final String OR = "|";
    private final String NAME = "\\D";
    private final String LEFT_ROUND_BRACKET = "(";
    private final String RIGHT_ROUND_BRACKET = ")";
    
    /** A private constructor */
    private LineProcessor() {}

    /**
     * @return an instance of a class
     */
    public static LineProcessor getInstance() { return instance; }
    
    public ArrayList<Variable> getVariables(Line line) {
        if (!line.isInformable()) { return null; }
        String content = line.getContent();
        ArrayList<Variable> result = new ArrayList<>();

        String regex = POSSESSIVE_SPACE + getAllTypesForRegexp() + POSSESSIVE_SPACE;
//        regex +=
        return null;
    }

    private String getAllTypesForRegexp() {
        String result = LEFT_ROUND_BRACKET;
        ArrayList<String> types = new ArrayList<>();
        for (Type type : Type.values()) {
            types.add(type.toString());
        }
        String core = String.join(OR, types);
        return result + core + RIGHT_ROUND_BRACKET;
    }
}
