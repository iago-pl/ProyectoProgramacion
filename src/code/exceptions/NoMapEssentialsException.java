package code.exceptions;

/**
 *
 * @author Rebeca Noya y Iago Pena
 */
public class NoMapEssentialsException extends Exception {

    public NoMapEssentialsException() {
        super("No Player or Flag found");
    }
}
