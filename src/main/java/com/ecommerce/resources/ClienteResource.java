package com.ecommerce.resources;

import com.ecommerce.dao.ClienteDAO;
import com.ecommerce.model.Cliente;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {

    @Inject
    private ClienteDAO clienteDAO;

    @GET
    public List<Cliente> listar() {
        return clienteDAO.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Response obtenerPorId(@PathParam("id") Long id) {
        Cliente cliente = clienteDAO.buscarPorId(id);
        if (cliente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(cliente).build();
    }

    @POST
    public Response crear(Cliente cliente) {
        clienteDAO.crear(cliente);
        return Response.status(Response.Status.CREATED).entity(cliente).build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") Long id, Cliente cliente) {
        Cliente existente = clienteDAO.buscarPorId(id);
        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        cliente.setIdCliente(id);
        clienteDAO.actualizar(cliente);
        return Response.ok(cliente).build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Long id) {
        Cliente existente = clienteDAO.buscarPorId(id);
        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        clienteDAO.eliminar(id);
        return Response.noContent().build();
    }
}
