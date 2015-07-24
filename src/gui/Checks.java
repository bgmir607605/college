/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import model.DB;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


//Проверка заполнения ФИО преподавателя
public class Checks {
    public static boolean notEmpTeacher(String l, String f, String m){
        if (l.equals("") || f.equals("") || m.equals("")){
            JOptionPane.showMessageDialog(null,"Заполните ФИО преподавателя полностью", "Не все поля заполнены", JOptionPane.DEFAULT_OPTION);
            return false;
        }
        else {
            return true;
        }
    }
     
    //Проверка подключения к БД
    public static boolean testConnect(){
        return DB.test();
    }
    
}
