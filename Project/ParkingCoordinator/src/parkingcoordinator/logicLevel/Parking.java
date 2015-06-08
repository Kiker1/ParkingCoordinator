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
    private String address;
    private int freeSpace;
    private int freeSpaceLeft;

    public String getAddress()
    {
	return address;
    }

    public int getFreeSpace()
    {
	return freeSpace;
    }

    public int getFreeSpaceLeft()
    {
	return freeSpaceLeft;
    }
    
    public void freeOnePlace()
    {
	if (freeSpaceLeft == freeSpace)
	    throw new IllegalStateException("Cant free when freeSpaceLeft == freeSpace");
	
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
