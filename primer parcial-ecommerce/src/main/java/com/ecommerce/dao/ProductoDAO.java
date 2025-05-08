package com.ecommerce.dao;

import com.ecommerce.model.Producto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ProductoDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Producto> listarTodos() {
        return entityManager.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();
    }

    public Producto buscarPorId(Long id) {
        return entityManager.find(Producto.class, id);
    }

    @Transactional
    public void crear(Producto producto) {
        entityManager.persist(producto);
    }

    @Transactional
    public void actualizar(Producto producto) {
        entityManager.merge(producto);
    }

    @Transactional
    public void eliminar(Long id) {
        Producto producto = buscarPorId(id);
        if (producto != null) {
            entityManager.remove(producto);
        }
    }
}
