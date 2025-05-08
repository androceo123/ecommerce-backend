package com.ecommerce.resources;

import com.ecommerce.dao.CategoriaDAO;
import com.ecommerce.model.Categoria;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/categorias")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CategoriaResource {

    @Inject
    private CategoriaDAO categoriaDAO;

    @GET
    public List<Categoria> listar() {
        return categoriaDAO.listarTodas();
    }

    @GET
    @Path("/{id}")
    public Response obtenerPorId(@PathParam("id") Long id) {
        Categoria categoria = categoriaDAO.buscarPorId(id);
        if (categoria == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(categoria).build();
    }

    @POST
    public Response crear(Categoria categoria) {
        categoriaDAO.crear(categoria);
        return Response.status(Response.Status.CREATED).entity(categoria).build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") Long id, Categoria categoria) {
        Categoria existente = categoriaDAO.buscarPorId(id);
        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        categoria.setIdCategoria(id);
        categoriaDAO.actualizar(categoria);
        return Response.ok(categoria).build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Long id) {
        Categoria existente = categoriaDAO.buscarPorId(id);
        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        categoriaDAO.eliminar(id);
        return Response.noContent().build();
    }
}