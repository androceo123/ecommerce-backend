package com.ecommerce.dao;

import com.ecommerce.model.Cliente;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import jakarta.transaction.Transactional;


@ApplicationScoped
@Transactional
public class ClienteDAO {

    @PersistenceContext
    private EntityManager em;

    public List<Cliente> listarTodos() {
        return em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
    }

    public Cliente buscarPorId(Long id) {
        return em.find(Cliente.class, id);
    }

    public void crear(Cliente cliente) {
        em.persist(cliente);
    }

    public void actualizar(Cliente cliente) {
        em.merge(cliente);
    }

    public void eliminar(Long id) {
        Cliente c = buscarPorId(id);
        if (c != null) {
            em.remove(c);
        }
    }
}
