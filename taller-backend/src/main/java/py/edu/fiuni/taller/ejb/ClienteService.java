package py.edu.fiuni.taller.ejb;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import py.edu.fiuni.taller.model.Cliente;

import java.util.List;

@Stateless
public class ClienteService {

    @PersistenceContext(unitName = "TallerPU")
    private EntityManager em;

    public void crear(Cliente cliente) {
        em.persist(cliente);
    }

    public Cliente encontrar(Long id) {
        return em.find(Cliente.class, id);
    }

    public List<Cliente> listar() {
        return em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
    }

    public void actualizar(Cliente cliente) {
        em.merge(cliente);
    }

    public void eliminar(Long id) {
        Cliente c = em.find(Cliente.class, id);
        if (c != null) {
            em.remove(c);
        }
    }
}

