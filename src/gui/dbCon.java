/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;
import gui.Form;
import java.io.*;
import java.sql.*;
/**
 *
 * @author aamir
 */


public class dbCon {
    String userName = "admin";
    String password = "12345678";
    String url = "jdbc:mysql://188.120.245.21:3306/bpt?zeroDateTimeBehavior=convertToNull";
    
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
    public void addGroup(String n){
        
        try
        {       
            Class.forName ("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection (url, userName, password);
            Statement stmt = null;
            ResultSet rs= null;
            stmt = conn.createStatement();
            stmt.executeUpdate("INSERT INTO groups (name, specialtyId) values('"+ n + "', '1')");
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
        
    //Просмотр преподавателей
    public String[][] seeTeacher(){
        Connection conn = null;
        String[][] arr = null;
        try
        {       
            Class.forName ("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection (url, userName, password);
            Statement stmt = null;
            ResultSet rs= null;
            stmt = conn.createStatement();   
            
            rs = stmt.executeQuery("select * from teachers");
            rs.last();
            int e = rs.getRow();
            rs.beforeFirst();
            arr = new String[e][4];
            
            int i = 0;
            while (rs.next()){
                arr[i][0] = rs.getString(1);
                arr[i][1] = rs.getString(2);
                arr[i][2] = rs.getString(3);
                arr[i][3] = rs.getString(4);
                i++;
            }
            System.out.println ("Database connection established");
                       
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
                    System.out.println ("Database connection terminated");
                }
                catch (Exception ex) { }
            }
        }
        return arr;
        
    }
    
    //Добавить в базу преподавателя
    public void addTeacher(String fName, String mName, String lName) throws SQLException{
        Connection conn = null;
        try
        {       
            Class.forName ("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection (url, userName, password);
            Statement stmt = null;
            ResultSet rs= null;
            stmt = conn.createStatement();          
            stmt.executeUpdate("INSERT INTO teachers (lName, fName, mName) values('"+ lName + "', '"+ fName + "', '"+ mName + "')");
            System.out.println ("Database connection established");
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
                    System.out.println ("Database connection terminated");
                }
                catch (Exception ex) { }
            }
        }
          
           
    }
    
    //Выборка групп
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
    
    
    
    public String[][] getGroupLoad(String id){
        /*
        *В качестве аргумента метод принимает Id группы
        *0 - Id нагрузки
        *1 - Id преподавателя
        *2 - Id дисциплины
        */
        
        Connection conn = null;
        String[][] arr = null;
        try
        {       
            Class.forName ("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection (url, userName, password);
            Statement stmt = null;
            ResultSet rs= null;
            stmt = conn.createStatement();          
            //Список id нагрузок по выбранной группе
            
            rs = stmt.executeQuery("select * from teacherLoad where groupId=" + id + "");
            System.out.println("ok");
            rs.last();
            int e = rs.getRow();
            rs.beforeFirst();
            arr = new String[e][3];
            int i = 0;
            while (rs.next()){
                arr[i][0] = rs.getString(1);//id нагрузки
                arr[i][1] = rs.getString(2);//id преподавателя
				arr[i][2] = rs.getString(4);//id дисциплины
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
    public void addShedule(){
        Connection conn = null;
        try
        {       
            Class.forName ("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection (url, userName, password);
            Statement stmt = null;
            ResultSet rs= null;
            stmt = conn.createStatement();          
            stmt.executeUpdate("INSERT INTO shedule (date, number, type, teacherLoadId) values('150308', '1', '1', '1')");
            System.out.println ("Database connection established");
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
                    System.out.println ("Database connection terminated");
                }
                catch (Exception ex) { }
            }
        }
    }
    
    public String getGroupId(String name){    
        Connection conn = null;
        String id = null;
        try
        {       
            Class.forName ("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection (url, userName, password);
            Statement stmt = null;
            ResultSet rs= null;
            stmt = conn.createStatement();   
            rs = stmt.executeQuery("select * from groups where name = '" + name + "';");
            rs.next();
            id = rs.getString(1);     
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
        return id;
    }
    
    public String[][] getGroupDisciplines(){
        /*
        *В качестве аргумента метод принимает Id группы
        *0 - Id дисциплины
        *1 - Краткое название дисциплины
        */
        
        Connection conn = null;
        String[][] arr = null;
        try {       
            Class.forName ("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection (url, userName, password);
            Statement stmt = null;
            ResultSet rs= null;
            stmt = conn.createStatement();
            
            String q = "Select * from discipline where id in (";
            for (int i = 0; i < Form.groupLoad.length; i++){
                q = q + Form.groupLoad[i][2] + ", ";
            }
            q = q.substring(0, q.length() - 2) + ")";
            
            rs = stmt.executeQuery(q);
            rs.last();
            int e = rs.getRow();
            rs.beforeFirst();
            arr = new String[e][2];
            int i = 0;
            System.out.println("Массив дисциплин группы");
            while (rs.next()){
                arr[i][0] = rs.getString(1);//id исциплины
                arr[i][1] = rs.getString(2);//название дисциплины
                System.out.println(arr[i][0] + " " + arr[i][1]);
                i++;
            }      
        }
        catch (Exception ex) {
            System.err.println ("Cannot connect to database server");
            ex.printStackTrace();
        }
        finally {
            if (conn != null) {
                try {
                    conn.close ();
                }
                catch (Exception ex) { }
            }
        }
        return arr;
    }
    
    public String[][] getGroupTeachers(){
        /*
        *В качестве аргумента метод принимает Id группы
        *0 - Id преподавателя
        *1 - Фамилия И. О. преподавателя
        */
        
        Connection conn = null;
        String[][] arr = null;
        try {       
            Class.forName ("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection (url, userName, password);
            Statement stmt = null;
            ResultSet rs= null;
            stmt = conn.createStatement();
            
            String q = "Select * from teachers where id in (";
            for (int i = 0; i < Form.groupLoad.length; i++){
                q = q + Form.groupLoad[i][1] + ", ";
            }
            q = q.substring(0, q.length() - 2) + ")";
            
            rs = stmt.executeQuery(q);
            rs.last();
            int e = rs.getRow();
            rs.beforeFirst();
            arr = new String[e][2];
            int i = 0;
            System.out.println("Массив преподавателей группы");
            while (rs.next()){
                arr[i][0] = rs.getString(1);//id преподавателя
                arr[i][1] = rs.getString(2) + " " + rs.getString(3).substring(0, 1) + ". " + rs.getString(4).substring(0, 1) + ".";
                System.out.println(arr[i][0] + " " + arr[i][1]);
                i++;
            }      
        }
        catch (Exception ex) {
            System.err.println ("Cannot connect to database server");
            ex.printStackTrace();
        }
        finally {
            if (conn != null) {
                try {
                    conn.close ();
                }
                catch (Exception ex) { }
            }
        }
        return arr;
    }
  

}


