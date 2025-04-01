package com.ecommerce.dao;

import com.ecommerce.model.DetalleVenta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;


public class DetalleVentaDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void registrarDetalleVenta(DetalleVenta detalleVenta) {
        entityManager.persist(detalleVenta);
        entityManager.flush();
    }

    @Transactional
    public List<DetalleVenta> buscarDetallesPorVentaId(Long idVenta) {
        String jpql = "SELECT d FROM DetalleVenta d "
                        + "JOIN FETCH d.producto "
                        + "JOIN FETCH d.producto.categoria "
                        + "WHERE d.venta.idVenta = :idVenta";

        return entityManager.createQuery(jpql, DetalleVenta.class)
                           .setParameter("idVenta", idVenta)
                           .getResultList();
    }
}
