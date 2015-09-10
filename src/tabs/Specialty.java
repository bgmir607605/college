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
public class Specialty {
    Form form;
    String codeSpecialty, nameSpecialty;
    public Specialty(Form form){
        this.form = form;
    }
    public void addSpecialty(){
        //codeSpecialty = form.codeSpec.getText();
        //nameSpecialty = form.nameSpec.getText();
        if (Checks.notEmpDiscipline(codeSpecialty, nameSpecialty)){
            try {
                new DB().ins("INSERT INTO specialty (code, name) values('"+ codeSpecialty + "', '" + nameSpecialty + "');");
            } catch (SQLException ex) {
                Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            //form.codeSpec.setText(null);
            //form.nameSpec.setText(null);
            form.refTab();
        }
        
    }
    public void delSpecialty(){
                /**
         * Ошибка при удалении записи, имеющей внешние связи в БД
         * 
         * 
         */ 
        //int sr = form.jTable3.getSelectedRow();
        //codeSpecialty = (String) form.jTable3.getValueAt(sr, 0);
        //nameSpecialty = (String) form.jTable3.getValueAt(sr, 1);
        try {
            new DB().del("specialty", "code = '" + codeSpecialty + "' and name = '" + nameSpecialty + "'");
        } catch (SQLException ex) {
            Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
        }
        form.refTab();
    }
}
