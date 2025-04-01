package com.ecommerce.dto;

import com.ecommerce.model.DetalleVenta;

public class DetalleVentaDTO {
    public String nombreProducto;
    public String nombreCategoria;
    public int cantidad;
    public double precioUnitario;
    public double totalDetalle;

    public DetalleVentaDTO(DetalleVenta detalleVenta) {
        this.nombreProducto = detalleVenta.getProducto().getNombre();
        this.nombreCategoria = detalleVenta.getProducto().getCategoria().getNombre();
        this.cantidad = detalleVenta.getCantidad();
        this.precioUnitario = detalleVenta.getProducto().getPrecioVenta();
        this.totalDetalle = detalleVenta.getCantidad() * precioUnitario;
    }
}
