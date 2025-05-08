package com.ecommerce.resources;

import com.ecommerce.dao.DetalleVentaDAO;
import com.ecommerce.dao.VentaDAO;
import com.ecommerce.dto.DetalleVentaDTO;
import com.ecommerce.model.DetalleVenta;
import com.ecommerce.model.Venta;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

import org.jboss.logging.Logger;

@Path("/ventas")
@Produces(MediaType.APPLICATION_JSON)
public class DetalleVentaResource {
    private static final Logger LOGGER = Logger.getLogger(VentaResource.class);

    @Inject
    private DetalleVentaDAO detalleVentaDAO;

    @Inject
    private VentaDAO ventaDAO;

    @GET
    @Path("/{idVenta}/detalles")
    public Response obtenerDetallesVenta(@PathParam("idVenta") Long idVenta) {
        Venta venta = ventaDAO.buscarPorId(idVenta);
        List<DetalleVenta> detalles = detalleVentaDAO.buscarDetallesPorVenta(venta);
        LOGGER.trace("Encontrado " + detalles.size() + " detalles para la venta " + venta.getIdVenta());
        List<DetalleVentaDTO> resultado = detalles.stream().map(DetalleVentaDTO::new).collect(Collectors.toList());
        LOGGER.trace("Creado lista de " + detalles.size() + " detalles");
        return Response.ok(resultado).build();
    }
}