/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import DB.MainDB;
import DB.SheduleDB;
import gui.Form;
import tabs.Shedule;

/**
 *
 * @author aamir
 */
public class LoadsForGroup {
    Shedule tab;
    String[][] arrLoads;
    String[][] arrTeachers;
    String[][] arrDisciplines;
    static String groupId;
    public LoadsForGroup(Shedule tab){
        this.tab = tab;
    }
    public void setGroupId(String nameGroup){
        groupId = new SheduleDB().getGroupId(nameGroup);
    }
    public String getGroupId(){
        return groupId;
    }
    public void setArrLoads(){
        arrLoads = new SheduleDB().getArrLoads(groupId);
    }
    public void setArrTeachers(){
        arrTeachers = new SheduleDB().getArrTeachers(arrLoads);
    }
    public void setArrDisciplines(){
        arrDisciplines = new SheduleDB().getArrDisciplines(arrLoads);
    }
    public String[] getArrDisciplines(){
        String[] arr = new String[arrDisciplines.length];
        for (int k = 0; k < arrDisciplines.length; k++){
            arr[k] = arrDisciplines[k][1];
            }
        return arr;
    }

    public String[] getArrTeacherForDiscipline(String discipline){
        //По названию дисциплины узнать id
        String disciplineId = null;
        for (int i = 0; i < arrDisciplines.length; i++){
                    if (arrDisciplines[i][1].equals(discipline)){
                        disciplineId = arrDisciplines[i][0];
                    }
            }
        //В каких нагрузках есть эта дисциплина - считаем количество
        int length = 0;
        for (int i = 0; i < arrLoads.length; i++){
                    if (arrLoads[i][2].equals(disciplineId)){
                        length++;
                    }
            }
        String[] arrOut = new String[length];
        //Заполнить 
        int j = 0;
        for (int i = 0; i < arrLoads.length; i++){
                    if (arrLoads[i][2].equals(disciplineId)){
                        arrOut[j] = arrLoads[i][1];
                        j++;
                    }
        }
        for (int i = 0; i < arrOut.length; i++){
            for (int k = 0; k < arrTeachers.length; k++){
                if (arrOut[i].equals(arrTeachers[k][0])){
                        arrOut[i] = arrTeachers[k][3];
                    }
            }
                    
        }
        return arrOut;
    }
    
}
