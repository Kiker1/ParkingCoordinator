/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkingcoordinator.logicLevel;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Kiker
 */
public abstract class Request implements Storable
{
    private final Date date;
    
    public Request()
    {
	this.date = new Date();
    }

    public static enum State {Open, Closed};
    
    public abstract List<Request> getTimeOutRequestsToClose();
    public abstract List<Request> getFillOutRequestsToClose();
    
}
