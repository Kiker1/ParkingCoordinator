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
import java.util.Date;
import logicLevel.DriverRequest;
import logicLevel.Request;
import logicLevel.User;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kiker
 */
public class DBDriverRequestTest
{

    private final Connection connection;

    public DBDriverRequestTest()
    {
        connection = DB.getConnection();
    }

    /**
     * Test of save method, of class DBDriverRequest.
     */
    @Test
    public void testSave() throws SQLException
    {
        DBUser u = new DBUser("test", "test");
        u.save();

        DBParking p = new DBParking(100, "", "");
        p.save();

        DBDriverRequest r = new DBDriverRequest(u, 10,
                new Date(), p, new Date(), new Date(), 0, Request.Status.Open);
        r.save();
        r.remove();

        p.remove();
        u.remove();
        
        
        PreparedStatement ps = connection.prepareCall("SELECT * FROM DRIVERREQUEST WHERE ID = ?");
        ps.setInt(1, r.getId());
        ResultSet res = ps.executeQuery();
        assertFalse(res.next());

    }

    /**
     * Test of remove method, of class DBDriverRequest.
     */
    @Test
    public void testRemove() throws SQLException
    {
        DBUser u = new DBUser("test", "test");
        u.save();

        DBParking p = new DBParking(100, "", "");
        p.save();
        p.takeOnePlace();
        
        DBDriverRequest r = new DBDriverRequest(u, 10,
                new Date(), p, new Date(), new Date(), 0, Request.Status.Open);
        r.save();
        
        PreparedStatement ps = connection.prepareCall("SELECT * FROM PARKING WHERE ID = ?");
        ps.setInt(1, p.getId());
        ResultSet res = ps.executeQuery();
        res.next();
        p = DBParking.parse(res);
        assertTrue(p.getFreeSpaceLeft() == 99);
        
        
        r.getParking().freeOnePlace();
        ps = connection.prepareCall("SELECT * FROM PARKING WHERE ID = ?");
        ps.setInt(1, p.getId());
        res = ps.executeQuery();
        res.next();
        p = DBParking.parse(res);
        assertTrue(p.getFreeSpaceLeft() == 100);
        
        r.remove();

        p.remove();
        u.remove();
        
        
        ps = connection.prepareCall("SELECT * FROM DRIVERREQUEST WHERE ID = ?");
        ps.setInt(1, r.getId());
        res = ps.executeQuery();
        assertFalse(res.next());
    }

}
