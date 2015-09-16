/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author aamir
 */
public class DB {
    static String userName = "adminBPT";
    static String password = "13579!Aa";
    static String url = "jdbc:mysql://188.120.245.21:3306/bpt?zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=utf8";
    static Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    
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
}
