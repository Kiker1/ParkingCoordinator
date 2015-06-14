
import dataLevel.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import parkingcoordinator.logicLevel.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Kiker
 */
public class ParkingCoordinator
{

   
    public static final Service service = new DBService();
    
    public static void main(String[] args) throws SQLException
    {
	// TODO code application logic here
//	
	
//	
	
	List<Parking> res = service.getAllParkings();

	if (res.size() == 0) return;
	
	DBUser u = new DBUser("testL", "testP");
	
	List<Request> requests = u.getAllRequests();
	System.out.println(requests.get(0));
	
//	u.createRequest(new DBDriverRequest(u, (DBParking)res.get(0), new Date(), new Date(), 2));
	
	
	service.stop();
	
    }
    
}
