/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.stateless;

import beans.UserLoginBean;
import entities.DriverRequest;
import entities.facades.DriverRequestFacade;
import entities.facades.PassengerRequestFacade;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.transaction.Transactional;

/**
 *
 * @author Kiker
 */
@ManagedBean(name = "createDriverRequestBean")
@ViewScoped
public class CreateDriverRequest implements Serializable
{

    @EJB
    private DriverRequestFacade driverRequestFacade;
    @ManagedProperty(value = "#{userLoginBean}")
    private UserLoginBean loginBean;

    public void setLoginBean(UserLoginBean loginBean)
    {
        this.loginBean = loginBean;
    }

    DriverRequest request = new DriverRequest();

    public DriverRequest getRequest()
    {
        return request;
    }

    @Transactional
    public String create()
    {
        request.setCreationDate(new Date());
        request.setUserId(loginBean.getCurrentUser());
        request.setStatus("OPEN");
        
        driverRequestFacade.create(request);

        return "/user/home.xhtml?faces-redirect=true";
    }
    
    

}
