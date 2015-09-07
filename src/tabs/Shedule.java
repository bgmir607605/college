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
    public Shedule(Form form, String groupId){
        this.form = form;
        this.groupId = groupId;
    }
    public void addShedule(){
        initArrToInsert();
        String quary = "INSERT INTO shedule (`date`, `number`, `type`, `teacherLoadId`, `h`) VALUES ";
        String date = "2015-09-07";
        for (int i = 0; i < arrToInsert.length - 1; i++){
            if (arrToInsert[i][0] != null){
                System.out.println("1");
                quary = quary + "('" + date + "', '" + arrToInsert[i][0] + "', '" + arrToInsert[i][1] + "', (select id from teacherLoad where teacherId = (select id from teachers where lName = '" + arrToInsert[i][3] + "') and groupId = '" + groupId +"' and disciplineId = (select id from discipline where shortName = '" + arrToInsert[i][2] + "')), '2'), ";
            }
        }
        if (arrToInsert[arrToInsert.length - 1][0] == null){
            quary = quary.substring(0, quary.length() - 2);
        }
        else{
            quary = quary + "('" + date + "', '" + arrToInsert[arrToInsert.length - 1][0] + "', '" + arrToInsert[arrToInsert.length - 1][1] + "', (select id from teacherLoad where teacherId = (select id from teachers where lName = '" + arrToInsert[arrToInsert.length - 1][3] + "') and groupId = '" + groupId +"' and disciplineId = (select id from discipline where shortName = '" + arrToInsert[arrToInsert.length - 1][2] + "')), '2')";
        }
        quary = quary + ";";
        try {
                new DB().ins(quary);
                System.out.println("row added");
            } catch (SQLException ex) {
                Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    public void initArrToInsert(){
        int k = 0;
        for (int i = 0; i < 5; i++){
            if (form.getValueCheck(i)){
                if (form.isNotEmptyLesson(i + i)){
                    setArrToInsert(k, i, "", form.getValueComboDiscipline(i + i), form.getValueCombolName(i + i));
                }
                k =  k + 2;
            }
            else{
                if (form.isNotEmptyLesson(i + i)){
                    setArrToInsert(k, i, "I", form.getValueComboDiscipline(i + i), form.getValueCombolName(i + i));
                }
                k++;
                if (form.isNotEmptyLesson(i + i + 1)){
                    setArrToInsert(k, i, "II", form.getValueComboDiscipline(i + i + 1), form.getValueCombolName(i + i + 1));
                }
                k++;
            }
        }
    }
    public void setArrToInsert(int i, int number, String type, Object discipline, Object lName){
        arrToInsert[i][0] = "" + ++number;
                    arrToInsert[i][1] = type;
                    arrToInsert[i][2] = (String) discipline;
                    arrToInsert[i][3] = (String) lName;
    }
    public String[] uniqArr(String[] arrIn){
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
    
    

}

    
    

