/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tabs;

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
    Form f;
    public Teacher(Form f){
        this.f = f;
    }
    
    public void addTeacher(){
            try {
                DB d = new DB();
                d.ins("INSERT INTO teachers (fName, mName, lName) values('"+ f.fNameTeacher.getText() + "', '"+ f.mNameTeacher.getText() + "', '" + f.lNameTeacher.getText() + "');");
            } catch (SQLException ex) {
                Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
            }
        f.fNameTeacher.setText(null);
        f.mNameTeacher.setText(null);
        f.lNameTeacher.setText(null);
        f.refTab();
    }
    
}
