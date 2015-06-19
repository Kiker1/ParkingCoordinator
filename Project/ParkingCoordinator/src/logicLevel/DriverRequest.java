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
public abstract class DriverRequest extends Request
{
    private final Date entryTime;
    private final Date leaveTime;
    private final Parking parking;
    private int freePlaces;

        

    public DriverRequest(Date creationDate, Date entryTime, Date leaveTime, int freePlaces, Parking parking, Status status)
    {
	super(creationDate, status);
	this.entryTime = entryTime;
	this.leaveTime = leaveTime;
	this.freePlaces = freePlaces;
	this.parking = parking;
    }
    
//    public DriverRequest(DriverRequest request)
//    {
//        super(request.getCreationDate(), request.getStatus());
//        this.entryTime = request.getEntryDate();
//	this.leaveTime = request.getLeaveDate();
//	this.freePlaces = request.getFreePlaces();
//	this.parking = request.getParking();
//    }
    
    public void changeState(Request.Status state)
    {
	this.status = state;
    }
    
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
    
    public void freeOnePlace()
    {
	freePlaces++;
	save();
    }
    
    public void takeOnePlace() 
    {
	if (freePlaces == 0)
	    throw new IllegalStateException("Cant take when freePlaces == 0");

	freePlaces--;
	save();
    }

    public void setClosedStatus()
    {
	status = Status.Closed;
    }
    
    
}
