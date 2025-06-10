package py.edu.fiuni.taller.ejb;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import py.edu.fiuni.taller.model.Vehiculo;

@Stateless
public class VehiculoFacade extends AbstractFacade<Vehiculo> {

    @PersistenceContext(unitName = "TallerPU")
    private EntityManager em;

    public VehiculoFacade() {
        super(Vehiculo.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}

