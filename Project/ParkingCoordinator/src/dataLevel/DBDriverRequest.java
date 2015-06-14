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
import parkingcoordinator.logicLevel.DriverRequest;
import parkingcoordinator.logicLevel.PassengerRequest;
import parkingcoordinator.logicLevel.Request;

/**
 *
 * @author Kiker
 */
public class DBDriverRequest extends DriverRequest
{

    private int id = -1;
    private int user_id = -1;
    private int parking_id = -1;

    public DBDriverRequest(Date creationDate, DBUser user, DBParking parking, Date entryTime, Date leaveTime, int freePlaces)
    {
	super(creationDate, entryTime, leaveTime, freePlaces, parking);
	if (user != null)
	{
	    this.user_id = user.getId();
	}
	if (parking != null)
	{
	    this.parking_id = parking.getId();
	}
    }

    @Override
    public void addPassengerRequest(PassengerRequest request)
    {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removePassengerRequest(PassengerRequest request)
    {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Request> getTimeOutRequestsToClose()
    {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Request> getFillOutRequestsToClose()
    {
	throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void save()
    {
	String str = "INSERT INTO DRIVERREQUEST(ID, USER_ID, CREATION_DATE, ENTER_DATE, LEAVE_DATE, FREE_PLACES_LEFT, PARKING_ID, STATUS) "
		+ "VALUES (?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE "
		+ "ID=?,USER_ID=?,CREATION_DATE=?,ENTER_DATE=?,LEAVE_DATE=?,FREE_PLACES_LEFT=?,PARKING_ID=?,STATUS=?";
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
	    ps.setTimestamp(4, new Timestamp(getEntryDate().getTime()));
	    ps.setTimestamp(5, new Timestamp(getLeaveDate().getTime()));
	    ps.setInt(6, getFreePlaces());
	    ps.setInt(7, parking_id);
	    ps.setString(8, getState().name());

	    ps.setInt(9, id);
	    ps.setInt(10, user_id);
	    ps.setTimestamp(11, new Timestamp(getCreationDate().getTime()));
	    ps.setTimestamp(12, new Timestamp(getEntryDate().getTime()));
	    ps.setTimestamp(13, new Timestamp(getLeaveDate().getTime()));
	    ps.setInt(14, getFreePlaces());
	    ps.setInt(15, parking_id);
	    ps.setString(16, getState().name());

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
	String str = "DELETE FROM DRIVERREQUEST WHERE ID = ? ";
	try
	{
	    PreparedStatement ps = DB.getConnection().prepareCall(str);
	    ps.setInt(1, id);
	    ps.execute();

	} catch (SQLException ex)
	{
	    Logger.getLogger(DBDriverRequest.class.getName()).log(Level.SEVERE, null, ex);
	}
    }

    public static DBDriverRequest parse(ResultSet set) throws SQLException
    {
	DBParking p = new DBParking(set.getInt("PARKING.FREE_PLACES"),
		set.getString("PARKING.ADDRESS"), set.getString("PARKING.COMMENTS"));

	DBDriverRequest res = new DBDriverRequest(
		new Date(set.getTimestamp("DRIVERREQUEST.CREATION_DATE").getTime()),
		null,
		p,
		new Date(set.getTimestamp("DRIVERREQUEST.ENTER_DATE").getTime()),
		new Date(set.getTimestamp("DRIVERREQUEST.LEAVE_DATE").getTime()),
		set.getInt("FREE_PLACES_LEFT"));

	return res;
    }

}
