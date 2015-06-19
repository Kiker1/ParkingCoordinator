/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceLevel;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import logicLevel.DriverRequest;

/**
 *
 * @author Kiker
 */
public interface UserService extends Remote
{

    String registerUser(Session session) throws RemoteException;

    String logInUser(Session session) throws RemoteException;

    List<Object> getAllRequestsForUser(Session session) throws RemoteException;

    List<Object> getAllAvaliableDriverRequests(Session session) throws RemoteException;

    List<Object> getAllParkings(Session session) throws RemoteException;

    String createDriverRequest(Session session, Map<String, Object> params) throws RemoteException;

    String createPassengerRequest(Session session, Map<String, Object> params) throws RemoteException;

    String deleteRequests(Session session, List<Object> params) throws RemoteException;

    boolean checkIfAdmin(Session session) throws RemoteException;
    
    String createParking(Session session, Map<String, Object> params) throws RemoteException;

}
