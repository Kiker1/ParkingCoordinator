/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicLevel;

import dataLevel.DBUser;
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
                try
                {
                    processClosingRequests();
//                    processRemovingReqests();
                    System.out.print(".");
                } catch (Exception ex)
                {
                    System.out.println(ex);
                }
            }
        }, 0, 5000);

    }

    public abstract void processClosingRequests();////

    public abstract void processRemovingReqests();////

    public abstract List<Request> getAllRequestsForUser(User user);

    public abstract List<Request> getAllDriverRequestsExceptUser(User user);

    public abstract List<Parking> getAllParkings(boolean includeFilled);

    public abstract String registerUser(User user);

    public abstract String registerDriverRequest(DriverRequest request);

    public abstract String registerPassengerRequest(PassengerRequest request);

    public abstract String removeRequests(List<Request> requests);

    public abstract boolean checkIfAdmin(User user);

    public abstract String registerParking(Parking parking);

    public void stop()
    {
        timer.cancel();
    }

}
