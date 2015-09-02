/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tabs;

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
    String l, f, m, g, d;
    public TeacherLoad(Form form){
        this.form = form;     
    }
    
    public void addTeacherLoad(){
        l = (String) form.comboLName.getSelectedItem();
        f = (String) form.comboFName.getSelectedItem();
        m = (String) form.comboMName.getSelectedItem();
        g = (String) form.comboGroup.getSelectedItem();
        d = (String) form.comboDiscipline.getSelectedItem();
        if (Checks.notEmpTeacher(l, f, m)){    
        try {
                new DB().ins("insert into teacherLoad (teacherId, groupId, disciplineId)" +
                        "values ((select id from teachers where lName ='" + l + "' and fName ='" + f + "' " +
                        "and mName ='" + m + "'), (select id from groups where name ='" + g + "'), " +
                        "(select id from discipline where shortName ='" + d + "'));");
            } catch (SQLException ex) {
                Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
            }
        form.comboLName.setSelectedItem(null);
        form.comboFName.setSelectedItem(null);
        form.comboMName.setSelectedItem(null);
        form.comboGroup.setSelectedItem(null);
        form.comboDiscipline.setSelectedItem(null);
        form.refTab();
        }
    }
    public void delTeacherLoad(){
                /**
         * Ошибка при удалении записи, имеющей внешние связи в БД
         * 
         * 
         */ 
        int sr = form.jTable5.getSelectedRow();
        l = (String) form.jTable5.getValueAt(sr, 0);
        f = (String) form.jTable5.getValueAt(sr, 1);
        m = (String) form.jTable5.getValueAt(sr, 2);
        g = (String) form.jTable5.getValueAt(sr, 3);
        d = (String) form.jTable5.getValueAt(sr, 4);
        
        try {
            new DB().del("teacherLoad", "teacherId = (select id from teachers where fName = '" + f + "' and lName = '" + l + "' and mName = '" + m + "') and groupId = (select id from groups where name = '" + g + "') and disciplineId = (select id from discipline where shortName = '" + d + "')");
        } catch (SQLException ex) {
            Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
        }
        form.refTab();
    }
}
