package py.una.pol.web.tarea6.exceptions;

/**
 * Created by codiumsa on 13/5/16.
 */
public class LogoutFailException extends Exception {
    public LogoutFailException() { super(); }
    public LogoutFailException(String message) { super(message); }
    public LogoutFailException(String message, Throwable cause) { super(message, cause); }
    public LogoutFailException(Throwable cause) { super(cause); }
}
