/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serviceLevel;

import java.io.Serializable;

/**
 *
 * @author Kiker
 */
public class Session implements Serializable
{

    public final String login;
    public final String password;

    public Session(String login, String password)
    {
        this.login = login;
        this.password = password;
    }
}
