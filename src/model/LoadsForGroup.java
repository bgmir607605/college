/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import gui.Form;

/**
 *
 * @author aamir
 */
public class LoadsForGroup {
    Form form;
    String[][] arrLoads;
    static String groupId;
    public LoadsForGroup(Form form){
        this.form = form;
    }
    public void setGroupId(String nameGroup){
        groupId = new DB().getGroupId(nameGroup);
    }
    public String getGroupId(){
        return groupId;
    }
    public void getArrLoads(){
        arrLoads = new DB().getTab("teacherLoad", "groupId = '" + groupId + "'");
    }
    public String[] getArrDisciplines(){
        String[] arr = new String[arrLoads.length];
        for (int k = 0; k < arrLoads.length; k++){
            arr[k] = arrLoads[k][3];
            }
        return arr;
    }
    public void setDisciplines(){
        form.setGroupShedule(getArrDisciplines());
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
