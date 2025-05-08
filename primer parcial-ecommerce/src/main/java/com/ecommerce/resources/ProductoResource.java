package com.ecommerce.resources;

import com.ecommerce.dao.ProductoDAO;
import com.ecommerce.dao.CategoriaDAO;
import com.ecommerce.model.Producto;
import com.ecommerce.model.Categoria;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/productos")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductoResource {

    @Inject
    private ProductoDAO productoDAO;

    @Inject
    private CategoriaDAO categoriaDAO;

    @GET
    public List<Producto> listar() {
        return productoDAO.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Response obtenerPorId(@PathParam("id") Long id) {
        Producto producto = productoDAO.buscarPorId(id);
        if (producto == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(producto).build();
    }

    @POST
    public Response crear(Producto producto) {
        Categoria categoria = categoriaDAO.buscarPorId(producto.getCategoria().getIdCategoria());
        if (categoria == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Categoría no encontrada").build();
        }
        producto.setCategoria(categoria);
        productoDAO.crear(producto);
        return Response.status(Response.Status.CREATED).entity(producto).build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") Long id, Producto producto) {
        Producto existente = productoDAO.buscarPorId(id);
        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Categoria categoria = categoriaDAO.buscarPorId(producto.getCategoria().getIdCategoria());
        if (categoria == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Categoría no encontrada").build();
        }
        producto.setIdProducto(id);
        producto.setCategoria(categoria);
        productoDAO.actualizar(producto);
        return Response.ok(producto).build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Long id) {
        Producto existente = productoDAO.buscarPorId(id);
        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        productoDAO.eliminar(id);
        return Response.noContent().build();
    }
}
