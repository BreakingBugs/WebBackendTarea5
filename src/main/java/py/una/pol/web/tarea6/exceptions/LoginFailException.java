package py.una.pol.web.tarea6.exceptions;

/**
 * Created by codiumsa on 13/5/16.
 */
public class LoginFailException extends Exception {
    public LoginFailException() { super(); }
    public LoginFailException(String message) { super(message); }
    public LoginFailException(String message, Throwable cause) { super(message, cause); }
    public LoginFailException(Throwable cause) { super(cause); }
}
