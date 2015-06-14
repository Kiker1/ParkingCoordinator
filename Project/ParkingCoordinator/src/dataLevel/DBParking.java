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
import java.util.logging.Level;
import java.util.logging.Logger;
import parkingcoordinator.logicLevel.Parking;

/**
 *
 * @author Kiker
 */
public class DBParking extends Parking
{

    private int id = -1;

    public DBParking(int freePlaces, String address, String comment)
    {
	super(freePlaces, address, comment);
    }
    
    public int getId()
    {
	return id;
    }
    
    @Override
    public void save()
    {
	String str = "INSERT INTO PARKING(ID, FREE_PLACES, COMMENTS, ADDRESS) VALUES (?,?,?,?)";
	
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
	    
	    ps.setInt(2, getFreeSpaceLeft());
	    ps.setString(3, getComment());
	    ps.setString(3, getAddress());
	    
	    ps.execute();
	    ResultSet rs = ps.getGeneratedKeys();
	    if (rs.next())
	    {
		id = rs.getInt(1);
	    }
	    
	    
	} catch (SQLException ex)
	{
	    Logger.getLogger(DBParking.class.getName()).log(Level.SEVERE, null, ex);
	}
	    
    }

    @Override
    public void remove()
    {
	if (id == -1)
	{
	    return;
	}
	String str = "DELETE FROM PARKING WHERE ID = ? ";
	
	try
	{
	    PreparedStatement ps = DB.getConnection().prepareCall(str);
	    ps.setInt(1, id);
	    ps.execute();
	} catch (SQLException ex)
	{
	    Logger.getLogger(DBParking.class.getName()).log(Level.SEVERE, null, ex);
	}
	
    }
    
    public static DBParking parse(ResultSet set) throws SQLException
    {
	DBParking res = new DBParking(set.getInt("FREE_PLACES"), set.getString("ADDRESS"), set.getString("COMMENTS"));
	res.id = set.getInt("ID");
	return res;
    }
    
}
