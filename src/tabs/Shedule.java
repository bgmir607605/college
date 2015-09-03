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
import javax.swing.JComboBox;

/**
 *
 * @author aamir
 */
public class Shedule {
    Form form;
    JComboBox[] arrComboDiscipline = new JComboBox[10];
    JComboBox[] arrCombolName = new JComboBox[10];
    
    //arrComboDiscipline.add = {form.shedule11, form.shedule12, form.shedule21, form.shedule22,
    //form.shedule31, form.shedule32, form.shedule41, form.shedule42, form.shedule51, form.shedule52};
    public Shedule(Form form){
        this.form = form;
        arrComboDiscipline[0] = form.shedule11;
        arrComboDiscipline[1] = form.shedule12;
        arrComboDiscipline[2] = form.shedule21;
        arrComboDiscipline[3] = form.shedule22;
        arrComboDiscipline[4] = form.shedule31;
        arrComboDiscipline[5] = form.shedule32;
        arrComboDiscipline[6] = form.shedule41;
        arrComboDiscipline[7] = form.shedule42;
        arrComboDiscipline[8] = form.shedule51;
        arrComboDiscipline[9] = form.shedule52;
        
        arrCombolName[0] = form.sheduleTeacher11;
        arrCombolName[1] = form.sheduleTeacher12;
        arrCombolName[2] = form.sheduleTeacher21;
        arrCombolName[3] = form.sheduleTeacher22;
        arrCombolName[4] = form.sheduleTeacher31;
        arrCombolName[5] = form.sheduleTeacher32;
        arrCombolName[6] = form.sheduleTeacher41;
        arrCombolName[7] = form.sheduleTeacher42;
        arrCombolName[8] = form.sheduleTeacher51;
        arrCombolName[9] = form.sheduleTeacher52;
        
    }
    public void addShedule(){
 
        try {
                new DB().ins("INSERT INTO shedule (`date`, `number`, `type`, `teacherLoadId`, `h`) VALUES ('2015-09-02', '2', 'I', (select id from teacherLoad where teacherId = (select id from teachers where lName = '" + form.sheduleTeacher11.getSelectedItem() + "') and groupId = (select id from groups where name = '" + form.groupShedule.getSelectedItem()  + "') and disciplineId = (select id from discipline where shortName = '" + form.shedule11.getSelectedItem()  + "')), '2');");
                System.out.println("row added");
            } catch (SQLException ex) {
                Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    public void setGroup(){
        for (int i = 0; i < arrComboDiscipline.length; i++){
            arrComboDiscipline[i].removeAllItems();
        }
        DB d = new DB();
        String[] a = d.getBoxList("shortName", "discipline", "id in ((select distinct disciplineId from teacherLoad where groupId = (select distinct id from groups where name = '" + form.groupShedule.getSelectedItem()  + "')))");
        for (int i = 0; i < a.length; i++){
            for (int k = 0; k < arrComboDiscipline.length; k++){
            arrComboDiscipline[k].addItem(a[i]);
            }
   
            
        }
        for (int i = 0; i < arrComboDiscipline.length; i++){
            arrComboDiscipline[i].setSelectedIndex(-1);
        }
    }
    public void setDiscipline(int x){
        arrCombolName[x].removeAllItems();
        DB d = new DB();
        String[] a = d.getBoxList("lName", "teachers", "id in ((select teacherId from teacherLoad where groupId = (select distinct id from groups where name = '" + form.groupShedule.getSelectedItem()  + "') and disciplineId = (select distinct id from discipline where shortName = '" + form.shedule11.getSelectedItem()  + "')))");
        for (int i = 0; i < a.length; i++){
            arrCombolName[x].addItem(a[i]);
        }
    }
    
}

    
    

