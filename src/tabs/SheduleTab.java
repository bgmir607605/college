/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tabs;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import model.DB;
import model.LoadsForGroup;

/**
 *
 * @author aamir
 */
public class SheduleTab extends javax.swing.JPanel {
    JComboBox[] arrComboDiscipline = new JComboBox[10];
    JComboBox[] arrCombolName = new JComboBox[10];
    JCheckBox[] arrCheck = new JCheckBox[5];
    static LoadsForGroup loads = null;

    /**
     * Creates new form SheduleTab
     */
    public SheduleTab() {
        initComponents();
    }
    public void initDate(){
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = null;
        String y;
        int i;
        dateFormat = new SimpleDateFormat("dd");
        y = dateFormat.format( currentDate );
        i = Integer.parseInt(y);
        jSpinner1.setValue(i);
        dateFormat = new SimpleDateFormat("MM");
        y = dateFormat.format( currentDate );
        i = Integer.parseInt(y);
        jSpinner2.setValue(i);
        dateFormat = new SimpleDateFormat("y");
        y = dateFormat.format( currentDate );
        i = Integer.parseInt(y);
        jSpinner3.setValue(i);

    }
    public boolean getValueCheck(int i){
        return arrCheck[i].isSelected();
    }
    public String getValueComboDiscipline(int i){
        return (String) arrComboDiscipline[i].getSelectedItem();
    }
    public boolean isNotEmptyLesson(int i){
        return arrComboDiscipline[i].getSelectedIndex() != -1;
    }
    public String getValueCombolName(int i){
        return (String) arrCombolName[i].getSelectedItem();
    }
    public void setGroupShedule(String[] arr){
        //Очистить комбобоксы с названиями дисциплин
        for (int i = 0; i < arrComboDiscipline.length; i++){
            arrComboDiscipline[i].removeAllItems();
        }
        //Добавить названия дисциплин в комбобоксы
        for (int i = 0; i < arr.length; i++){
            for (int k = 0; k < arrComboDiscipline.length; k++){
            arrComboDiscipline[k].addItem(arr[i]);
            }
        }
        //Установить по умолчанию "окна"
        for (int i = 0; i < arrComboDiscipline.length; i++){
            arrComboDiscipline[i].setSelectedIndex(-1);
        }
    }
    public void initArrsOfCombo(){
        arrComboDiscipline[0] = shedule11;
        arrComboDiscipline[1] = shedule12;
        arrComboDiscipline[2] = shedule21;
        arrComboDiscipline[3] = shedule22;
        arrComboDiscipline[4] = shedule31;
        arrComboDiscipline[5] = shedule32;
        arrComboDiscipline[6] = shedule41;
        arrComboDiscipline[7] = shedule42;
        arrComboDiscipline[8] = shedule51;
        arrComboDiscipline[9] = shedule52;
        
        arrCombolName[0] = sheduleTeacherLName11;
        arrCombolName[1] = sheduleTeacherLName12;
        arrCombolName[2] = sheduleTeacherLName21;
        arrCombolName[3] = sheduleTeacherLName22;
        arrCombolName[4] = sheduleTeacherLName31;
        arrCombolName[5] = sheduleTeacherLName32;
        arrCombolName[6] = sheduleTeacherLName41;
        arrCombolName[7] = sheduleTeacherLName42;
        arrCombolName[8] = sheduleTeacherLName51;
        arrCombolName[9] = sheduleTeacherLName52;
        
        arrCheck[0] = check1;
        arrCheck[1] = check2;
        arrCheck[2] = check3;
        arrCheck[3] = check4;
        arrCheck[4] = check5;
    }
    public void setTotalLesson(int x){
        if (arrCheck[x].isSelected()){
            arrComboDiscipline[x + x + 1].setEnabled(false);
        }
        else{
            arrComboDiscipline[x + x + 1].setEnabled(true);
        }
        
    }
    public void setDisciplineLesson(int x){
        arrCombolName[x].removeAllItems();
        String[] lNames = loads.getArrTeacherForDiscipline((String)arrComboDiscipline[x].getSelectedItem());
        for (int i = 0; i < lNames.length; i++){
            arrCombolName[x].addItem(lNames[i]);
        }
                
    }
    public String getDate(){
        String s = "";
        String ts = "" + jSpinner3.getValue();
        int t = Integer.parseInt(ts);
        s = s + t + "-";
        ts = "" + jSpinner2.getValue();
        t = Integer.parseInt(ts);
        if (t < 10){
            s = s + "0" + t;
        }
        else {
            s = s + t;
        }
        s = s + "-";
        ts = "" + jSpinner1.getValue();
        t = Integer.parseInt(ts);
        if (t < 10){
            s = s + "0" + t;
        }
        else {
            s = s + t;
        }
        return s;
    }
    void getGroupsList(){
        groupShedule.removeAllItems();
        DB d = new DB();
        String[] a = d.getBoxList("name", "groups");
        for (int i = 0; i < a.length; i++){
            groupShedule.addItem(a[i]);
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

        jLabel3 = new javax.swing.JLabel();
        shedule11 = new javax.swing.JComboBox();
        shedule12 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        groupShedule = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        sheduleTeacherLName11 = new javax.swing.JComboBox();
        check1 = new javax.swing.JCheckBox();
        shedule21 = new javax.swing.JComboBox();
        shedule22 = new javax.swing.JComboBox();
        sheduleTeacherLName12 = new javax.swing.JComboBox();
        sheduleTeacherLName22 = new javax.swing.JComboBox();
        sheduleTeacherLName21 = new javax.swing.JComboBox();
        check2 = new javax.swing.JCheckBox();
        jLabel13 = new javax.swing.JLabel();
        sheduleTeacherLName31 = new javax.swing.JComboBox();
        sheduleTeacherLName32 = new javax.swing.JComboBox();
        shedule32 = new javax.swing.JComboBox();
        shedule31 = new javax.swing.JComboBox();
        check3 = new javax.swing.JCheckBox();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        check4 = new javax.swing.JCheckBox();
        shedule41 = new javax.swing.JComboBox();
        sheduleTeacherLName41 = new javax.swing.JComboBox();
        sheduleTeacherLName42 = new javax.swing.JComboBox();
        shedule42 = new javax.swing.JComboBox();
        check5 = new javax.swing.JCheckBox();
        jLabel16 = new javax.swing.JLabel();
        shedule51 = new javax.swing.JComboBox();
        sheduleTeacherLName51 = new javax.swing.JComboBox();
        sheduleTeacherLName52 = new javax.swing.JComboBox();
        shedule52 = new javax.swing.JComboBox();
        jSpinner1 = new javax.swing.JSpinner();
        jSpinner2 = new javax.swing.JSpinner();
        jSpinner3 = new javax.swing.JSpinner();

        jLabel3.setText("1.");

        shedule11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shedule11ActionPerformed(evt);
            }
        });

        shedule12.setEnabled(false);
        shedule12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shedule12ActionPerformed(evt);
            }
        });

        jLabel4.setText("Группа");

        groupShedule.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                groupShedulePopupMenuWillBecomeVisible(evt);
            }
        });
        groupShedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                groupSheduleActionPerformed(evt);
            }
        });

        jLabel5.setText("Дата");

        jButton8.setText("Добавить");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        check1.setSelected(true);
        check1.setText("общ.");
        check1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check1ActionPerformed(evt);
            }
        });

        shedule21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shedule21ActionPerformed(evt);
            }
        });

        shedule22.setEnabled(false);
        shedule22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shedule22ActionPerformed(evt);
            }
        });

        check2.setSelected(true);
        check2.setText("общ.");
        check2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check2ActionPerformed(evt);
            }
        });

        jLabel13.setText("1.");

        shedule32.setEnabled(false);
        shedule32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shedule32ActionPerformed(evt);
            }
        });

        shedule31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shedule31ActionPerformed(evt);
            }
        });

        check3.setSelected(true);
        check3.setText("общ.");
        check3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check3ActionPerformed(evt);
            }
        });

        jLabel14.setText("1.");

        jLabel15.setText("1.");

        check4.setSelected(true);
        check4.setText("общ.");
        check4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check4ActionPerformed(evt);
            }
        });

        shedule41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shedule41ActionPerformed(evt);
            }
        });

        shedule42.setEnabled(false);
        shedule42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shedule42ActionPerformed(evt);
            }
        });

        check5.setSelected(true);
        check5.setText("общ.");
        check5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check5ActionPerformed(evt);
            }
        });

        jLabel16.setText("1.");

        shedule51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shedule51ActionPerformed(evt);
            }
        });

        shedule52.setEnabled(false);
        shedule52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shedule52ActionPerformed(evt);
            }
        });

        jSpinner1.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(shedule22, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(check2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(shedule21, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(61, 61, 61)
                                        .addComponent(shedule12, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(check1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(shedule11, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sheduleTeacherLName11, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sheduleTeacherLName12, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sheduleTeacherLName21, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sheduleTeacherLName22, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(shedule52, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel16)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(check5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(shedule51, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(sheduleTeacherLName51, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(sheduleTeacherLName52, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(shedule42, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel15)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(check4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(shedule41, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel14)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(61, 61, 61)
                                            .addComponent(shedule32, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(check3)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(shedule31, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(sheduleTeacherLName31, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(sheduleTeacherLName32, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(sheduleTeacherLName41, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(sheduleTeacherLName42, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jButton8)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(groupShedule, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(groupShedule, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(shedule11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sheduleTeacherLName11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(check1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(shedule12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sheduleTeacherLName12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(shedule21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel13)
                                .addComponent(check2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(shedule22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sheduleTeacherLName21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sheduleTeacherLName22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(shedule31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sheduleTeacherLName31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(check3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(shedule32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sheduleTeacherLName32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(shedule41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel15)
                                .addComponent(check4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(shedule42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sheduleTeacherLName41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sheduleTeacherLName42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(shedule51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel16)
                                .addComponent(check5)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(shedule52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sheduleTeacherLName51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sheduleTeacherLName52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton8)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void shedule11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shedule11ActionPerformed
        setDisciplineLesson(0);
    }//GEN-LAST:event_shedule11ActionPerformed

    private void shedule12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shedule12ActionPerformed
        setDisciplineLesson(1);
    }//GEN-LAST:event_shedule12ActionPerformed

    private void groupShedulePopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_groupShedulePopupMenuWillBecomeVisible
        getGroupsList();
    }//GEN-LAST:event_groupShedulePopupMenuWillBecomeVisible

    private void groupSheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_groupSheduleActionPerformed
        loads = new LoadsForGroup(this);
        loads.setGroupId((String) groupShedule.getSelectedItem());
        loads.getArrLoads();
        loads.setDisciplines();
    }//GEN-LAST:event_groupSheduleActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        new Shedule(this, loads.getGroupId()).addShedule();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void check1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check1ActionPerformed
        setTotalLesson(0);
    }//GEN-LAST:event_check1ActionPerformed

    private void shedule21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shedule21ActionPerformed
        setDisciplineLesson(2);
    }//GEN-LAST:event_shedule21ActionPerformed

    private void shedule22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shedule22ActionPerformed
        setDisciplineLesson(3);
    }//GEN-LAST:event_shedule22ActionPerformed

    private void check2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check2ActionPerformed
        setTotalLesson(1);
    }//GEN-LAST:event_check2ActionPerformed

    private void shedule32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shedule32ActionPerformed
        setDisciplineLesson(5);
    }//GEN-LAST:event_shedule32ActionPerformed

    private void shedule31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shedule31ActionPerformed
        setDisciplineLesson(4);
    }//GEN-LAST:event_shedule31ActionPerformed

    private void check3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check3ActionPerformed
        setTotalLesson(2);
    }//GEN-LAST:event_check3ActionPerformed

    private void check4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check4ActionPerformed
        setTotalLesson(3);
    }//GEN-LAST:event_check4ActionPerformed

    private void shedule41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shedule41ActionPerformed
        setDisciplineLesson(6);
    }//GEN-LAST:event_shedule41ActionPerformed

    private void shedule42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shedule42ActionPerformed
        setDisciplineLesson(7);
    }//GEN-LAST:event_shedule42ActionPerformed

    private void check5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check5ActionPerformed
        setTotalLesson(4);
    }//GEN-LAST:event_check5ActionPerformed

    private void shedule51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shedule51ActionPerformed
        setDisciplineLesson(8);
    }//GEN-LAST:event_shedule51ActionPerformed

    private void shedule52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shedule52ActionPerformed
        setDisciplineLesson(9);
    }//GEN-LAST:event_shedule52ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JCheckBox check1;
    public javax.swing.JCheckBox check2;
    public javax.swing.JCheckBox check3;
    public javax.swing.JCheckBox check4;
    public javax.swing.JCheckBox check5;
    public javax.swing.JComboBox groupShedule;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner3;
    public javax.swing.JComboBox shedule11;
    public javax.swing.JComboBox shedule12;
    public javax.swing.JComboBox shedule21;
    public javax.swing.JComboBox shedule22;
    public javax.swing.JComboBox shedule31;
    public javax.swing.JComboBox shedule32;
    public javax.swing.JComboBox shedule41;
    public javax.swing.JComboBox shedule42;
    public javax.swing.JComboBox shedule51;
    public javax.swing.JComboBox shedule52;
    public javax.swing.JComboBox sheduleTeacherLName11;
    public javax.swing.JComboBox sheduleTeacherLName12;
    public javax.swing.JComboBox sheduleTeacherLName21;
    public javax.swing.JComboBox sheduleTeacherLName22;
    public javax.swing.JComboBox sheduleTeacherLName31;
    public javax.swing.JComboBox sheduleTeacherLName32;
    public javax.swing.JComboBox sheduleTeacherLName41;
    public javax.swing.JComboBox sheduleTeacherLName42;
    public javax.swing.JComboBox sheduleTeacherLName51;
    public javax.swing.JComboBox sheduleTeacherLName52;
    // End of variables declaration//GEN-END:variables
}