package main;

class IllegalNumberOfArgumentsException extends Exception {

    private static final long serialVersionUID = 1L;
    private final String MESSAGE = "Illegal number of arguments. Program accepts exactly one argument that" +
            " is a path to a file";

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
