/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicLevel;

import java.util.Date;

/**
 *
 * @author Kiker
 */
public abstract class PassengerRequest extends Request
{
    private DriverRequest driverRequest;
    public PassengerRequest(Date date, DriverRequest driverRequest, Status status)
    {
	super(date, status);
	this.driverRequest = driverRequest;
    }

    public DriverRequest getDriverRequest()
    {
	return driverRequest;
    }
    
    
    
}
