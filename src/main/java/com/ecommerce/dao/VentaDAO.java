package com.ecommerce.dao;

import com.ecommerce.model.Venta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

public class VentaDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void registrarVenta(Venta venta) {
        entityManager.persist(venta);
        entityManager.flush();
    }

    // crear un método para obtener una venta por su ID
    @Transactional
    public Venta buscarPorId(Long idVenta) {
        return entityManager.find(Venta.class, idVenta);
    }
}