
import java.util.logging.Level;
import java.util.logging.Logger;
import parkingcoordinator.logicLevel.Service;

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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
	// TODO code application logic here
	Service s = new Service();
	
	try
	{
	    Thread.sleep(10000);
	} catch (InterruptedException ex)
	{
	    Logger.getLogger(ParkingCoordinator.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
    
}
