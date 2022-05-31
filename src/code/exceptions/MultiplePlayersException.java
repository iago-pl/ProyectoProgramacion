package code.exceptions;

import code.transform.Vector2;

/**
 *
 * @author Rebeca Noya y Iago Pena
 */
public class MultiplePlayersException extends Exception {

    /**
     * Creates a new instance of <code>MultiplePlayersException</code> without
     * detail message.
     */
    public MultiplePlayersException(Vector2 temp) {

        super("Multiple Player Found on x = " + temp.x + " and y = " + temp.y);
    }
}
