package com.ecommerce.resources;

import com.ecommerce.dto.DetalleVentaDTO;
import com.ecommerce.service.DetalleVentaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/ventas")
@Produces(MediaType.APPLICATION_JSON)
public class DetalleVentaResource {

    @Inject
    private DetalleVentaService detalleVentaService;

    @GET
    @Path("/{idVenta}/detalles")
    public Response obtenerDetallesVenta(@PathParam("idVenta") Long idVenta) {
        List<DetalleVentaDTO> detalles = detalleVentaService.obtenerDetallesVenta(idVenta);
        return Response.ok(detalles).build();
    }
}