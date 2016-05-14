package py.una.pol.web.tarea6.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import py.una.pol.web.tarea6.controller.ProviderController;
import py.una.pol.web.tarea6.controller.UserController;
import py.una.pol.web.tarea6.exceptions.LoginFailException;
import py.una.pol.web.tarea6.exceptions.LogoutFailException;
import py.una.pol.web.tarea6.model.Order;
import py.una.pol.web.tarea6.model.Provider;
import py.una.pol.web.tarea6.model.User;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by codiumsa on 13/5/16.
 */
@Path("/usuarios")
public class UserService {
    @Inject
    UserController userController;

    @GET
    @Produces("application/json")
    public List<User> getUsers() {
        return userController.getUsers();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public User addUser(User newUser) {
        userController.addUser(newUser);
        return newUser;
    }


    @GET
    @Path("/{id: [0-9]*}")
    @Produces("application/json")
    public Response getUser(@PathParam("id") Integer id) {
        User p = userController.getUser(id);
        System.out.println("Usuario: " + p.getUsername());
        System.out.println("Rol: ");
        System.out.println(p.getRole());
        System.out.println(p.getRole().getValue());
        if (p != null) {
            return Response.ok(p).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/{id: [0-9]*}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response updateUser(@PathParam("id") Integer id, User updatedUser) {
        User p = userController.updateUser(id, updatedUser);
        if (p != null) {
            return Response.ok(p).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id: [0-9]*}")
    public Response removeUser(@PathParam("id") Integer id) {
        userController.removeUser(id);
        return Response.ok().build();
    }

    @POST
    @Path("/login")
    @Consumes("application/json")
    @Produces("application/json")
    public Response login(User user) {
        try {
            String at = userController.tryLogin(user);
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setAccessToken(at);
            return Response.ok().entity(loginResponse).build();
        } catch(LoginFailException l) {
            Map<String, String> msg = new HashMap<String, String>();
            msg.put("error", l.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).entity(msg).build();
        }
    }

    @POST
    @Path("/logout")
    @Produces("application/json")
    public Response logout(@HeaderParam("Authorization") String authorization) {
        if(authorization == null) {
            Map<String, String> msg = new HashMap<String, String>();
            msg.put("error", "Header Authorization no presente.");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(msg).build();
        }
        String token = authorization.substring("Bearer".length()).trim();
        try {
            userController.logout(token);
            return Response.ok().build();
        }
        catch (LogoutFailException l) {
            Map<String, String> msg = new HashMap<String, String>();
            msg.put("error", l.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(msg).build();
        }
    }


    private class LoginResponse implements Serializable {
        private static final long serialVersionUID = 1L;

        private String accessToken;

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }
    }
}
