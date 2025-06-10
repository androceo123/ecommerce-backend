package py.edu.fiuni.taller.rest;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import py.edu.fiuni.taller.ejb.VehiculoService;
import py.edu.fiuni.taller.model.Vehiculo;

import java.util.List;

@Path("/vehiculos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VehiculoResource {

    @EJB
    private VehiculoService vehiculoService;

    @GET
    public Response listarVehiculos() {
        List<Vehiculo> vehiculos = vehiculoService.listar();
        return Response.ok(vehiculos).build();
    }

    @POST
    public Response crearVehiculo(Vehiculo vehiculo) {
        vehiculoService.crear(vehiculo);
        return Response.status(Response.Status.CREATED).build();
    }
}

