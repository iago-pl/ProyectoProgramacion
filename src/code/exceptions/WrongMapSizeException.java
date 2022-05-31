package code.exceptions;

/**
 *
 * @author Rebeca Noya y Iago Pena
 */
public class WrongMapSizeException extends Exception {

    public WrongMapSizeException() {
        super("Wrong Map Size");
    }
    
    public WrongMapSizeException(int position) {
        super("Wrong Map Size on line " + position);
    }
    
    public WrongMapSizeException(String msg) {
        super(msg);
    }
}
