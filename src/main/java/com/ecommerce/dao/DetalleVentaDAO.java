package com.ecommerce.dao;

import com.ecommerce.model.DetalleVenta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

public class DetalleVentaDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void registrarDetalleVenta(DetalleVenta detalleVenta) {
        entityManager.persist(detalleVenta);
        entityManager.flush();
    }
}
