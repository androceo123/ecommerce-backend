package com.ecommerce.dao;

import com.ecommerce.model.Categoria;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CategoriaDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Categoria> listarTodas() {
        return entityManager.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList();
    }

    public Categoria buscarPorId(Long id) {
        return entityManager.find(Categoria.class, id);
    }

    @Transactional
    public void crear(Categoria categoria) {
        entityManager.persist(categoria);
    }

    @Transactional
    public void actualizar(Categoria categoria) {
        entityManager.merge(categoria);
    }

    @Transactional
    public void eliminar(Long id) {
        Categoria categoria = buscarPorId(id);
        if (categoria != null) {
            entityManager.remove(categoria);
        }
    }
}
