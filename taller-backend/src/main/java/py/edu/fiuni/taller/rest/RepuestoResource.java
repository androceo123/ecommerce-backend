package py.edu.fiuni.taller.rest;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import py.edu.fiuni.taller.ejb.RepuestoService;
import py.edu.fiuni.taller.model.Repuesto;

import java.util.List;

@Path("/repuestos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RepuestoResource {

    @EJB
    private RepuestoService repuestoService;

    @GET
    public Response listarRepuestos() {
        List<Repuesto> repuestos = repuestoService.listar();
        return Response.ok(repuestos).build();
    }

    @POST
    public Response crearRepuesto(Repuesto repuesto) {
        repuestoService.crear(repuesto);
        return Response.status(Response.Status.CREATED).build();
    }
}

