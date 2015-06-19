/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicLevel;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Kiker
 */
public abstract class Request implements Storable
{
    private final Date creationDate;
    protected Request.Status status;
    
    public Request(Date date, Status status)
    {
	this.creationDate = date;
        this.status = status;
    }
    
    public Date getCreationDate()
    {
	return creationDate;
    }

    public Status getStatus()
    {
        return status;
    }
    
    public static enum Status {Open, Closed, Approved};
    
}
