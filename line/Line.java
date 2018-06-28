package line;

import component.Component;

import java.util.ArrayList;

/**
 * Represents any type of line that can be found in the source file
 */
public interface Line {

    String getContent();

    default ArrayList<? extends Component> extractComponents() throws IllegalLineFormatException {
        return null;
    }

}
