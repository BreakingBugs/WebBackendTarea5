package py.una.pol.web.tarea6.exceptions;

import javax.ejb.ApplicationException;

/**
 * Created by codiumsa on 12/3/16.
 */
@ApplicationException(rollback = true)
public class OutOfStockException extends Exception {
}
