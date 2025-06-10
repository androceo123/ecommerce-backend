package py.edu.fiuni.taller.rest;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import py.edu.fiuni.taller.ejb.ServicioService;
import py.edu.fiuni.taller.model.Servicio;
import py.edu.fiuni.taller.model.DetalleServicio;

import java.time.LocalDate;
import java.util.List;

@Path("/servicios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServicioResource {

    @EJB
    private ServicioService servicioService;

    @GET
    public Response listarServicios() {
        List<Servicio> servicios = servicioService.listar();
        return Response.ok(servicios).build();
    }

    @POST
    public Response crearServicio(Servicio servicio) {
        servicioService.crear(servicio);
        return Response.status(Response.Status.CREATED).build();
    }

    // 🔽 ESTE ES EL MÉTODO NUEVO PARA FILTRAR POR CLIENTE Y FECHA
    @GET
    @Path("/buscar")
    public Response buscarServicios(@QueryParam("clienteId") Long clienteId,
                                     @QueryParam("fecha") String fecha) {
        LocalDate fechaParsed = null;
        if (fecha != null && !fecha.isEmpty()) {
            fechaParsed = LocalDate.parse(fecha);
        }
        List<Servicio> resultados = servicioService.buscarPorClienteYFecha(clienteId, fechaParsed);
        return Response.ok(resultados).build();
    }
    @GET
@Path("/{id}/detalles")
public Response obtenerDetalles(@PathParam("id") Long servicioId) {
    List<DetalleServicio> detalles = servicioService.obtenerDetalles(servicioId);
    return Response.ok(detalles).build();
       }
}

