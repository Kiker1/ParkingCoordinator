/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkingcoordinator.logicLevel;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Kiker
 */
public class Service
{
    Timer timer = new Timer();
    public Service()
    {
	timer.scheduleAtFixedRate(new TimerTask()
	{

	    @Override
	    public void run()
	    {
		System.out.println("Timer!");
	    }
	}, 0, 1000);
    }
    
//    public List<Request> getAllRequestToClose()
//    {
////	List<Request> = 
//    }
}
