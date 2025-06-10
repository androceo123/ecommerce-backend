package py.edu.fiuni.taller.ejb;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import py.edu.fiuni.taller.model.Servicio;
import py.edu.fiuni.taller.model.DetalleServicio;

import java.time.LocalDate;
import java.util.List;

@Stateless
public class ServicioService {

    @PersistenceContext(unitName = "TallerPU")
    private EntityManager em;

    public void crear(Servicio s) {
        em.persist(s);
    }

    public Servicio encontrar(Long id) {
        return em.find(Servicio.class, id);
    }

    public List<Servicio> listar() {
        return em.createQuery("SELECT s FROM Servicio s", Servicio.class).getResultList();
    }

    public void actualizar(Servicio s) {
        em.merge(s);
    }

    public void eliminar(Long id) {
        Servicio s = em.find(Servicio.class, id);
        if (s != null) {
            em.remove(s);
        }
    }

    // NUEVO MÉTODO PARA FILTRAR POR CLIENTE Y FECHA
    public List<Servicio> buscarPorClienteYFecha(Long clienteId, LocalDate fecha) {
        String jpql = "SELECT s FROM Servicio s WHERE "
                    + "(:clienteId IS NULL OR s.vehiculo.cliente.id = :clienteId) "
                    + "AND (:fecha IS NULL OR s.fecha = :fecha)";
        TypedQuery<Servicio> query = em.createQuery(jpql, Servicio.class);
        query.setParameter("clienteId", clienteId);
        query.setParameter("fecha", fecha);
        return query.getResultList();
    }
public List<DetalleServicio> obtenerDetalles(Long servicioId) {
    return em.createQuery("SELECT d FROM DetalleServicio d WHERE d.servicio.id = :id", DetalleServicio.class)
             .setParameter("id", servicioId)
             .getResultList();
}
}

