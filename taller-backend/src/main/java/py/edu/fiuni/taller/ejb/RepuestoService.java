package py.edu.fiuni.taller.ejb;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import py.edu.fiuni.taller.model.Repuesto;

import java.util.List;

@Stateless
public class RepuestoService {

    @PersistenceContext(unitName = "TallerPU")
    private EntityManager em;

    public void crear(Repuesto r) {
        em.persist(r);
    }

    public Repuesto encontrar(Long id) {
        return em.find(Repuesto.class, id);
    }

    public List<Repuesto> listar() {
        return em.createQuery("SELECT r FROM Repuesto r", Repuesto.class).getResultList();
    }

    public void actualizar(Repuesto r) {
        em.merge(r);
    }

    public void eliminar(Long id) {
        Repuesto r = em.find(Repuesto.class, id);
        if (r != null) {
            em.remove(r);
        }
    }
}

