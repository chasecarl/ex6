package main;

import line.Line;
import parser.Parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Sjavac {

    /** Stores number of arguments that program accepts */
    private static final int NUMBER_OF_ARGUMENTS = 1;

    public static void main(String[] args) {
        try {
            checkArgs(args);
            Parser parser = Parser.getInstance();
            String currentPath = new File("").getAbsolutePath();
            String filePath = currentPath.concat(args[0]);
            System.out.println(filePath);
            ArrayList<Line> lines = parser.parseFile(filePath);
            for (Line line : lines) {
                System.out.println(line.getContent());
            }
        }
        catch (IllegalNumberOfArgumentsException inoae) {
            System.err.println(inoae.getMessage());
            return;
        }
        catch (IOException ioe) {
            System.err.println(ioe.getMessage());
            return;
        }
    }

    /**
     * Checks number of arguments given to the program
     * @param args arguments given to the program (an array of Strings)
     * @throws IllegalNumberOfArgumentsException iff there is more or less than 1 argument
     */
    private static void checkArgs(String[] args) throws IllegalNumberOfArgumentsException {
        if (!(args.length == NUMBER_OF_ARGUMENTS)) throw new IllegalNumberOfArgumentsException();
    }
}
