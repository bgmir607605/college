/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

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
    
    //Параметры подключения
    static String userName = "adminBPT";
    static String password = "13579!Aa";
    static String url = "jdbc:mysql://188.120.245.21:3306/bpt?zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=utf8";
    static Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
   
    //Проверка подключенния
    public static boolean test(){
        try
        {       
            Class.forName ("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection (url, userName, password);
            return true;
        }
        catch (Exception ex)
        {
            return false;
        }
        finally {
            closeCon();
        }
    }
    
    //Закрытие соединения
    static void closeCon(){
        if (conn != null)
            {
                try
                {
                    conn.close ();
                }
                catch (Exception ex) { }
            }
    }
        
    //Получить таблицу
    public String[][] getTab(String t){
        String[][] arr = null;
        try
        {       
            Class.forName ("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection (url, userName, password);   
            stmt = conn.createStatement();
                rs = stmt.executeQuery("select * from " + t + ";");
                
                if (t.equals("groups")){
                    rs = stmt.executeQuery("SELECT groups.name, specialty.name "
                        + "FROM groups LEFT OUTER JOIN specialty "
                        + "ON groups.specialtyId=specialty.id;");
                }
                if (t.equals("teacherLoad")){
                    rs = stmt.executeQuery("SELECT teachers.lName, "
                        + "teachers.fName, teachers.mName, groups.name, "
                        + "discipline.shortName FROM teacherLoad "
                        + "LEFT OUTER JOIN teachers ON teacherLoad.teacherId=teachers.id "
                        + "LEFT OUTER JOIN groups ON teacherLoad.groupId=groups.id "
                        + "LEFT OUTER JOIN discipline ON teacherLoad.disciplineId=discipline.id;");
                }
            rs.last();
            int m = rs.getRow();
            int n = rs.getMetaData().getColumnCount();
            rs.beforeFirst();
            arr = new String[m][n];
            int i = 0;
            while (rs.next()){
                for (int j = 1; j <= n; j++){
                    arr[i][--j] = rs.getString(++j);
                }
                i++;
            }          
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally {closeCon();}
        return arr;
    }
    
    //Получить список для КомбоБоксов без условий
    public String[] getBoxList(String f, String t){
        String[] arr = null;
        try
        {       
            Class.forName ("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection (url, userName, password);   
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select " + f + " from " + t + ";");
            rs.last();
            int m = rs.getRow();
            rs.beforeFirst();
            arr = new String[m];
            int i = 0;
            while (rs.next()){
                arr[i] = rs.getString(1);
                i++;
            }          
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally {closeCon();}
        return arr;
    }
    
    //Получить список для КомбоБоксов с условием
    public String[] getBoxList(String f, String t, String w){
        String[] arr = null;
        try
        {       
            Class.forName ("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection (url, userName, password);   
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select " + f + " from " + t + " where " + w + ";");
            rs.last();
            int m = rs.getRow();
            rs.beforeFirst();
            arr = new String[m];
            int i = 0;
            while (rs.next()){
                arr[i] = rs.getString(1);
                i++;
            }          
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally {closeCon();}
        return arr;
    }
    
    public void ins(String q) throws SQLException{
        try
        {       
            Class.forName ("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection (url, userName, password);
            stmt = conn.createStatement();          
            stmt.executeUpdate(q);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            closeCon();
        }  
    }
    
    public void del(String t, String w) throws SQLException{
        try
        {       
            Class.forName ("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection (url, userName, password);
            stmt = conn.createStatement();
            stmt.executeUpdate("delete from " + t + " where " + w + ";");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }    
        finally
        {
            closeCon();
        }  
    }
    
    public void delUpdate(String q) throws SQLException{
        try
        {       
            Class.forName ("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection (url, userName, password);
            stmt = conn.createStatement();          
            stmt.executeUpdate(q);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            closeCon();
        }       
    }
    
}
