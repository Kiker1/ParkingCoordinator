/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.facades;

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
public class UserFacade extends AbstractFacade<User>
{

    @PersistenceContext(unitName = "com.kiker_ParkingCoordinator_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager()
    {
        return em;
    }

    public UserFacade()
    {
        super(User.class);
    }

    public User findUser(String username, String password)
    {
        List<User> res = em.createNamedQuery("User.findByNameAndPassword")
                .setParameter("name", username)
                .setParameter("password", password)
                .getResultList();
        if (res.size() == 1)
        {
            return res.get(0);
        }
        return null;
    }

}
