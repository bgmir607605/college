/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shedule;

import DB.MainDB;
import DB.SheduleDB;
import gui.Form;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.LoadsForGroup;

/**
 *
 * @author aamir
 */
public class Shedule extends javax.swing.JPanel {
    public static LoadsForGroup loads = null;
    AvailableShedule available;
    LessonPanel[] arrLessonPanels;

    public Shedule() {
        initComponents();
        initArrLessons();
        comboDate.form = this;
        comboDate.initDate();
    }

    /**
     * Добавить значения в comboBox групп
     */
    void setGroupsBox(){
        groupShedule.removeAllItems();
        String[] a = new MainDB().getBoxList("name", "groups");
        for (String a1 : a) {
            groupShedule.addItem(a1);
        }
    }
    public void setAvailable(){
        resetLessons();
        if (loads != null){
            if (new SheduleDB().isAvailableShedule(loads.getStringOfTeacherLoadsId(), comboDate.getDate())){
                available = new AvailableShedule(this);
            }
            else{
                
            }
        }
        
    }
    
    void selectGroup(){
        loads = new LoadsForGroup((String) groupShedule.getSelectedItem());
        for (LessonPanel lesson: arrLessonPanels){
            lesson.setDisciplinesBox();
        }
        setAvailable();
    }
    
    public void setLesson(String type, int number , String discipline, String lName){
        arrLessonPanels[number].setLesson(type, discipline, lName);
    }

    //Конец интерфейсной части//
    //Обработка добавления расписания//
    
    /**
     * Добавить расписание
     */
    String getQuaryToInsert(){
        String[][] arrToInsert = initArrToInsert();
        initArrToInsert();
        String quary = "INSERT INTO shedule (`date`, `number`, `type`, `teacherLoadId`, `h`) VALUES ";
        String date = comboDate.getDate();
        for (int i = 0; i < arrToInsert.length; i++){
            if (arrToInsert[i][0] != null){
                quary = quary + "('" + date + "', '" + arrToInsert[i][0] + "', '" + arrToInsert[i][1] + "', '" + arrToInsert[i][2] + "', '2'), ";
            }
        }
        quary = quary.substring(0, quary.length() - 2) + ";";
        return quary;
    }
    
    public void addShedule(){
        dellAvailableLessons();
        String quary = getQuaryToInsert();;
        System.out.println(quary);
        try {
                new MainDB().ins(quary);
                JOptionPane.showMessageDialog(null,"Расписание добавлено", "Сообщение системы", JOptionPane.DEFAULT_OPTION);
            } catch (SQLException ex) {
                Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    void dellAvailableLessons(){
        String quary = "";
        if (available != null){
            quary = "delete from shedule where teacherLoadId in (" + loads.getStringOfTeacherLoadsId() + ") and date ='" + comboDate.getDate() + "'; ";
        
            System.out.println(quary);
            try {
                new MainDB().ins(quary);
            } catch (SQLException ex) {
                Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
    /**
     * |   0    |  1   |     2         |
     * | number | type | teacherLoadId |
     * @return 
     */
    public String[][] initArrToInsert(){
        int i = 0;
        String[][] arrToInsert = new String[10][3];
        while (i < 10){
            for (LessonPanel arrLessonPanel : arrLessonPanels) {
                String[][] les = arrLessonPanel.getArrToInsert();
                for (String[] row: les){
                    arrToInsert[i] = row;
                    i++;
                }
            }
        }
        return arrToInsert;
    }

    void resetLessons(){
        for (LessonPanel lesson: arrLessonPanels){
            lesson.resetLesson();
        }
    }
    
    //////////////////////////////
    //                          //
    //  Автогенерированный код  //
    //                          //
    //////////////////////////////
    public final void initArrLessons(){

        arrLessonPanels = new LessonPanel[5];
        arrLessonPanels[0] = lessonP1;
        arrLessonPanels[1] = lessonP2;
        arrLessonPanels[2] = lessonP3;
        arrLessonPanels[3] = lessonP4;
        arrLessonPanels[4] = lessonP5;
        for (int i = 0; i < arrLessonPanels.length; i++){
            arrLessonPanels[i].setNumber(i + 1);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        groupShedule = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        comboDate = new shedule.comboDate();
        lessonP1 = new shedule.LessonPanel();
        lessonP2 = new shedule.LessonPanel();
        lessonP3 = new shedule.LessonPanel();
        lessonP4 = new shedule.LessonPanel();
        lessonP5 = new shedule.LessonPanel();

        jLabel4.setText("Группа");

        groupShedule.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                groupShedulePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                groupShedulePopupMenuWillBecomeVisible(evt);
            }
        });

        jLabel5.setText("Дата");

        jButton8.setText("Сохранить");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton1.setText("Очистить");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Выход");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton8)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lessonP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(groupShedule, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lessonP2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lessonP3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lessonP5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lessonP4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(groupShedule, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(comboDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lessonP1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lessonP2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lessonP3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lessonP4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lessonP5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void groupShedulePopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_groupShedulePopupMenuWillBecomeVisible
        setGroupsBox();
    }//GEN-LAST:event_groupShedulePopupMenuWillBecomeVisible

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        addShedule();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void groupShedulePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_groupShedulePopupMenuWillBecomeInvisible
        selectGroup();
    }//GEN-LAST:event_groupShedulePopupMenuWillBecomeInvisible

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        resetLessons();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public shedule.comboDate comboDate;
    public javax.swing.JComboBox groupShedule;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private shedule.LessonPanel lessonP1;
    private shedule.LessonPanel lessonP2;
    private shedule.LessonPanel lessonP3;
    private shedule.LessonPanel lessonP4;
    private shedule.LessonPanel lessonP5;
    // End of variables declaration//GEN-END:variables
}
