package com.ecommerce.resources;

import com.ecommerce.dao.ClienteDAO;
import com.ecommerce.dao.ProductoDAO;
import com.ecommerce.dao.VentaDAO;
import com.ecommerce.dto.ClienteDTO;
import com.ecommerce.model.DetalleVenta;
import com.ecommerce.model.Producto;
import com.ecommerce.model.Venta;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/ventas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VentaResource {

    @Inject
    private VentaDAO ventaDAO;

    @Inject
    private ProductoDAO productoDAO;

    @Inject ClienteDAO clienteDAO;

    @POST
    public Response registrarVenta(Venta newVenta) {
        // Establecer la venta para cada detalle ANTES de persistir
        for (DetalleVenta detalle : newVenta.getDetalles()) {
            Producto producto = productoDAO.buscarPorId(detalle.getProducto().getIdProducto());

            if (producto == null || producto.getCantidadExistente() < detalle.getCantidad()) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Producto no disponible en inventario").build();
            }

            producto.setCantidadExistente(producto.getCantidadExistente() - detalle.getCantidad());
            productoDAO.actualizar(producto);

            // Asociar el detalle con la venta ANTES de persistir
            detalle.setVenta(newVenta);
        }

        // Guardar la venta con todos sus detalles ya asociados
        ventaDAO.registrarVenta(newVenta);

        return Response.status(Response.Status.CREATED).entity(newVenta).build();
    }

    @GET
    public Response getVentas(@QueryParam("fecha") @DefaultValue("todos") String dateString, @QueryParam("cliente") @DefaultValue("-1") long clientId) {
        if (dateString.equals("todos") && clientId == -1) {
            List<Venta> ventas = ventaDAO.getVentas();
            List<VentaDTO> resultado = ventas.stream().map(VentaDTO::new).collect(Collectors.toList());
            return Response.ok(resultado).build();
        } else {
            if (!dateString.equals("todos")) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                sdf.setLenient(false); // Strict parsing
                try {
                    Date date = sdf.parse(dateString);
                    if (clientId != -1) {
                        List<Venta> ventas = ventaDAO.getVentas(clienteDAO.buscarPorId(clientId), date);
                        List<VentaDTO> resultado = ventas.stream().map(VentaDTO::new).collect(Collectors.toList());
                        return Response.ok(resultado).build();
                    } else {
                        List<Venta> ventas = ventaDAO.getVentas(date);
                        List<VentaDTO> resultado = ventas.stream().map(VentaDTO::new).collect(Collectors.toList());
                        return Response.ok(resultado).build();
                    }
                } catch (Exception e) {
                    return Response.status(400).build();
                }
            } else {
                List<Venta> ventas = ventaDAO.getVentas(clienteDAO.buscarPorId(clientId));
                List<VentaDTO> resultado = ventas.stream().map(VentaDTO::new).collect(Collectors.toList());
                return Response.ok(resultado).build();
            }
        }
    }


    private void enviarCorreo(Venta venta) {
        // TODO implementar envío de correo electrónico
        System.out.println("Enviando correo electrónico a " + venta.getCliente().getEmail());
    }

    
    // DTO interno
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