/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataLevel;

import java.util.List;
import parkingcoordinator.logicLevel.Request;
import parkingcoordinator.logicLevel.User;

/**
 *
 * @author Kiker
 */
public class DBUser extends User
{
    private Connection conn;
    
    public DBUser(Connection conn, String login, String pass)
    {
	super(login, pass);
	this.conn = conn;
    }

    @Override
    public void createRequest(Request requset)
    {
    }

    @Override
    public void deleteRequest(Request requset)
    {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Request> getAllRequests()
    {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addNewUser(User user)
    {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save()
    {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
