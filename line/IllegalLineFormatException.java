package line;

public class IllegalLineFormatException extends Exception {

    private static final long serialVersionUID = 1L;
    private final String MESSAGE = "Illegal line format";

    @Override
    public String getMessage() { return MESSAGE; }
}
