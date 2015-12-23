package entities;

import entities.DriverRequest;
import entities.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-23T07:42:01")
@StaticMetamodel(PassengerRequest.class)
public class PassengerRequest_ { 

    public static volatile SingularAttribute<PassengerRequest, Date> creationDate;
    public static volatile SingularAttribute<PassengerRequest, User> userId;
    public static volatile SingularAttribute<PassengerRequest, Integer> idpassengerRequest;
    public static volatile SingularAttribute<PassengerRequest, DriverRequest> driverRequestid;

}