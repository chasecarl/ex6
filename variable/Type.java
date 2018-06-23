package variable;

public enum Type {
    INTEGER { public String toString() { return "int"; }},
    DOUBLE { public String toString() {return "double"; }};

    public abstract String toString();
}
