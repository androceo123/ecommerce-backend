package py.edu.fiuni.taller.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import py.edu.fiuni.taller.ejb.ClienteService;
import py.edu.fiuni.taller.model.Cliente;

import java.util.List;

@Path("/clientes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteResource {

    @Inject
    private ClienteService clienteService;

    @POST
    public void crear(Cliente cliente) {
        clienteService.crear(cliente);
    }

    @GET
    public List<Cliente> listar() {
        return clienteService.listar();
    }

    @GET
    @Path("/{id}")
    public Cliente obtener(@PathParam("id") Long id) {
        return clienteService.encontrar(id);
    }

    @PUT
    public void actualizar(Cliente cliente) {
        clienteService.actualizar(cliente);
    }

    @DELETE
    @Path("/{id}")
    public void eliminar(@PathParam("id") Long id) {
        clienteService.eliminar(id);
    }
}

