package line;

public enum Modifier {
    FINAL { public String toString() { return "final"; }};

    public abstract String toString();
}
