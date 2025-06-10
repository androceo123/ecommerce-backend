package py.edu.fiuni.taller.ejb;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import py.edu.fiuni.taller.model.Mecanico;

import java.util.List;

@Stateless
public class MecanicoService {

    @PersistenceContext(unitName = "TallerPU")
    private EntityManager em;

    public void crear(Mecanico m) {
        em.persist(m);
    }

    public Mecanico encontrar(Long id) {
        return em.find(Mecanico.class, id);
    }

    public List<Mecanico> listar() {
        return em.createQuery("SELECT m FROM Mecanico m", Mecanico.class).getResultList();
    }

    public void actualizar(Mecanico m) {
        em.merge(m);
    }

    public void eliminar(Long id) {
        Mecanico m = em.find(Mecanico.class, id);
        if (m != null) {
            em.remove(m);
        }
    }
}

