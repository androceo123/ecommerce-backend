package com.ecommerce.dto;

public class DetalleVentaDTO {
    private String nombreProducto;
    private String nombreCategoria;
    private int cantidad;
    private double precioUnitario;
    private double totalDetalle;

    public DetalleVentaDTO(String nombreProducto, String nombreCategoria, int cantidad, double precioUnitario) {
        this.nombreProducto = nombreProducto;
        this.nombreCategoria = nombreCategoria;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.totalDetalle = cantidad * precioUnitario;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public double getTotalDetalle() {
        return totalDetalle;
    }

}
