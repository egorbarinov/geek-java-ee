package ru.geekbrains.rest;

import ru.geekbrains.dto.UserDto;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/v1/users")
public interface UserServiceRest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<UserDto> findAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    UserDto findById(@PathParam("id") Long id);

    @GET
    @Path("/name")
    @Produces(MediaType.APPLICATION_JSON)
    UserDto findByName(@QueryParam("name") String name);

    @GET
    @Path("/count")
    Long countAll();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(UserDto userDto);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void update(UserDto userDto);

    @DELETE
    void deleteById(Long id);
}
