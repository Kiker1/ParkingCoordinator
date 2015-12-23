package entities;

import entities.Admin;
import entities.DriverRequest;
import entities.PassengerRequest;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-12-23T07:42:01")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, Integer> iduser;
    public static volatile SingularAttribute<User, String> name;
    public static volatile ListAttribute<User, PassengerRequest> passengerRequestList;
    public static volatile SingularAttribute<User, String> password;
    public static volatile ListAttribute<User, DriverRequest> driverRequestList;
    public static volatile ListAttribute<User, Admin> adminList;

}