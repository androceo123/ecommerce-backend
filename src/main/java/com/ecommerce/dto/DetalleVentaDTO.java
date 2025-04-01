package com.ecommerce.dto;

import com.ecommerce.model.DetalleVenta;

public class DetalleVentaDTO {
    public Long idProducto;
    public String nombreProducto;
    public Long idCategoria;
    public String nombreCategoria;
    public int cantidad;
    public double precioUnitario;
    public double totalDetalle;

    public DetalleVentaDTO(DetalleVenta detalleVenta) {
        this.idProducto = detalleVenta.getProducto().getIdProducto();
        this.nombreProducto = detalleVenta.getProducto().getNombre();
        this.idCategoria = detalleVenta.getProducto().getCategoria().getIdCategoria();
        this.nombreCategoria = detalleVenta.getProducto().getCategoria().getNombre();
        this.cantidad = detalleVenta.getCantidad();
        this.precioUnitario = detalleVenta.getProducto().getPrecioVenta();
        this.totalDetalle = detalleVenta.getCantidad() * precioUnitario;
    }
}
