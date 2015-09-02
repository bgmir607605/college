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
public class Group {
    Form form;
    String nG, nS, idS;
    public Group(Form form){
        this.form = form;
    }
    public void addGroup(){
        nG = form.nameGroup.getText();
        nS = (String) form.nameSpecOfGroup.getSelectedItem();
        if (Checks.notEmpGroup(nG, nS)){    
        try {
                new DB().ins("INSERT INTO groups (name, specialtyId) values('"+ nG + "', (select id from specialty where name = '" + nS + "'));");
            } catch (SQLException ex) {
                Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
            }
        form.nameGroup.setText(null);
        form.nameSpecOfGroup.setSelectedItem(null);
        form.refTab();
        }
    }
    public void delGroup(){
                /**
         * Ошибка при удалении записи, имеющей внешние связи в БД
         * 
         * 
         */ 
        int sr = form.jTable4.getSelectedRow();
        nG = (String) form.jTable4.getValueAt(sr, 0);
        try {
            new DB().del("groups", "name = '" + nG + "'");
        } catch (SQLException ex) {
            Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
        }
        form.refTab();
    }
}
