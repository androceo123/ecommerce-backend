package com.ecommerce.service;

import com.ecommerce.dao.DetalleVentaDAO;
import com.ecommerce.dto.DetalleVentaDTO;
import com.ecommerce.model.DetalleVenta;
import jakarta.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class DetalleVentaService {

    @Inject
    private DetalleVentaDAO detalleVentaDAO;

    // Método para convertir entidades a DTOs
    public List<DetalleVentaDTO> obtenerDetallesVenta(Long idVenta) {
        List<DetalleVenta> detalles = detalleVentaDAO.buscarDetallesPorVentaId(idVenta);
        return detalles.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    private DetalleVentaDTO convertirADTO(DetalleVenta detalle) {
        return new DetalleVentaDTO(
                detalle.getProducto().getNombre(),
                detalle.getProducto().getCategoria().getNombre(),
                detalle.getCantidad(),
                detalle.getPrecioUnitario()
        );
    }
}
