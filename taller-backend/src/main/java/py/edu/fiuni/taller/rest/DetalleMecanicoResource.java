package py.edu.fiuni.taller.rest;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import py.edu.fiuni.taller.ejb.DetalleServicioService;
import py.edu.fiuni.taller.model.DetalleServicio;

import java.util.List;

@Path("/detalles-mecanico")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DetalleMecanicoResource {

    @EJB
    private DetalleServicioService detalleServicioService;

    @GET
    public Response listarDetalles() {
        List<DetalleServicio> detalles = detalleServicioService.listar();
        return Response.ok(detalles).build();
    }

    @POST
    public Response crearDetalle(DetalleServicio detalle) {
        detalleServicioService.crear(detalle);
        return Response.status(Response.Status.CREATED).build();
    }
}

