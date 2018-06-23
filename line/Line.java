package line;

import variable.Variable;

import java.util.ArrayList;

/**
 * Represents any type of line that can be found in the source file
 */
public interface Line {

    String getContent();

    default ArrayList<Variable> getVariables() { return null; }
}
