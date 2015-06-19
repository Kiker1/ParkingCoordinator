/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicLevel;

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
    
    protected User(User u)
    {
	this(u.login, u.pass);
    }

    public String getLogin()
    {
	return login;
    }

    public String getPass()
    {
	return pass;
    }
    
    public abstract String registerThisUser();
    public abstract String loginAsThisUser();
//    public void createRequest(Request requset)
//    {
//	requset.setUser(this);
//	requset.save();
//    }
	    
//    public abstract List<Request> getAllRequests();

}
