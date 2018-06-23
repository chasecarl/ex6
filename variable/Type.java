package variable;

public enum Type {
    INTEGER { public String toString() { return "int"; }};

    public abstract String toString();
}
