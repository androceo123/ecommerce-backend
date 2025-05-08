package com.ecommerce.dao;

import java.util.List;

import com.ecommerce.model.Venta;
import com.ecommerce.model.Cliente;
import com.ecommerce.model.DetalleVenta;

import java.util.Date;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
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

    public List<Venta> getVentas() {
        return entityManager.createQuery("SELECT v FROM Venta v", Venta.class).getResultList();
    }

    public List<Venta> getVentas(Cliente cliente) {
        String jpql = "SELECT v FROM Venta v "
                        + "WHERE v.cliente = :cliente";
        TypedQuery<Venta> query = entityManager.createQuery(jpql, Venta.class);
        query.setParameter("cliente", cliente);
        return query.getResultList();
    }

    public List<Venta> getVentas(Date fecha) {
        String jpql = "SELECT v FROM Venta v "
                        + "WHERE v.fecha = :fecha";
        TypedQuery<Venta> query = entityManager.createQuery(jpql, Venta.class);
        query.setParameter("fecha", fecha);
        return query.getResultList();
    }

    public List<Venta> getVentas(Cliente cliente, Date fecha) {
        String jpql = "SELECT v FROM Venta v "
                        + "WHERE v.fecha = :fecha "
                        + "AND v.cliente = :cliente";
        TypedQuery<Venta> query = entityManager.createQuery(jpql, Venta.class);
        query.setParameter("fecha", fecha);
        query.setParameter("cliente", cliente);
        return query.getResultList();
    }
}