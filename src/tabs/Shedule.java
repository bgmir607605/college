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
    String[][] arrLoads;
    JComboBox[] arrComboDiscipline = new JComboBox[10];
    JComboBox[] arrCombolName = new JComboBox[10];
    JCheckBox[] arrCheck = new JCheckBox[5];
    String[][] arrToInsert = new String[10][4];
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
        
        arrCombolName[0] = form.sheduleTeacherLName11;
        arrCombolName[1] = form.sheduleTeacherLName12;
        arrCombolName[2] = form.sheduleTeacherLName21;
        arrCombolName[3] = form.sheduleTeacherLName22;
        arrCombolName[4] = form.sheduleTeacherLName31;
        arrCombolName[5] = form.sheduleTeacherLName32;
        arrCombolName[6] = form.sheduleTeacherLName41;
        arrCombolName[7] = form.sheduleTeacherLName42;
        arrCombolName[8] = form.sheduleTeacherLName51;
        arrCombolName[9] = form.sheduleTeacherLName52;
        
        arrCheck[0] = form.check1;
        arrCheck[1] = form.check2;
        arrCheck[2] = form.check3;
        arrCheck[3] = form.check4;
        arrCheck[4] = form.check5;
        
        //Получить массив нагрузок по группе
        arrLoads = new DB().getTab("teacherLoad", "groupId = (select id from groups where name = '" + form.groupShedule.getSelectedItem() + "')");
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
        String values = null;
        String date = "2015-09-02";
        String value ="('2015-09-02', '2', 'I', (select id from teacherLoad where teacherId = (select id from teachers where lName = '" + form.sheduleTeacherLName11.getSelectedItem() + "') and groupId = (select id from groups where name = '" + form.groupShedule.getSelectedItem()  + "') and disciplineId = (select id from discipline where shortName = '" + form.shedule11.getSelectedItem()  + "')), '2')";
        for (int i = 0; i < arrToInsert.length - 1; i++){
            values = values + "('" + date + "', '" + arrToInsert[i][0] + "', '" + arrToInsert[i][1] + "', (select id from teacherLoad where teacherId = (select id from teachers where lName = '" + arrToInsert[i][3] + "') and groupId = (select id from groups where name = '" + form.groupShedule.getSelectedItem()  + "') and disciplineId = (select id from discipline where shortName = '" + arrToInsert[i][2] + "')), '2'), ";
        }
        values = values + "('" + date + "', '" + arrToInsert[i][0] + "', '" + arrToInsert[i][1] + "', (select id from teacherLoad where teacherId = (select id from teachers where lName = '" + arrToInsert[i][3] + "') and groupId = (select id from groups where name = '" + form.groupShedule.getSelectedItem()  + "') and disciplineId = (select id from discipline where shortName = '" + arrToInsert[i][2] + "')), '2');";
        try {
                new DB().ins("INSERT INTO shedule (`date`, `number`, `type`, `teacherLoadId`, `h`) VALUES ('2015-09-02', '2', 'I', (select id from teacherLoad where teacherId = (select id from teachers where lName = '" + form.sheduleTeacherLName11.getSelectedItem() + "') and groupId = (select id from groups where name = '" + form.groupShedule.getSelectedItem()  + "') and disciplineId = (select id from discipline where shortName = '" + form.shedule11.getSelectedItem()  + "')), '2');");
                System.out.println("row added");
            } catch (SQLException ex) {
                Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    public void setGroup(){
        //Очистить комбобоксы с названиями дисциплин
        for (int i = 0; i < arrComboDiscipline.length; i++){
            arrComboDiscipline[i].removeAllItems();
        }
        //Добавить названия дисциплин в комбобоксы
        for (int i = 0; i < arrLoads.length; i++){
            for (int k = 0; k < arrComboDiscipline.length; k++){
            arrComboDiscipline[k].addItem(arrLoads[i][3]);
            }
        }
        //Установить по умолчанию "окна"
        for (int i = 0; i < arrComboDiscipline.length; i++){
            arrComboDiscipline[i].setSelectedIndex(-1);
        }
    }
    public void setDiscipline(int x){
        
        arrCombolName[x].removeAllItems();
        String[] a = this.getArrTeacherForDiscipline((String)arrComboDiscipline[x].getSelectedItem());
        for (int i = 0; i < a.length; i++){
            arrCombolName[x].addItem(a[i]);
        }
                
    }
    public void setTotalLesson(int x){
        if (arrCheck[x].isSelected()){
            arrComboDiscipline[x + x + 1].setEnabled(false);
        }
        else{
            arrComboDiscipline[x + x + 1].setEnabled(true);
        }
        
    }
    public String[] getArrTeacherForDiscipline(String discipline){
        //Посчитать размер возвращаемого массива (Количество нахождений дисциплины)
        int length = 0;
        for (int i = 0; i < arrLoads.length; i++){
                    if (arrLoads[i][3].equals(discipline)){
                        length++;
                    }
            }
        //Сформировать возвращаемый массив
        String[] arrOut = new String[length];
        int j = 0;
        for (int i = 0; i < arrLoads.length; i++){
                    if (arrLoads[i][3].equals(discipline)){
                        arrOut[j] = arrLoads[i][0];
                        j++;
                    }
        }
        return arrOut;
    }
}

    
    

