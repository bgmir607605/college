/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shedule;

import DB.SheduleDB;
import model.LoadsForGroup;

/**
 *
 * @author aamir
 */
public class AvailableShedule {
    String[][] AvailableShedule;
    String date;
    Shedule shedule;
    public AvailableShedule(Shedule shedule){
        this.date = shedule.comboDate.getDate();
        this.shedule = shedule;
        AvailableShedule = new SheduleDB().getArrAvailableShedule(shedule.loads.getStringOfTeacherLoadsId(), date);
        setAvailableShedule();
    }

    void setAvailableShedule(){
        for (int i = 0; i < AvailableShedule.length; i++){
            setAvailableLesson(AvailableShedule[i]);
        }
    }
     void setAvailableLesson(String[] lesson){
        String type = lesson[1];
        String teacherLoadId = lesson[2];
        
        int number = Integer.parseInt(lesson[0]) - 1;
        String discipline = shedule.loads.getDisciplineOnTeacherLoadId(teacherLoadId);
        String lName = shedule.loads.getLNameTeacherOnTeacherLoadId(teacherLoadId);

        shedule.setLesson(type, number, discipline, lName);
        
        
    }
    
}
