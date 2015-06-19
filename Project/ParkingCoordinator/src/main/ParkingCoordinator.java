package main;


import dataLevel.DBService;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import logicLevel.Service;
import serviceLevel.impl.UserServiceImpl;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Kiker
 */
public class ParkingCoordinator
{

//    public static final Service service = new DBService();
    public static final String UBIND = "PC/user";
    public static final Service service = new DBService();
    
    public static void main(String[] args) throws RemoteException, AlreadyBoundException
    {
        final Registry registry = LocateRegistry.createRegistry(3000);
        System.out.println("Service register OK");
        
        final UserServiceImpl userService = new UserServiceImpl();
        Remote ustub = UnicastRemoteObject.exportObject(userService, 0);
        
        registry.bind(UBIND, ustub);
        System.out.println("Service USER BIND success");

    }

}
