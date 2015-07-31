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
import gui.Form;

/**
 *
 * @author Andrey
 */
public class Teacher {
    Form form;
    String l, f, m;
    public Teacher(Form form){
        this.form = form;     
    }
    
    public void addTeacher(){
        l = form.lNameTeacher.getText();
        f = form.fNameTeacher.getText();
        m = form.mNameTeacher.getText();
        if (Checks.notEmpTeacher(l, f, m)){    
        try {
                new DB().ins("INSERT INTO teachers (fName, mName, lName) values('"+ f + "', '"+ m + "', '" + l + "');");
            } catch (SQLException ex) {
                Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
            }
        form.fNameTeacher.setText(null);
        form.mNameTeacher.setText(null);
        form.lNameTeacher.setText(null);
        form.refTab();
        }
    }
    public void delTeacher(){
                /**
         * Ошибка при удалении записи, имеющей внешние связи в БД
         * 
         * 
         */ 
        int sr = form.jTable1.getSelectedRow();
        l = (String) form.jTable1.getValueAt(sr, 0);
        f = (String) form.jTable1.getValueAt(sr, 1);
        m = (String) form.jTable1.getValueAt(sr, 2);
        try {
            new DB().del("teachers", "lName = '" + l + "' and fName = '" + f + "' and mName = '" + m + "'");
        } catch (SQLException ex) {
            Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
        }
        form.refTab();
    }
}
