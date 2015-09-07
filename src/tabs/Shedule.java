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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import model.DB;

/**
 *
 * @author aamir
 */
public class Shedule {
    Form form;
    String[][] arrToInsert = new String[10][4];
    String groupId;
    public Shedule(Form form){
        this.form = form;
    }
   /* public String[] uniqArr(String[] arrIn){
        for (int i = 0; i < arrIn.length; i++){
            for (int j = i + 1; j < arrIn.length; j++){
                if (i != j){
                    if (arrIn[j].equals(arrIn[i])){
                    arrIn[i] = "";
                    }
                }
            }
        }
        int u = 0;
        for (int i = 0; i < arrIn.length; i++){
            if (!(arrIn[i].equals(""))){
                u++;
            }
        }
        String[] arrOut = new String[u];
        int j = 0;
        for (int i = 0; i < arrIn.length; i++){
            if (!(arrIn[i].equals(""))){
                arrOut[j] = arrIn[i];
                j++;
            }
        }
        return arrOut;
    }
    public void setArrToInsert(int i, int number, String type, Object discipline, Object lName){
        arrToInsert[i][0] = "" + ++number;
                    arrToInsert[i][1] = type;
                    arrToInsert[i][2] = (String) discipline;
                    arrToInsert[i][2] = (String) lName;
    }
    public void initArrToInsert(){
        int k = 0;
        for (int i = 0; i < arrCheck.length; i++){
            if (arrCheck[i].isSelected()){
                if (arrComboDiscipline[i + i].getSelectedIndex() != -1){
                    setArrToInsert(k, i, "", arrComboDiscipline[i + i].getSelectedItem(), arrCombolName[i + i].getSelectedItem());
                }
                k =  k + 2;
            }
            else{
                if (arrComboDiscipline[i + i].getSelectedIndex() != -1){
                    setArrToInsert(k, i, "I", arrComboDiscipline[i + i].getSelectedItem(), arrCombolName[i + i].getSelectedItem());
                }
                k++;
                if (arrComboDiscipline[i + i + 1].getSelectedIndex() != -1){
                    setArrToInsert(k, i, "II", arrComboDiscipline[i + i + 1].getSelectedItem(), arrCombolName[i + i + 1].getSelectedItem());
                }
                k++;
            }
        }
    }
    public void addShedule(){
        initArrToInsert();
        String values = null;
        String date = "2015-09-02";
        String value ="('2015-09-02', '2', 'I', (select id from teacherLoad where teacherId = (select id from teachers where lName = '" + form.sheduleTeacherLName11.getSelectedItem() + "') and groupId = (select id from groups where name = '" + form.groupShedule.getSelectedItem()  + "') and disciplineId = (select id from discipline where shortName = '" + form.shedule11.getSelectedItem()  + "')), '2')";
        for (int i = 0; i < arrToInsert.length - 1; i++){
            values = values + "('" + date + "', '" + arrToInsert[i][0] + "', '" + arrToInsert[i][1] + "', (select id from teacherLoad where teacherId = (select id from teachers where lName = '" + arrToInsert[i][3] + "') and groupId = (select id from groups where name = '" + form.groupShedule.getSelectedItem()  + "') and disciplineId = (select id from discipline where shortName = '" + arrToInsert[i][2] + "')), '2'), ";
        }
        //values = values + "('" + date + "', '" + arrToInsert[i][0] + "', '" + arrToInsert[i][1] + "', (select id from teacherLoad where teacherId = (select id from teachers where lName = '" + arrToInsert[i][3] + "') and groupId = (select id from groups where name = '" + form.groupShedule.getSelectedItem()  + "') and disciplineId = (select id from discipline where shortName = '" + arrToInsert[i][2] + "')), '2');";
        try {
                new DB().ins("INSERT INTO shedule (`date`, `number`, `type`, `teacherLoadId`, `h`) VALUES ('2015-09-02', '2', 'I', (select id from teacherLoad where teacherId = (select id from teachers where lName = '" + form.sheduleTeacherLName11.getSelectedItem() + "') and groupId = (select id from groups where name = '" + form.groupShedule.getSelectedItem()  + "') and disciplineId = (select id from discipline where shortName = '" + form.shedule11.getSelectedItem()  + "')), '2');");
                System.out.println("row added");
            } catch (SQLException ex) {
                Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
            }
        }*/
    

}

    
    

