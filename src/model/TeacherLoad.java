/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import gui.Checks;
import gui.Form;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DB;

/**
 *
 * @author Andrey
 */
public class TeacherLoad {
    Form form;
    String lName, fName, mName, group, discipline;
    public TeacherLoad(Form form){
        this.form = form;     
    }
    
    public void addTeacherLoad(){
        //lName = (String) form.comboLName.getSelectedItem();
        //fName = (String) form.comboFName.getSelectedItem();
        //mName = (String) form.comboMName.getSelectedItem();
        //group = (String) form.comboGroup.getSelectedItem();
        //discipline = (String) form.comboDiscipline.getSelectedItem();
        if (Checks.notEmpTeacher(lName, fName, mName)){    
        try {
                new DB().ins("insert into teacherLoad (teacherId, groupId, disciplineId)" +
                        "values ((select id from teachers where lName ='" + lName + "' and fName ='" + fName + "' " +
                        "and mName ='" + mName + "'), (select id from groups where name ='" + group + "'), " +
                        "(select id from discipline where shortName ='" + discipline + "'));");
            } catch (SQLException ex) {
                Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
            }
        //form.comboLName.setSelectedItem(null);
        //form.comboFName.setSelectedItem(null);
        //form.comboMName.setSelectedItem(null);
        //form.comboGroup.setSelectedItem(null);
        //form.comboDiscipline.setSelectedItem(null);
        form.refTab();
        }
    }
    public void delTeacherLoad(){
                /**
         * Ошибка при удалении записи, имеющей внешние связи в БД
         * 
         * 
         */ 
        //int sr = form.jTable5.getSelectedRow();
        //lName = (String) form.jTable5.getValueAt(sr, 0);
        //fName = (String) form.jTable5.getValueAt(sr, 1);
        //mName = (String) form.jTable5.getValueAt(sr, 2);
        //group = (String) form.jTable5.getValueAt(sr, 3);
        //discipline = (String) form.jTable5.getValueAt(sr, 4);
        
        try {
            new DB().del("teacherLoad", "teacherId = (select id from teachers where fName = '" + fName + "' and lName = '" + lName + "' and mName = '" + mName + "') and groupId = (select id from groups where name = '" + group + "') and disciplineId = (select id from discipline where shortName = '" + discipline + "')");
        } catch (SQLException ex) {
            Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
        }
        form.refTab();
    }
}
