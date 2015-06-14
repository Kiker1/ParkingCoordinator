/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataLevel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author Kiker
 */
public class DB
{
    private static final String url;
    private static final String user;
    private static final String password;
    
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("There are not mysql jdbc driver!");
        }

        Properties configuration = new Properties();
        try {
            configuration.load(new FileInputStream("database.config"));
        } catch (IOException ex) {
	    configuration.setProperty("url", "127.0.0.1");
	    configuration.setProperty("user", "SYSDBA");
	    configuration.setProperty("password", "masterkey");
	    try
	    {
		configuration.store(new FileOutputStream("database.config"), null);
	    } catch (IOException ex1) {}
            throw new RuntimeException("Missing configuration file for database: (/database.config)");
        }
        url = configuration.getProperty("url");
        user = configuration.getProperty("user");
        password = configuration.getProperty("password");
        if (url==null || user==null || password==null) throw new RuntimeException("Bad configuration file for database!");
    }
       
    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(url,user,password);
        } catch (SQLException ex) {
            throw new RuntimeException("Can not connect to the database");
        }
    }
}
