package py.una.pol.web.tarea6.rest.interceptor;

import py.una.pol.web.tarea6.model.Role;

import javax.ws.rs.NameBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by codiumsa on 13/5/16.
 */
@NameBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface RequiresRole {
    Role value() default Role.UNDEFINED;
}
