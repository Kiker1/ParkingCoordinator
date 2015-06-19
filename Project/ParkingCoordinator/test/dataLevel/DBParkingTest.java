/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataLevel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kiker
 */
public class DBParkingTest
{

    private final Connection connection;

    public DBParkingTest()
    {
        connection = DB.getConnection();
    }

    /**
     * Test of save method, of class DBParking.
     */
    @Test
    public void testSave() throws SQLException
    {
        DBParking p = new DBParking(100, "test address", "test comment");
        p.save();

        PreparedStatement ps = connection.prepareCall("SELECT *  FROM PARKING WHERE ID = ?");
        ps.setInt(1, p.getId());
        ResultSet res = ps.executeQuery();
//        res.next();
        assertTrue(res.next());

        ps = connection.prepareCall("DELETE FROM  PARKING WHERE ID = ?");
        ps.setInt(1, p.getId());
        ps.execute();
    }

    /**
     * Test of remove method, of class DBParking.
     */
    @Test
    public void testRemove() throws SQLException
    {
        DBParking p = new DBParking(100, "test address", "test comment");
        p.save();
        p.remove();
        PreparedStatement ps = connection.prepareCall("SELECT *  FROM PARKING WHERE ID = ?");
        ps.setInt(1, p.getId());
        ResultSet res = ps.executeQuery();
        assertFalse(res.next());
    }
    
    
    @Test
    public void testTakePlace() throws SQLException
    {
        DBParking p = new DBParking(100, "test address", "test comment");
        p.takeOnePlace();
        
        ResultSet res = connection.prepareCall("SELECT *  FROM PARKING WHERE ID = " + p.getId()).executeQuery();
        res.next();
        assertTrue(res.getInt("FREE_PLACES") == 99);
        
        p.remove();
    }
    
}
