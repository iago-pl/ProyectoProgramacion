package code.exceptions;

/**
 *
 * @author a21iagopl
 */
public class MultiplePlayersException extends Exception {

    /**
     * Creates a new instance of <code>MultiplePlayersException</code> without
     * detail message.
     */
    public MultiplePlayersException() {

        super("Multiple Players Found");
    }
}
