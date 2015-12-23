/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.User;
import entities.facades.UserFacade;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Kiker
 */
@ManagedBean(name = "userLoginBean")
@SessionScoped
public class UserLoginBean implements Serializable
{

    @EJB
    private UserFacade usersFacade;


    String username;
    String password;
    boolean signedIn = false;
    boolean admin = false;

    User currentUser = null;

    public User getCurrentUser()
    {
        return currentUser;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public UserLoginBean()
    {
    }

    public String login()
    {
        System.out.println("Username[" + username + "] Password [" + password + "]");
        User user = usersFacade.findUser(username, password);
        if (user != null)
        {
            signedIn = true;
            currentUser = user;
            return "/user/home.xhtml?faces-redirect=true";
        }

        return "/login.xhtml?faces-redirect=true";

    }

    public boolean isSignedIn()
    {
        return signedIn;
    }

    public boolean isAdmin()
    {
        return admin;
    }

//    public String logOut()
//    {
//        signedIn = false;
//        return null;
//    }

}
