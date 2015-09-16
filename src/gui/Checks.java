/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import DB.MainDB;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


//Проверка заполнения ФИО преподавателя
public class Checks {
    
    public static boolean notEmpTeacherLoad(String l, String f, String m, String g, String d){
        if (l.equals("") || f.equals("") || m.equals("")){
            JOptionPane.showMessageDialog(null,"Заполните ФИО преподавателя полностью", "Не все поля заполнены", JOptionPane.DEFAULT_OPTION);
            return false;
        }
        else {
            if (g.equals("") || d.equals("")){
            JOptionPane.showMessageDialog(null,"Укажите группу и дисциплину", "Не все поля заполнены", JOptionPane.DEFAULT_OPTION);
            return false;
        }
            else {
                return true;
            }
        }
    }
    
    public static boolean notEmpTeacher(String l, String f, String m){
        if (l.equals("") || f.equals("") || m.equals("")){
            JOptionPane.showMessageDialog(null,"Заполните ФИО преподавателя полностью", "Не все поля заполнены", JOptionPane.DEFAULT_OPTION);
            return false;
        }
        else {
            return true;
        }
    }
    
    public static boolean notEmpDiscipline(String s, String f){
        if (s.equals("") || f.equals("")){
            JOptionPane.showMessageDialog(null,"Заполните названия дисциплин полностью", "Не все поля заполнены", JOptionPane.DEFAULT_OPTION);
            return false;
        }
        else {
            return true;
        }
    }
    
    public static boolean notEmpSpecialty(String c, String n){
        if (c.equals("") || n.equals("")){
            JOptionPane.showMessageDialog(null,"Заполните сведения о специальности полностью", "Не все поля заполнены", JOptionPane.DEFAULT_OPTION);
            return false;
        }
        else {
            return true;
        }
    }
    
    public static boolean notEmpGroup(String g, String s){
        if (s.equals("") || g.equals("")){
            JOptionPane.showMessageDialog(null,"Заполните названия группы и специальности полностью", "Не все поля заполнены", JOptionPane.DEFAULT_OPTION);
            return false;
        }
        else {
            return true;
        }
    }
     
    //Проверка подключения к БД
    public static boolean testConnect(){
        return MainDB.test();
    }
    
}
