package main;

public class Sjavac {

    private static final int NUMBER_OF_ARGUMENTS = 1;

    public static void main(String[] args) {
        if (!checkArgs(args)) return;
    }

    // TODO: SHOULD WE USE AN EXCEPTION INSTEAD?
    private static boolean checkArgs(String[] args) {
        return args.length == NUMBER_OF_ARGUMENTS;
    }
}
