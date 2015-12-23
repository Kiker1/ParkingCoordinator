/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.facades;

import entities.PassengerRequest;
import entities.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Kiker
 */
@Stateless
public class PassengerRequestFacade extends AbstractFacade<PassengerRequest>
{

    @PersistenceContext(unitName = "com.kiker_ParkingCoordinator_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public PassengerRequestFacade()
    {
        super(PassengerRequest.class);
    }

    public List<PassengerRequest> findByUser(User user)
    {
        return em.createNamedQuery("PassengerRequest.findByUser")
                .setParameter("userId", user)
                .getResultList();
    }

    
    
    
//    public void createNew(PassengerRequest req)
//    {
//        em.getTransaction().begin();
//        em.persist(req);
//       
//        em.getTransaction().commit();
//    }

    @Override
    public void create(PassengerRequest req)
    {
        req.getDriverRequestid().setFreePlacesLeft(req.getDriverRequestid().getFreePlacesLeft() - 1);
        em.merge(req.getDriverRequestid());
        em.persist(req);
    }

    @Override
    public void remove(PassengerRequest req)
    {
        req.getDriverRequestid().setFreePlacesLeft(req.getDriverRequestid().getFreePlacesLeft() + 1);
        em.merge(req.getDriverRequestid());
        req = em.merge(req);
        em.remove(req);
    }
    
    

}
