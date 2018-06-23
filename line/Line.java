package line;

/**
 * Represents any type of line that can be found in the source file
 */
public interface Line {

    String getContent();

    default boolean isInformable() { return false; }
}
