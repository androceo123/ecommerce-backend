package com.ecommerce.resources;

import com.ecommerce.dao.ClienteDAO;
import com.ecommerce.model.Cliente;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import jakarta.ws.rs.ext.Provider;


@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {

    @Inject
    private ClienteDAO clienteDAO;

    @GET
    public Response listar(@QueryParam("apellido") String apellido, @QueryParam("cedula") String cedula) {
        List<Cliente> clientes = clienteDAO.buscarConFiltros(apellido, cedula);
        List<ClienteDTO> resultado = clientes.stream().map(ClienteDTO::new).collect(Collectors.toList());
        return Response.ok(resultado).build();
    }

    @GET
    @Path("/{id}")
    public Response obtenerPorId(@PathParam("id") Long id) {
        Cliente cliente = clienteDAO.buscarPorId(id);
        if (cliente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse("Cliente no encontrado")).build();
        }
        return Response.ok(new ClienteDTO(cliente)).build();
    }

    @POST
    public Response crear(@Valid Cliente cliente) {
        if (clienteDAO.existePorCedula(cliente.getCedula())) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(new ErrorResponse("Ya existe un cliente con esa cédula")).build();
        }
        if (clienteDAO.existePorEmail(cliente.getEmail())) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(new ErrorResponse("Ya existe un cliente con ese email")).build();
        }
        clienteDAO.crear(cliente);
        return Response.status(Response.Status.CREATED).entity(new ClienteDTO(cliente)).build();
    }

    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") Long id, @Valid Cliente cliente) {
        Cliente existente = clienteDAO.buscarPorId(id);
        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse("Cliente no encontrado")).build();
        }
        cliente.setIdCliente(id);
        clienteDAO.actualizar(cliente);
        return Response.ok(new ClienteDTO(cliente)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Long id) {
        Cliente existente = clienteDAO.buscarPorId(id);
        if (existente == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse("Cliente no encontrado")).build();
        }
        clienteDAO.eliminar(id);
        return Response.noContent().build();
    }

    // DTO interno
    public static class ClienteDTO {
        public Long id;
        public String nombre;
        public String apellido;
        public String cedula;
        public String email;

        public ClienteDTO(Cliente c) {
            this.id = c.getIdCliente();
            this.nombre = c.getNombre();
            this.apellido = c.getApellido();
            this.cedula = c.getCedula();
            this.email = c.getEmail();
        }
    }

    // Error personalizado
    public static class ErrorResponse {
        public String mensaje;
        public ErrorResponse(String mensaje) {
            this.mensaje = mensaje;
        }
    }

    // Manejo global para errores de validación
    @Provider
    public static class ConstraintViolationMapper implements jakarta.ws.rs.ext.ExceptionMapper<ConstraintViolationException> {
        @Override
        public Response toResponse(ConstraintViolationException exception) {
            Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
            List<String> errores = violations.stream()
                    .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                    .collect(Collectors.toList());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(errores)
                    .build();
        }
    }
}
