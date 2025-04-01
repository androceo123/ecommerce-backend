package com.ecommerce.resources;

import com.ecommerce.dao.ClienteDAO;
import com.ecommerce.dao.ProductoDAO;
import com.ecommerce.dao.VentaDAO;
import com.ecommerce.dto.ClienteDTO;
import com.ecommerce.model.DetalleVenta;
import com.ecommerce.model.Producto;
import com.ecommerce.model.Venta;
import com.ecommerce.service.EmailService;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.jboss.logging.Logger;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/ventas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VentaResource {
    private static final Logger LOGGER = Logger.getLogger(VentaResource.class);

    @Inject
    private VentaDAO ventaDAO;

    @Inject
    private ProductoDAO productoDAO;

    @Inject
    private ClienteDAO clienteDAO;

    @Inject
    private EmailService emailService;

    @POST
    public Response registrarVenta(Venta newVenta) {
        // Validación y actualización de inventario
        for (DetalleVenta detalle : newVenta.getDetalles()) {
            Producto producto = productoDAO.buscarPorId(detalle.getProducto().getIdProducto());

            if (producto == null || producto.getCantidadExistente() < detalle.getCantidad()) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Producto no disponible en inventario").build();
            }

            producto.setCantidadExistente(producto.getCantidadExistente() - detalle.getCantidad());
            productoDAO.actualizar(producto);
            detalle.setVenta(newVenta);
        }

        // Registrar venta
        ventaDAO.registrarVenta(newVenta);

        // Construir correo
        String asunto = "Resumen de su compra";
        StringBuilder cuerpo = new StringBuilder();
        cuerpo.append("Gracias por su compra.\n\n")
              .append("Fecha: ").append(newVenta.getFecha()).append("\n")
              .append("Total: $").append(newVenta.getTotal()).append("\n\n")
              .append("Detalle:\n");

        for (DetalleVenta d : newVenta.getDetalles()) {
            Producto p = productoDAO.buscarPorId(d.getProducto().getIdProducto());
            cuerpo.append("- ").append(p.getNombre())
                  .append(" (").append(p.getCategoria().getNombre()).append("): ")
                  .append(d.getCantidad()).append(" x $").append(d.getPrecioUnitario())
                  .append(" = $").append(d.getTotalDetalle()).append("\n");
        }

        try {
            var cliente = clienteDAO.buscarPorId(newVenta.getCliente().getIdCliente());
            if (cliente != null && cliente.getEmail() != null && !cliente.getEmail().isBlank()) {
                emailService.enviarCorreo(cliente.getEmail(), asunto, cuerpo.toString());
            } else {
                LOGGER.warn("Correo no enviado: el cliente no tiene un email válido.");
            }
        } catch (Exception e) {
            LOGGER.error("Error al enviar correo: " + e.getMessage());
        }

        return Response.status(Response.Status.CREATED).entity(newVenta).build();
    }

    @GET
    public Response getVentas(@QueryParam("fecha") @DefaultValue("todos") String dateString, @QueryParam("cliente") @DefaultValue("-1") long clientId) {
        try {
            List<Venta> ventas;

            if (dateString.equals("todos") && clientId == -1) {
                ventas = ventaDAO.getVentas();
            } else if (!dateString.equals("todos") && clientId != -1) {
                Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
                ventas = ventaDAO.getVentas(clienteDAO.buscarPorId(clientId), fecha);
            } else if (!dateString.equals("todos")) {
                Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
                ventas = ventaDAO.getVentas(fecha);
            } else {
                ventas = ventaDAO.getVentas(clienteDAO.buscarPorId(clientId));
            }

            List<VentaDTO> resultado = ventas.stream().map(VentaDTO::new).collect(Collectors.toList());
            return Response.ok(resultado).build();
        } catch (Exception e) {
            return Response.status(400).entity("Formato de fecha inválido (usar yyyy-MM-dd)").build();
        }
    }

    public static class VentaDTO {
        public Long idVenta;
        public Date fecha;
        public Double total;
        public ClienteDTO cliente;

        public VentaDTO(Venta v) {
            this.idVenta = v.getIdVenta();
            this.fecha = v.getFecha();
            this.total = v.getTotal();
            this.cliente = new ClienteDTO(v.getCliente());
        }
    }
}
