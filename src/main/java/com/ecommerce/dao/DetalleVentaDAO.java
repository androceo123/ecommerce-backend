package com.ecommerce.dao;

import com.ecommerce.model.DetalleVenta;
import com.ecommerce.model.Venta;

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
    public List<DetalleVenta> buscarDetallesPorVenta(Venta venta) {
        String jpql = "SELECT d FROM DetalleVenta d ";
                        // + "WHERE d.venta = :venta";

        return entityManager.createQuery(jpql, DetalleVenta.class)
                        //    .setParameter("venta", venta)
                           .getResultList();
    }
}
