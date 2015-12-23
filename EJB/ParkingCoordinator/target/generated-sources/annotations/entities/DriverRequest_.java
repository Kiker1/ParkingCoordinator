package entities;

import entities.Parking;
import entities.PassengerRequest;
import entities.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-23T07:42:01")
@StaticMetamodel(DriverRequest.class)
public class DriverRequest_ { 

    public static volatile SingularAttribute<DriverRequest, Date> creationDate;
    public static volatile SingularAttribute<DriverRequest, Integer> iddriverRequest;
    public static volatile SingularAttribute<DriverRequest, Date> enterDate;
    public static volatile SingularAttribute<DriverRequest, String> status;
    public static volatile SingularAttribute<DriverRequest, User> userId;
    public static volatile ListAttribute<DriverRequest, PassengerRequest> passengerRequestList;
    public static volatile SingularAttribute<DriverRequest, Parking> parkingId;
    public static volatile SingularAttribute<DriverRequest, Date> leaveDate;
    public static volatile SingularAttribute<DriverRequest, Integer> freePlacesLeft;

}