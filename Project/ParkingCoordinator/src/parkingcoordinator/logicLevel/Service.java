/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkingcoordinator.logicLevel;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Kiker
 */
public abstract class Service
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
	}, 0, 10000);
	
    }
    
    public abstract List<Request> getAllRequestToClose();
    
    public abstract boolean addNewUser(User user);
    
    public abstract List<Parking> getAllParkings();

    public void stop()
    {
	timer.cancel();
    }
}
