package py.edu.fiuni.taller.ejb;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import py.edu.fiuni.taller.model.DetalleServicio;

import java.util.List;

@Stateless
public class DetalleServicioService {

    @PersistenceContext(unitName = "TallerPU")
    private EntityManager em;

    public void crear(DetalleServicio d) {
        em.persist(d);
    }

    public DetalleServicio encontrar(Long id) {
        return em.find(DetalleServicio.class, id);
    }

    public List<DetalleServicio> listar() {
        return em.createQuery("SELECT d FROM DetalleServicio d", DetalleServicio.class).getResultList();
    }

    public void actualizar(DetalleServicio d) {
        em.merge(d);
    }

    public void eliminar(Long id) {
        DetalleServicio d = em.find(DetalleServicio.class, id);
        if (d != null) {
            em.remove(d);
        }
    }
}

