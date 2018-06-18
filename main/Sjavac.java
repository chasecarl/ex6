package main;

public class Sjavac {

    private static final int NUMBER_OF_ARGUMENTS = 1;

    public static void main(String[] args) {
        try { checkArgs(args); }
        catch (IllegalNumberOfArgumentsException inoae) {
            System.err.println(inoae.getMessage());
        }
    }

    // TODO: SHOULD WE USE AN EXCEPTION INSTEAD?
    private static void checkArgs(String[] args) throws IllegalNumberOfArgumentsException {
        if (!(args.length == NUMBER_OF_ARGUMENTS)) throw new IllegalNumberOfArgumentsException();
    }
}
