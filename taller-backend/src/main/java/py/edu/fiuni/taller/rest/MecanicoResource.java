package py.edu.fiuni.taller.rest;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import py.edu.fiuni.taller.ejb.MecanicoService;
import py.edu.fiuni.taller.model.Mecanico;

import java.util.List;

@Path("/mecanicos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MecanicoResource {

    @EJB
    private MecanicoService mecanicoService;

    @GET
    public Response listarMecanicos() {
        List<Mecanico> mecanicos = mecanicoService.listar();
        return Response.ok(mecanicos).build();
    }

    @POST
    public Response crearMecanico(Mecanico mecanico) {
        mecanicoService.crear(mecanico);
        return Response.status(Response.Status.CREATED).build();
    }
}

