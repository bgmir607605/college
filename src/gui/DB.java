/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Andrey
 */
public class DB {
    String userName = "admin";
    String password = "12345678";
    String url = "jdbc:mysql://188.120.245.21:3306/bpt?zeroDateTimeBehavior=convertToNull";
    public String[] getGroups(){
        Connection conn = null;
        String[] arr = null;
        try
        {       
            Class.forName ("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection (url, userName, password);
            Statement stmt = null;
            ResultSet rs= null;
            stmt = conn.createStatement();   
            
            rs = stmt.executeQuery("select * from groups");
            rs.last();
            int e = rs.getRow();
            rs.beforeFirst();
            arr = new String[e];
            int i = 0;
            while (rs.next()){
                arr[i] = rs.getString(2);
                i++;
            }          
        }
        catch (Exception ex)
        {
            System.err.println ("Cannot connect to database server");
            ex.printStackTrace();
        }
        finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close ();
                }
                catch (Exception ex) { }
            }
        }
        return arr;
    }
    
}
