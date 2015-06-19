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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicLevel.DriverRequest;
import logicLevel.Parking;
import logicLevel.PassengerRequest;
import logicLevel.Request;
import logicLevel.Service;
import logicLevel.User;

/**
 *
 * @author Kiker
 */
public class DBService extends Service
{

    @Override
    public List<Parking> getAllParkings(boolean includeFilled)
    {
        String str = "SELECT * FROM PARKING";
        if (!includeFilled)
        {
            str += " WHERE FREE_PLACES > 0";
        }
        List<Parking> parkings = new ArrayList();
        try
        {
            PreparedStatement ps = DB.getConnection().prepareCall(str);
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                parkings.add(DBParking.parse(rs));
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DBService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parkings;
    }

    @Override
    public List<Request> getAllRequestsForUser(User user)
    {
        List<Request> res = new ArrayList();
        if (((DBUser) user).getId() == -1)
        {
            return res;
        }
        String str = "SELECT * FROM PARKING, DRIVERREQUEST WHERE DRIVERREQUEST.USER_ID = ? and"
                + " DRIVERREQUEST.PARKING_ID = PARKING.ID";
        try
        {
            PreparedStatement ps = DB.getConnection().prepareCall(str);
            ps.setInt(1, ((DBUser) user).getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                res.add(new DBDriverRequest((DBUser) user, rs));
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DBUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        str = "SELECT * FROM PASSENGERREQUEST, DRIVERREQUEST, PARKING WHERE "
                + "PASSENGERREQUEST.USER_ID = ? and PASSENGERREQUEST.DRIVERREQUEST_ID = DRIVERREQUEST.ID"
                + " and DRIVERREQUEST.PARKING_ID = PARKING.ID";

        try
        {
            PreparedStatement ps = DB.getConnection().prepareCall(str);
            ps.setInt(1, ((DBUser) user).getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                res.add(new DBPassengerRequest((DBUser) user, rs));
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DBUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;
    }

    @Override
    public String registerUser(User user)
    {
        String res = "User succesfully registered. Now you can log in.";
        try
        {
            PreparedStatement ps = DB.getConnection().prepareCall("SELECT ID FROM USER WHERE LOGIN = ?");//INSERT INTO USER(ID, LOGIN, PASS) VALUES (?,?,?)
            ps.setString(1, user.getLogin());
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                //такой уже есть
                res = "User with such this login already exist.";
            } else
            {
                if (user instanceof DBUser)
                {
                    user.save();
                } else
                {
                    new DBUser(user).save();
                }
            }

        } catch (SQLException ex)
        {
            Logger.getLogger(DBService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    @Override
    public String registerDriverRequest(DriverRequest request)
    {
        request.getParking().takeOnePlace();

        request.save();
        return "";
    }

    @Override
    public String removeRequests(List<Request> requests)
    {
        for (Request r : requests)
        {
            if (r instanceof DriverRequest)
            {
                ((DriverRequest) r).getParking().freeOnePlace();
            } else if (r instanceof PassengerRequest)
            {
                ((PassengerRequest) r).getDriverRequest().freeOnePlace();
            }

            r.remove();
        }
        return "";
    }

    @Override
    public List<Request> getAllDriverRequestsExceptUser(User user)
    {
        List<Request> res = new ArrayList();
        if (((DBUser) user).getId() == -1)
        {
            return res;
        }
        String str = "SELECT * FROM PARKING, DRIVERREQUEST, USER WHERE DRIVERREQUEST.USER_ID != ? and "
                + "DRIVERREQUEST.PARKING_ID = PARKING.ID and "
                + "USER.ID = DRIVERREQUEST.USER_ID and "
                + "DRIVERREQUEST.STATUS = 'Open' and "
                + "DRIVERREQUEST.FREE_PLACES_LEFT > 0";

        try
        {
            PreparedStatement ps = DB.getConnection().prepareCall(str);
            ps.setInt(1, ((DBUser) user).getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                res.add(new DBDriverRequest(new DBUser(rs.getString("USER.LOGIN"), rs.getString("USER.PASS")), rs));
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(DBUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }

    @Override
    public String registerPassengerRequest(PassengerRequest request)
    {
        request.getDriverRequest().takeOnePlace();

        request.save();
        return "";
    }

    @Override
    public boolean checkIfAdmin(User user)
    {
        String str = "SELECT * FROM ADMIN WHERE USER_ID = ?";

        try
        {
            PreparedStatement ps = DB.getConnection().prepareCall(str);
            ps.setInt(1, ((DBUser) user).getId());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex)
        {
            Logger.getLogger(DBUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String registerParking(Parking parking)
    {
        parking.save();
        return "";
    }

    @Override
    public void processRemovingReqests()
    {
        Connection conn = DB.getConnection();
        
        String str = "DELETE FROM DRIVERREQUEST "
                + "WHERE "
                + "(DRIVERREQUEST.STATUS = ? and DRIVERREQUEST.LEAVE_DATE > ?) or "
                + "DRIVERREQUEST.FREE_PLACES_LEFT > ?";
        try
        {
            PreparedStatement ps = conn.prepareCall(str);
            ps.setString(1, Request.Status.Closed.name());
            ps.setTimestamp(2, new Timestamp(new Date().getTime()));
            ps.setInt(3, 4);
            ps.execute();

        } catch (SQLException ex)
        {
            Logger.getLogger(DBUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void processClosingRequests()
    {
        String str = "UPDATE DRIVERREQUEST SET "
                + "STATUS = ? "
                + "WHERE "
                + "STATUS = ? and "
                + "FREE_PLACES_LEFT < ? and "
                + "ENTER_DATE < ?";
           
        try
        {
            long HOUR = 3600 * 1000;

            PreparedStatement ps = DB.getConnection().prepareCall(str);
            ps.setString(1, Request.Status.Closed.name());
            ps.setString(2, Request.Status.Open.name());
            ps.setInt(3, 3);
            ps.setTimestamp(4, new Timestamp(new Date().getTime() + HOUR * 1));
            System.out.println(ps);
            ps.execute();

        } catch (SQLException ex)
        {
            Logger.getLogger(DBUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        str = "UPDATE PASSENGERREQUEST, DRIVERREQUEST  SET "
                + "PASSENGERREQUEST.STATUS = ? "
                + "WHERE "
                + "DRIVERREQUEST.STATUS = ? and DRIVERREQUEST.ID = PASSENGERREQUEST.DRIVERREQUEST_ID";
        
         try
        {
            PreparedStatement ps = DB.getConnection().prepareCall(str);
            ps.setString(1, Request.Status.Closed.name());
            ps.setString(2, Request.Status.Closed.name());
            ps.execute();

        } catch (SQLException ex)
        {
            Logger.getLogger(DBUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
