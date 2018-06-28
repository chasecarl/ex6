package component;

import line.Modifier;
import line.Type;

import java.util.ArrayList;

public class Variable<T> implements Component {

    private ArrayList<String> modifiers;
    private line.Type type;
    private String name;
    private T value;

    public ArrayList<String> getModifiers() {
        return modifiers;
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) { this.value = value; }

    public Variable(ArrayList<String> modifiers, Type type, String name, T value) {
        this.modifiers = modifiers;
        this.type = type;
        this.name = name;
        this.value = value;
    }
}
