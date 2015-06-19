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
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicLevel.PassengerRequest;
import logicLevel.Request;

/**
 *
 * @author Kiker
 */
public class DBPassengerRequest extends PassengerRequest
{

    private int id = -1;
    private int user_id = -1;
    private int drequest_id = -1;

    public DBPassengerRequest(DBUser user, ResultSet set) throws SQLException
    {
	this(	user,
		set.getInt("PASSENGERREQUEST.ID"),
		new Date(set.getTimestamp("PASSENGERREQUEST.CREATION_DATE").getTime()),
		new DBDriverRequest(user, set),
                Status.valueOf(set.getString("PASSENGERREQUEST.STATUS")));
    }
    
     public DBPassengerRequest(DBUser user, int id,  Date creationDate, DBDriverRequest request, Status status)
    {
	super(creationDate, request, status);
	this.id = id;
	this.user_id = user.getId();
	this.drequest_id = request.getId();
	
    }

    @Override
    public void save()
    {
	String str = "INSERT INTO PASSENGERREQUEST(ID, USER_ID, CREATION_DATE, DRIVERREQUEST_ID, STATUS) "
		+ "VALUES (?,?,?,?,?) ON DUPLICATE KEY UPDATE "
		+ "ID=?,USER_ID=?,CREATION_DATE=?,DRIVERREQUEST_ID=?,STATUS=?";
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

	    ps.setInt(2, user_id);
	    ps.setTimestamp(3, new Timestamp(getCreationDate().getTime()));
	    ps.setInt(4, drequest_id);
            ps.setString(5, status.name());

	    ps.setInt(6, id);
	    ps.setInt(7, user_id);
	    ps.setTimestamp(8, new Timestamp(getCreationDate().getTime()));
	    ps.setInt(9, drequest_id);
            ps.setString(10, status.name());

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
	try
	{
	    PreparedStatement ps = DB.getConnection().prepareCall("DELETE FROM PASSENGERREQUEST WHERE ID = ? ");
	    ps.setInt(1, id);
	    ps.execute();

	} catch (SQLException ex)
	{
	    Logger.getLogger(DBDriverRequest.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
    
}
