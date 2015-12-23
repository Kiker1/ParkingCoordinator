/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.facades;

import entities.Parking;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Kiker
 */
@Stateless
public class ParkingFacade extends AbstractFacade<Parking>
{

    @PersistenceContext(unitName = "com.kiker_ParkingCoordinator_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public ParkingFacade()
    {
        super(Parking.class);
    }

    public Parking findById(int id)
    {
        try
        {
            return (Parking) em.createNamedQuery("Parking.findByIdparking")
                    .setParameter("idparking", id)
                    .getSingleResult();
        } catch (Exception ex)
        {
        }
        return null;
    }

}
