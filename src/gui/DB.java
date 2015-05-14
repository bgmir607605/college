/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Andrey
 */
public class DB {
    String userName = "admin";
    String password = "12345678";
    String url = "jdbc:mysql://188.120.245.21:3306/bpt?zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=utf8";
    Connection conn = null;
    
    void closeCon(){
        if (conn != null)
            {
                try
                {
                    conn.close ();
                    System.out.println ("Database connection terminated");
                }
                catch (Exception ex) { }
            }
    }
    
    public String[] getGroups(){
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
            closeCon();
        }
        return arr;
    }
    
    public String[][] selSQL(String t){
        String[][] arr = null;
        try
        {       
            Class.forName ("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection (url, userName, password);
            Statement stmt = null;
            ResultSet rs= null;
            stmt = conn.createStatement();   
            rs = stmt.executeQuery("select * from " + t + ";");
            rs.last();
            int m = rs.getRow();
            int n = rs.getMetaData().getColumnCount();
            rs.beforeFirst();
            arr = new String[m][n];
            int i = 0;
            while (rs.next()){
                for (int j = 1; j < n; j++){
                    arr[i][--j] = rs.getString(++j);
                }
                i++;
            }          
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            closeCon();
        }
        return arr;
    }
    
    public void ins(String q) throws SQLException{
        try
        {       
            Class.forName ("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection (url, userName, password);
            Statement stmt = null;
            ResultSet rs = null;
            stmt = conn.createStatement();          
            stmt.executeUpdate(q);
            System.out.println ("Database connection established");
        }
        catch (Exception ex)
        {
            System.err.println ("Cannot connect to database server");
            ex.printStackTrace();
        }
        
        
        finally
        {
            closeCon();
        }
          
           
    }
    public void del(String q) throws SQLException{
        try
        {       
            Class.forName ("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection (url, userName, password);
            Statement stmt = null;
            ResultSet rs = null;
            stmt = conn.createStatement();          
            stmt.executeUpdate(q);
            System.out.println ("Database connection established");
        }
        catch (Exception ex)
        {
            System.err.println ("Cannot connect to database server");
            ex.printStackTrace();
        }
        
        
        finally
        {
            closeCon();
        }
          
           
    }
    
}
