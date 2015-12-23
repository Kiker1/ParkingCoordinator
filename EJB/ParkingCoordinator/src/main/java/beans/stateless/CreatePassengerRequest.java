/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.stateless;

import beans.UserLoginBean;
import entities.PassengerRequest;
import entities.facades.PassengerRequestFacade;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.transaction.Transactional;

/**
 *
 * @author Kiker
 */
@ManagedBean(name = "createPassengerRequestBean")
@ViewScoped
public class CreatePassengerRequest implements Serializable
{

    @EJB
    private PassengerRequestFacade passengerRequestFacade;
    @ManagedProperty(value = "#{userLoginBean}")
    private UserLoginBean loginBean;

    public void setLoginBean(UserLoginBean loginBean)
    {
        this.loginBean = loginBean;
    }

    PassengerRequest request = new PassengerRequest();

    public PassengerRequest getRequest()
    {
        return request;
    }

    @Transactional
    public String create()
    {
        if (request.getDriverRequestid() == null) return "/user/createPassengerRequest.xhtml";
        request.setCreationDate(new Date());
        request.setUserId(loginBean.getCurrentUser());
        
        passengerRequestFacade.create(request);
        

        return "/user/home.xhtml?faces-redirect=true";
    }
}
