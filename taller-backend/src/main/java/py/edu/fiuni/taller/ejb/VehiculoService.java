package py.edu.fiuni.taller.ejb;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import py.edu.fiuni.taller.model.Vehiculo;

import java.util.List;

@Stateless
public class VehiculoService {

    @PersistenceContext(unitName = "TallerPU")
    private EntityManager em;

    public void crear(Vehiculo v) {
        em.persist(v);
    }

    public Vehiculo encontrar(Long id) {
        return em.find(Vehiculo.class, id);
    }

    public List<Vehiculo> listar() {
        return em.createQuery("SELECT v FROM Vehiculo v", Vehiculo.class).getResultList();
    }

    public void actualizar(Vehiculo v) {
        em.merge(v);
    }

    public void eliminar(Long id) {
        Vehiculo v = em.find(Vehiculo.class, id);
        if (v != null) {
            em.remove(v);
        }
    }
}

