package com.ecommerce.resources;

import com.ecommerce.dao.DetalleVentaDAO;
import com.ecommerce.dao.ProductoDAO;
import com.ecommerce.dao.VentaDAO;
import com.ecommerce.model.DetalleVenta;
import com.ecommerce.model.Producto;
import com.ecommerce.model.Venta;
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


    private void enviarCorreo(Venta venta) {
        // TODO implementar envío de correo electrónico
        System.out.println("Enviando correo electrónico a " + venta.getEmailCliente());
    }
}