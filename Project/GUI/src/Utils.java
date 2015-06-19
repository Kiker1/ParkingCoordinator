
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import main.ParkingCoordinator;
import serviceLevel.UserService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Kiker
 */
public class Utils
{

    private static Registry registry = null;
    private static UserService userService = null;

    public static UserService getUserService() throws RemoteException, NotBoundException
    {
        if (registry == null)
        {
            registry = LocateRegistry.getRegistry("localhost", 3000);
        }
        if (userService == null)
        {
            userService = (UserService) registry.lookup(ParkingCoordinator.UBIND);
        }
        return userService;
    }
}
