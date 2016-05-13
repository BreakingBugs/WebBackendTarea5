package py.una.pol.web.tarea6.rest;

import py.una.pol.web.tarea6.controller.ProviderController;
import py.una.pol.web.tarea6.controller.UserController;
import py.una.pol.web.tarea6.model.Order;
import py.una.pol.web.tarea6.model.Provider;
import py.una.pol.web.tarea6.model.User;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

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
}
