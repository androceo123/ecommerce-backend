package com.ecommerce.dao;

import jakarta.persistence.TypedQuery;
import com.ecommerce.model.Cliente;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class ClienteDAO {

    @PersistenceContext
    private EntityManager em;

    public List<Cliente> buscarConFiltros(String apellido, String cedula) {
        String jpql = "SELECT c FROM Cliente c WHERE 1=1";
        if (apellido != null && !apellido.isEmpty()) {
            jpql += " AND c.apellido LIKE :apellido";
        }
        if (cedula != null && !cedula.isEmpty()) {
            jpql += " AND c.cedula = :cedula";
        }
        TypedQuery<Cliente> query = em.createQuery(jpql, Cliente.class);
        if (apellido != null && !apellido.isEmpty()) {
            query.setParameter("apellido", "%" + apellido + "%");
        }
        if (cedula != null && !cedula.isEmpty()) {
            query.setParameter("cedula", cedula);
        }
        return query.getResultList();
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

    public boolean existePorCedula(String cedula) {
        return em.createQuery("SELECT COUNT(c) FROM Cliente c WHERE c.cedula = :cedula", Long.class)
                .setParameter("cedula", cedula)
                .getSingleResult() > 0;
    }

    public boolean existePorEmail(String email) {
        return em.createQuery("SELECT COUNT(c) FROM Cliente c WHERE c.email = :email", Long.class)
                .setParameter("email", email)
                .getSingleResult() > 0;
    }
}
