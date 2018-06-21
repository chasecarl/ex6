package main;

public class Sjavac {

    /** Stores number of arguments that program accepts */
    private static final int NUMBER_OF_ARGUMENTS = 1;

    public static void main(String[] args) {
        try { checkArgs(args); }
        catch (IllegalNumberOfArgumentsException inoae) {
            System.err.println(inoae.getMessage());
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
