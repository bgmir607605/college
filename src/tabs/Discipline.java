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
public class Discipline {
    Form form;
    String s, f;
    public Discipline (Form form){
        this.form = form;
    }
    public void addDiscipline(){
        s = form.sName.getText();
        f = form.fName.getText();
        if (Checks.notEmpDiscipline(s, f)){
            try {
                new DB().ins("INSERT INTO discipline (shortName, fullName) values('"+ s + "', '" + f + "');");
            } catch (SQLException ex) {
                Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            form.sName.setText(null);
            form.fName.setText(null);
            form.refTab();
        }
        
    }
    public void delDiscipline(){
                /**
         * Ошибка при удалении записи, имеющей внешние связи в БД
         * 
         * 
         */ 
        int sr = form.jTable2.getSelectedRow();
        s = (String) form.jTable2.getValueAt(sr, 0);
        f = (String) form.jTable2.getValueAt(sr, 1);
        try {
            new DB().del("discipline", "shortName = '" + s + "' and fullName = '" + f + "'");
        } catch (SQLException ex) {
            Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
        }
        form.refTab();
    }
}
