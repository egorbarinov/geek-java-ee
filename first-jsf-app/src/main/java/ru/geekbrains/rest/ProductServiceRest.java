package ru.geekbrains.rest;

import ru.geekbrains.dto.ProductDto;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/v1/products")
public interface ProductServiceRest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductDto> findAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    ProductDto findById(@PathParam("id") Long id);

    @GET
    @Path("/name")
    @Produces(MediaType.APPLICATION_JSON)
    ProductDto findByName(@QueryParam("name") String name);

    @GET
    @Path("/category")
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductDto> findAllByCategory(@QueryParam("category_id") Long categoryId);

    @GET
    @Path("/count")
    Long countAll();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(ProductDto productDto);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void update(ProductDto productDto);

    @DELETE
    void deleteById(Long id);
}
