package py.una.pol.web.tarea6.rest.interceptor;

import py.una.pol.web.tarea6.controller.UserController;
import py.una.pol.web.tarea6.exceptions.AuthenticationFailException;
import py.una.pol.web.tarea6.exceptions.AuthorizationFailException;
import py.una.pol.web.tarea6.model.AccessToken;
import py.una.pol.web.tarea6.model.Role;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Interceptor de autenticación
 */
@RequiresRole
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
    @Inject
    UserController userController;

    @Context
    private ResourceInfo resourceInfo;

    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        String authorization = containerRequestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (authorization == null || !authorization.startsWith("Bearer")) {
            throw new NotAuthorizedException("Header Authorization no presente o no es válido.");
        }

        String token = authorization.substring("Bearer".length()).trim();
        try {
            AccessToken accessToken = userController.validate(token);

            checkAuthorization(accessToken, containerRequestContext);
        } catch (AuthenticationFailException e) {
            containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        } catch (AuthorizationFailException e) {
            containerRequestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
        }
    }

    private void checkAuthorization(AccessToken accessToken, ContainerRequestContext containerRequestContext) throws AuthorizationFailException {
        //Se obtiene el método asociado al request
        Method requestResourceMethod = resourceInfo.getResourceMethod();
        RequiresRole requiresRoleMethodAnnotation = requestResourceMethod.getAnnotation(RequiresRole.class);
        Role rol = null;
        if (requiresRoleMethodAnnotation == null) {
            //Vemos si la clase tiene la anotacion
            Class<?> requestResourceClass = resourceInfo.getResourceClass();
            RequiresRole requiresRoleClassAnnotation = requestResourceClass.getAnnotation(RequiresRole.class);
            if (requiresRoleClassAnnotation == null) {
                rol = Role.UNDEFINED;
            } else {
                rol = requiresRoleClassAnnotation.value();
            }
        } else {
            rol = requiresRoleMethodAnnotation.value();
        }

        userController.checkPermission(accessToken.getUser(), rol);
    }
}
