package py.una.pol.web.tarea6.exceptions;

/**
 * Created by codiumsa on 13/5/16.
 */
public class AuthorizationFailException extends Exception {
    public AuthorizationFailException() { super(); }
    public AuthorizationFailException(String message) { super(message); }
    public AuthorizationFailException(String message, Throwable cause) { super(message, cause); }
    public AuthorizationFailException(Throwable cause) { super(cause); }
}
