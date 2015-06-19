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
public class DBUserTest
{

    private final Connection connection;

    public DBUserTest()
    {
        connection = DB.getConnection();
    }

    /**
     * Test of save method, of class DBUser.
     */
    @Test
    public void testSave() throws SQLException
    {
        DBUser u = new DBUser("testlogin", "testpassword");
        u.save();
        
        PreparedStatement ps = connection.prepareCall("SELECT * FROM USER WHERE ID = ?");
        ps.setInt(1, u.getId());
        ResultSet res = ps.executeQuery();
        assertTrue(res.next());

        ps = connection.prepareCall("DELETE FROM USER WHERE ID = ?");
        ps.setInt(1, u.getId());
        ps.execute();
    }

    /**
     * Test of remove method, of class DBUser.
     */
    @Test
    public void testRemove() throws SQLException
    {
        DBUser u = new DBUser("testlogin", "testpassword");
        u.save();
        
        u.remove();
        
        PreparedStatement ps = connection.prepareCall("SELECT * FROM USER WHERE ID = ?");
        ps.setInt(1, u.getId());
        ResultSet res = ps.executeQuery();
        assertFalse(res.next());
        
    }

}
