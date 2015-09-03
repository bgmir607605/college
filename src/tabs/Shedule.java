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
 * @author aamir
 */
public class Shedule {
    Form form;
    public Shedule(Form form){
        this.form = form;     
    }
    public void addShedule(){
 
        try {
                new DB().ins("INSERT INTO shedule (`date`, `number`, `type`, `teacherLoadId`, `h`) VALUES ('2015-09-02', '2', 'I', (select id from teacherLoad where teacherId = (select id from teachers where lName = '" + form.sheduleTeacher11.getSelectedItem() + "') and groupId = (select id from groups where name = '" + form.groupShedule.getSelectedItem()  + "') and disciplineId = (select id from discipline where shortName = '" + form.shedule11.getSelectedItem()  + "')), '2');");
                System.out.println("row added");
            } catch (SQLException ex) {
                Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    
}

    
    

