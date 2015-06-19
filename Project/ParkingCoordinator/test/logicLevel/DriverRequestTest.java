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
public class DriverRequestTest
{
    
    public DriverRequestTest()
    {
    }

    /**
     * Test of changeState method, of class DriverRequest.
     */
    @Test
    public void testChangeState()
    {
        DriverRequest dr = new DriverRequestImpl();
        assertTrue(dr.status == Request.Status.Open);
        dr.changeState(Request.Status.Closed);
        assertTrue(dr.status == Request.Status.Closed);
    }


    /**
     * Test of setClosedState method, of class DriverRequest.
     */
    @Test
    public void testSetClosedState()
    {
        DriverRequest dr = new DriverRequestImpl();
        assertTrue(dr.status == Request.Status.Open);
        dr.setClosedStatus();
        assertTrue(dr.status == Request.Status.Closed);
    }

    public class DriverRequestImpl extends DriverRequest
    {

        public DriverRequestImpl()
        {
            super(null, null, null, 0, null, Status.Open);
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
