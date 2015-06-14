/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataLevel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import parkingcoordinator.logicLevel.Parking;
import parkingcoordinator.logicLevel.Request;
import parkingcoordinator.logicLevel.Service;
import parkingcoordinator.logicLevel.User;

/**
 *
 * @author Kiker
 */
public class DBService extends Service
{

    @Override
    public List<Request> getAllRequestToClose()
    {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addNewUser(User user)
    {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Parking> getAllParkings()
    {
	String str = "SELECT * FROM PARKING";
	List<Parking> res = new ArrayList();
	try
	{
	    PreparedStatement ps = DB.getConnection().prepareCall(str);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next())
	    {
		res.add(DBParking.parse(rs));
	    }
	} finally
	{
	    return res;
	}
    }
    
}
