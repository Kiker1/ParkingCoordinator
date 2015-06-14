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
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import parkingcoordinator.logicLevel.Request;
import parkingcoordinator.logicLevel.User;

/**
 *
 * @author Kiker
 */
public class DBUser extends User
{

    private int id = -1;

    public DBUser(User user)
    {
	this(user.getLogin(), user.getPass());
    }

    public DBUser(String login, String pass)
    {
	super(login, pass);
	id = retreaveId();
    }

    @Override
    public List<Request> getAllRequests()
    {
	List<Request> res = new ArrayList();
	if (id == -1)
	    return res;
	String str = "SELECT * FROM PARKING, DRIVERREQUEST WHERE DRIVERREQUEST.USER_ID = ? and"
		+ " DRIVERREQUEST.PARKING_ID = PARKING.ID";
	try
	{
	    PreparedStatement ps = DB.getConnection().prepareCall(str);
	    ps.setInt(1, id);
	    ResultSet rs = ps.executeQuery();
	    while (rs.next())
	    {
		DBDriverRequest r = DBDriverRequest.parse(rs);
		r.setUser(this);
		res.add(r);
	    }
	} catch (SQLException ex)
	{
	    Logger.getLogger(DBUser.class.getName()).log(Level.SEVERE, null, ex);
	}
	return res;
    }

    @Override
    public void save()
    {
	String str = "INSERT INTO USER (ID, LOGIN, PASS) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE ID=?, LOGIN=?, PASS=?";
	try
	{
	    PreparedStatement ps = DB.getConnection().prepareStatement(str, Statement.RETURN_GENERATED_KEYS);
	    if (id == -1)
	    {
		ps.setNull(1, Types.INTEGER);
	    } else
	    {
		ps.setInt(1, id);
	    }

	    ps.setString(2, getLogin());
	    ps.setString(3, getPass());

	    ps.setInt(4, id);
	    ps.setString(5, getLogin());
	    ps.setString(6, getPass());

	    ps.execute();
	    ResultSet rs = ps.getGeneratedKeys();
	    if (rs.next())
	    {
		id = rs.getInt(1);
	    }

	} catch (SQLException ex)
	{
	    Logger.getLogger(DBUser.class.getName()).log(Level.SEVERE, null, ex);
	}

    }

    @Override
    public void remove()
    {
	if (id == -1)
	{
	    return;
	}

	String str = "DELETE FROM USER WHERE ID = ?";
	try
	{
	    PreparedStatement ps = DB.getConnection().prepareCall(str);
	    ps.setInt(1, id);
	    ps.execute();
	} catch (SQLException ex)
	{
	    Logger.getLogger(DBUser.class.getName()).log(Level.SEVERE, null, ex);
	}

    }

    private int retreaveId()
    {
	try
	{
	    PreparedStatement ps = DB.getConnection().prepareCall("select id from USER where LOGIN = ? and PASS = ?");
	    ps.setString(1, this.getLogin());
	    ps.setString(2, this.getPass());
	    ResultSet rs = ps.executeQuery();
	    rs.next();
	    return rs.getInt("ID");
	} catch (SQLException ex)
	{
	    return -1;
	}
    }

    public int getId()
    {
	return id;
    }

}
