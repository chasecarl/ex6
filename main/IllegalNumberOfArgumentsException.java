package main;

class IllegalNumberOfArgumentsException extends Exception {

    private final String message = "Illegal number of arguments. Program accepts exactly one argument that" +
            " is a path to a file";

    @Override
    public String getMessage() {
        return message;
    }
}
