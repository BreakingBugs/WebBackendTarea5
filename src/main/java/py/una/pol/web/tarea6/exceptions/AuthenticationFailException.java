package py.una.pol.web.tarea6.exceptions;

/**
 * Created by codiumsa on 13/5/16.
 */
public class AuthenticationFailException extends Exception {
    public AuthenticationFailException() { super(); }
    public AuthenticationFailException(String message) { super(message); }
    public AuthenticationFailException(String message, Throwable cause) { super(message, cause); }
    public AuthenticationFailException(Throwable cause) { super(cause); }
}
