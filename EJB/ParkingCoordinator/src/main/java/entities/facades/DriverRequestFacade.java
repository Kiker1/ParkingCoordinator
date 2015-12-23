/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.facades;

import entities.DriverRequest;
import entities.PassengerRequest;
import entities.User;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Kiker
 */
@Stateless
public class DriverRequestFacade extends AbstractFacade<DriverRequest>
{

    @EJB
    private PassengerRequestFacade passengerRequestFacade;

    @PersistenceContext(unitName = "com.kiker_ParkingCoordinator_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public DriverRequestFacade()
    {
        super(DriverRequest.class);
    }

    public List<DriverRequest> findByUser(User user)
    {
        return em.createNamedQuery("DriverRequest.findByUser")
                .setParameter("userId", user)
                .getResultList();
    }

    public List<DriverRequest> findAllValid(User user)
    {
        return em.createNamedQuery("DriverRequest.findAllValid")
                .setParameter("userId", user)
                .getResultList();
    }

    @Override
    public void create(DriverRequest req)
    {
        req.getParkingId().setFreePlaces(req.getParkingId().getFreePlaces() - 1);
        em.merge(req.getParkingId());
        em.persist(req);
    }

    @Override
    public void remove(DriverRequest req)
    {
        req.getParkingId().setFreePlaces(req.getParkingId().getFreePlaces() + 1);
        req = em.merge(req);
        em.remove(req);
    }

}
