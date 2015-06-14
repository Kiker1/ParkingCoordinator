/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkingcoordinator.logicLevel;

import java.util.Date;

/**
 *
 * @author Kiker
 */
public abstract class DriverRequest extends Request
{
    private final Date entryTime;
    private final Date leaveTime;
    private final Parking parking;
    private int freePlaces;
    private Request.State state;
        

    public DriverRequest(Date creationDate, Date entryTime, Date leaveTime, int freePlaces, Parking parking)
    {
	super(creationDate);
	this.entryTime = entryTime;
	this.leaveTime = leaveTime;
	this.freePlaces = freePlaces;
	this.parking = parking;
	this.state = Request.State.Open;
    }
    
    public void changeState(Request.State state)
    {
	this.state = state;
    }
    
    public abstract void addPassengerRequest(PassengerRequest request);
    public abstract void removePassengerRequest(PassengerRequest request);

    public Date getEntryDate()
    {
	return entryTime;
    }

    public Date getLeaveDate()
    {
	return leaveTime;
    }

    public Parking getParking()
    {
	return parking;
    }

    public int getFreePlaces()
    {
	return freePlaces;
    }

    public State getState()
    {
	return state;
    }
    
    
    
}
