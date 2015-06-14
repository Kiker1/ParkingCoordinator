/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkingcoordinator.logicLevel;

/**
 *
 * @author Kiker
 */
public abstract class Parking implements Storable
{
    private String comment;
    private String address;
    private int freeSpaceLeft;
    
    public Parking(int freePlaces, String address, String comment)
    {
	this.freeSpaceLeft = freePlaces;
	this.address = address;
	this.comment = comment;
    }

    public String getAddress()
    {
	return address;
    }

    public String getComment()
    {
	return comment;
    }

    public int getFreeSpaceLeft()
    {
	return freeSpaceLeft;
    }
    
    public void freeOnePlace()
    {
	freeSpaceLeft++;
	save();
    }
    
    public void takeOnePlace() 
    {
	if (freeSpaceLeft == 0)
	    throw new IllegalStateException("Cant take when freeSpaceLeft == 0");

	freeSpaceLeft--;
	save();
    }
    
    
}
