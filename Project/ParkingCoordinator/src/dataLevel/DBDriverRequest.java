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
import java.util.logging.Level;
import java.util.logging.Logger;
import logicLevel.DriverRequest;

/**
 *
 * @author Kiker
 */
public class DBDriverRequest extends DriverRequest
{

    private int id = -1;
    private int parking_id = -1;
    private int user_id = -1;

    public DBDriverRequest(DBUser user, ResultSet set) throws SQLException
    {
        this(user,
                set.getInt("DRIVERREQUEST.ID"),
                new Date(set.getTimestamp("DRIVERREQUEST.CREATION_DATE").getTime()),
                DBParking.parse(set),
                new Date(set.getTimestamp("DRIVERREQUEST.ENTER_DATE").getTime()),
                new Date(set.getTimestamp("DRIVERREQUEST.LEAVE_DATE").getTime()),
                set.getInt("DRIVERREQUEST.FREE_PLACES_LEFT"),
                Status.valueOf(set.getString("DRIVERREQUEST.STATUS"))
        );
    }

    public DBDriverRequest(DBUser user, int id, Date creationDate, DBParking parking, Date entryTime, Date leaveTime, int freePlaces, Status status)
    {
        super(creationDate, entryTime, leaveTime, freePlaces, parking, status);
        this.id = id;
        this.parking_id = parking.getId();
        this.user_id = user.getId();
    }

    public int getId()
    {
        return id;
    }

    @Override
    public void save()
    {
        String str = "INSERT INTO DRIVERREQUEST(ID, USER_ID, CREATION_DATE, ENTER_DATE, LEAVE_DATE, FREE_PLACES_LEFT, PARKING_ID, STATUS) "
                + "VALUES (?,?,?,?,?,?,?,?) "
                + "ON DUPLICATE KEY UPDATE FREE_PLACES_LEFT=?,STATUS=?";
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
            ps.setString(8, getStatus().name());

            
            ps.setInt(9, getFreePlaces());
            ps.setString(10, getStatus().name());

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
        
        String str = "DELETE FROM DRIVERREQUEST WHERE ID = ?";
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

}
