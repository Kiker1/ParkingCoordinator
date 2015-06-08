/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkingcoordinator.logicLevel;

import java.util.List;

/**
 *
 * @author Kiker
 */
public abstract class User implements Storable 
{


    private final String login;

    private final String pass;

    public User(String login, String pass)
    {
	this.login = login;
	this.pass = pass;
    }

    public String getLogin()
    {
	return login;
    }

    public String getPass()
    {
	return pass;
    }
    
    public abstract void createRequest(Request requset);
    public abstract void deleteRequest(Request requset);
    public abstract List<Request> getAllRequests();
    
    public abstract void addNewUser(User user);
}
