package component;

import line.IllegalLineFormatException;
import line.Line;

import java.util.ArrayList;

public class Extractor {

    public static ArrayList<? extends Component> extract(ArrayList<Line> lines) throws IllegalLineFormatException {
        ArrayList<? extends Component> result = new ArrayList<>();
        for (Line line : lines) {
//            result.addAll(line.extractComponents());
        }
        return null;
    }
}
