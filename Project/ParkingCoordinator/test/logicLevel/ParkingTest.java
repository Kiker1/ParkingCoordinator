/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicLevel;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kiker
 */
public class ParkingTest
{
    
    public ParkingTest()
    {
    }

    /**
     * Test of getAddress method, of class Parking.
     */
    @Test
    public void testGetAddress()
    {
    }

    /**
     * Test of getComment method, of class Parking.
     */
    @Test
    public void testGetComment()
    {
    }

    /**
     * Test of getFreeSpaceLeft method, of class Parking.
     */
    @Test
    public void testGetFreeSpaceLeft()
    {
    }

    /**
     * Test of freeOnePlace method, of class Parking.
     */
    @Test
    public void testFreeOnePlace()
    {
    }

    /**
     * Test of takeOnePlace method, of class Parking.
     */
    @Test
    public void testTakeOnePlace()
    {
    }

    public class ParkingImpl extends Parking
    {

        public ParkingImpl()
        {
            super(10, "some address", "some comments");
        }

        @Override
        public void save()
        {
        }

        @Override
        public void remove()
        {
        }
    }
    
}
