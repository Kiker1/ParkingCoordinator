/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceLevel.impl;

import dataLevel.DBDriverRequest;
import dataLevel.DBParking;
import dataLevel.DBPassengerRequest;
import dataLevel.DBUser;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import logicLevel.DriverRequest;
import logicLevel.Parking;
import logicLevel.PassengerRequest;
import logicLevel.Request;
import main.ParkingCoordinator;
import serviceLevel.Session;
import serviceLevel.UserService;

/**
 *
 * @author Kiker
 */
public class UserServiceImpl implements UserService
{
    @Override
    public String registerUser(Session session) throws RemoteException
    {
        return ParkingCoordinator.service.registerUser(new DBUser(session.login, session.password));
    }

    @Override
    public String logInUser(Session session) throws RemoteException
    {
        DBUser user = new DBUser(session.login, session.password);
        if (user.getId() == -1)
        {
            return "Invalid pair login/password.";
        }
        return "";
    }

    @Override
    public List<Object> getAllRequestsForUser(Session session) throws RemoteException
    {
        List<Object> res = new ArrayList();
        List<Request> requests = ParkingCoordinator.service.getAllRequestsForUser(new DBUser(session.login, session.password));

        for (Request r : requests)
        {

            if (r instanceof DriverRequest)
            {
                res.add(new Object[]
                {
                    "DriverRequest",
                    r.getCreationDate(),
                    r.getStatus().name(),
                    ((DriverRequest) r).getEntryDate(),
                    ((DriverRequest) r).getLeaveDate(),
                    ((DriverRequest) r).getFreePlaces(),
                    ((DriverRequest) r).getParking().getAddress()
                });
            } else if (r instanceof PassengerRequest)
            {
                res.add(new Object[]
                {
                    "PassengerRequest", r.getCreationDate(), r.getStatus().name()
                });
            }
        }

        return res;
    }

    @Override
    public List<Object> getAllParkings(Session session) throws RemoteException
    {
        List<Parking> parkings = ParkingCoordinator.service.getAllParkings(false);

        List<Object> res = new ArrayList();

        for (Parking p : parkings)
        {
            res.add(new Object[]
            {
                p.getAddress(), p.getComment(), p.getFreeSpaceLeft()
            });
        }

        return res;
    }

    @Override
    public String createDriverRequest(Session session, Map<String, Object> params) throws RemoteException
    {
        List<Parking> parkings = ParkingCoordinator.service.getAllParkings(true);
        Parking parking = null;
        for (Parking p : parkings)
        {
            if (p.getAddress().equalsIgnoreCase((String) params.get("address")))
            {
                parking = p;
                break;
            }
        }

        DBDriverRequest r = new DBDriverRequest(new DBUser(session.login, session.password),
                -1, new Date(),
                (DBParking) parking,
                (Date) params.get("entryDate"),
                (Date) params.get("leaveDate"),
                (int) params.get("freePlaces"),
                Request.Status.Open);

        ParkingCoordinator.service.registerDriverRequest(r);

        return "";
    }

    @Override
    public String deleteRequests(Session session, List<Object> params) throws RemoteException
    {
        List<Request> res = new ArrayList();
        List<Request> requests = ParkingCoordinator.service.getAllRequestsForUser(new DBUser(session.login, session.password));

        for (Request r : requests)
        {
            for (Object o : params)
            {
//                System.out.println("comparing " + r.getCreationDate() + "  and  " + (Date)o);
                if (r.getCreationDate().getTime() == ((Date) o).getTime())
                {
                    res.add(r);
                    break;
                }
            }
        }

        ParkingCoordinator.service.removeRequests(res);
        return "";
    }

    @Override
    public List<Object> getAllAvaliableDriverRequests(Session session) throws RemoteException
    {
        List<Object> res = new ArrayList();
        List<Request> requests
                = ParkingCoordinator.service.getAllDriverRequestsExceptUser(
                        new DBUser(session.login, session.password));

        for (Request r : requests)
        {
            res.add(new Object[]
            {
                r.getCreationDate(),
                ((DriverRequest) r).getParking().getAddress(),
                ((DriverRequest) r).getEntryDate(),
                ((DriverRequest) r).getLeaveDate(),
                ((DriverRequest) r).getFreePlaces()
            });

        }

        return res;
    }

    @Override
    public String createPassengerRequest(Session session, Map<String, Object> params) throws RemoteException
    {
        List<Request> requests
                = ParkingCoordinator.service.getAllDriverRequestsExceptUser(
                        new DBUser(session.login, session.password));

        DBDriverRequest req = null;

        for (Request r : requests)
        {
            if (r.getCreationDate().getTime() == ((Date) params.get("creationDate")).getTime())
            {
                req = (DBDriverRequest) r;
                break;
            }
        }

        ParkingCoordinator.service.registerPassengerRequest(
                new DBPassengerRequest(
                        new DBUser(session.login, session.password),
                        -1, new Date(),
                        req, Request.Status.Open));

        return "";

    }

    @Override
    public boolean checkIfAdmin(Session session) throws RemoteException
    {
        return ParkingCoordinator.service.checkIfAdmin(new DBUser(session.login, session.password));
    }

    @Override
    public String createParking(Session session, Map<String, Object> params) throws RemoteException
    {
        ParkingCoordinator.service.registerParking(new DBParking(
                (int) params.get("freePlaces"),
                (String) params.get("address"),
                (String) params.get("comments")));

        return "";
    }

}
