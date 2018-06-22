package parser;

import line.Line;
import line.LineFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class that parses a file into lines
 */
public class Parser {

    /** Stores an instance of a class */
    private static Parser instance = new Parser();

    /** A private constructor */
    private Parser() {}

    /**
     * @return an instance of a class
     */
    public static Parser getInstance() { return instance; }

    /**
     * Parses a given file into lines
     * @param path a path to the file
     * @return an ArrayList of Line objects
     * @throws IOException if there was an io problem
     */
    public ArrayList<Line> parseFile(String path) throws IOException {
        try (FileReader reader = new FileReader(path)) {
            BufferedReader buffered = new BufferedReader(reader);
            ArrayList<Line> result = new ArrayList<>();

            String currentLine;
            do {
                currentLine = buffered.readLine();
                if (currentLine != null) { result.add(LineFactory.getInstance().createLine(currentLine)); }
            } while (currentLine != null);
            return result;
        }
    }
}
