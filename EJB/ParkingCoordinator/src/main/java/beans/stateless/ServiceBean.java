/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.stateless;

import entities.DriverRequest;
import entities.Parking;
import entities.PassengerRequest;
import entities.User;
import entities.facades.DriverRequestFacade;
import entities.facades.ParkingFacade;
import entities.facades.PassengerRequestFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.ScalarDataModel;

/**
 *
 * @author Kiker
 */
@Stateless
@ManagedBean
public class ServiceBean
{

    @EJB
    private ParkingFacade parkingFacade;
    @EJB
    private DriverRequestFacade driverRequestFacade;
    @EJB
    private PassengerRequestFacade passengerRequestFacade;

    public DataModel<PassengerRequest> getPassReqDataModel(User user)
    {
        return new ListDataModel(passengerRequestFacade.findByUser(user));
    }

    public DataModel<DriverRequest> getDriverReqDataModel(User user)
    {
        return new ListDataModel(driverRequestFacade.findByUser(user));
    }

    public List<DriverRequest> getAllValidDriverRequests(User user)
    {
        return driverRequestFacade.findAllValid(user);
    }
    
    public DataModel<DriverRequest> getAllValidDriverRequestsDataModel(User user)
    {
        return new ListDataModel(getAllValidDriverRequests(user));
    }

    public List<Parking> getAllParkings()
    {
        return parkingFacade.findAll();
    }

    public DataModel<Parking> getAllParkingsDataModel(String p)
    {
        if (p != null && !p.isEmpty())
        {
            int id = Integer.parseInt(p);
            return new ScalarDataModel<>(parkingFacade.findById(id));
        }
        return new ListDataModel<>(parkingFacade.findAll());
    }
    
    public String removePassengerRequest(PassengerRequest req)
    {
        
        passengerRequestFacade.remove(req);
        return null;
    }
    
    public String removeDriverRequest(DriverRequest req)
    {
        driverRequestFacade.remove(req);
        return null;
    }

}
