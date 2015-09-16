/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DB;

import static DB.DB.conn;
import java.sql.DriverManager;

/**
 *
 * @author aamir
 */
public class SheduleDB extends DB {
    public String getGroupId(String n){
        String id = null;
        try
        {       
            Class.forName ("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection (url, userName, password);   
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select id from groups where name ='" + n + "';");
            while (rs.next()){
                id = rs.getString(1);
            }          
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally {closeCon();}
        return id;
    }
    
    /**
     * Возвращает массив нагрузок по группе
     * [0] - id нагрузки
     * [1] - id преподавателя
     * [2] - id дисциплины
     * @param w
     * @return 
     */
    public String[][] getArrLoads(String w){
        String[][] arr = null;
        try
        {       
            Class.forName ("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection (url, userName, password);   
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT id, teacherId, disciplineId from teacherLoad where groupId = '" + w + "';");
            rs.last();
            int m = rs.getRow();
            rs.beforeFirst();
            arr = new String[m][3];
            int i = 0;
            while (rs.next()){
                for (int j = 1; j <= 3; j++){
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
    
    /**
     * отобрать уникальные ид преподавателей и вернуть массив ид Ф И О
     * @param arrLoads
     * @return 
     */
    public String[][] getArrTeachers(String[][] arrLoads){
        String s = "";
        for (int i = 0; i < arrLoads.length;i++){
            s = s + arrLoads[i][0] + ", ";
        }
        s = s.substring(0, s.length() - 2);
        String[][] arr = null;
        try
        {       
            Class.forName ("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection (url, userName, password);   
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * from teachers where id in ((select distinct teacherId from teacherLoad where id in (" + s + ")));");
            rs.last();
            int m = rs.getRow();
            rs.beforeFirst();
            int n = 4;
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
        for (int i = 0; i < arr.length; i++){
            System.out.println(arr [i][0] + arr [i][1] + arr [i][2] + arr [i][3]);
        }
        return arr;
    }
    public String[][] getArrDisciplines(String[][] arrLoads){
        String s = "";
        for (int i = 0; i < arrLoads.length;i++){
            s = s + arrLoads[i][0] + ", ";
        }
        s = s.substring(0, s.length() - 2);
        String[][] arr = null;
        try
        {       
            Class.forName ("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection (url, userName, password);   
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT id, shortName from discipline where id in ((select distinct disciplineId from teacherLoad where id in (" + s + ")));");
            rs.last();
            int m = rs.getRow();
            rs.beforeFirst();
            int n = 2;
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
        for (int i = 0; i < arr.length; i++){
            System.out.println(arr [i][0] + arr [i][1]);
        }
        return arr;
    }
    
}
