package scope;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Scope {

    //Super scope of the current scope
    private Scope parent;

    //All nearest sub scopes
    private Set<Scope> children;

    //All variables in the scope;
    //TODO change type to "variable class"
    private TreeSet<Scope> variables;

    /**
     * Creates a new scope
     * @param superScope
     */
    public Scope(Scope superScope){
        parent = superScope;
        children = new HashSet<Scope>();
        //TODO change type to "variable class"
        variables = new TreeSet<Scope>();
    }

    /**
     * Adds a sub scope to the sub scopes' set
     * @param subScope
     */
    public void addSubScope(Scope subScope){
        children.add(subScope);
    }


}
