/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tabs;

import DB.MainDB;
import DB.SheduleDB;
import gui.Form;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import model.LoadsForGroup;

/**
 *
 * @author aamir
 */
public class Shedule extends javax.swing.JPanel {
    JComboBox[] arrComboDiscipline = new JComboBox[10];
    JComboBox[] arrCombolName = new JComboBox[10];
    JCheckBox[] arrCheck = new JCheckBox[5];
    static LoadsForGroup loads = null;
    String[][] arrToInsert = new String[10][3];
    /**
     * |   0    |  1   |     2         |
     * | number | type | teacherLoadId |
     */
    String[][] AvailableShedule;

    /**
     * Creates new form SheduleTab
     */
    public Shedule() {
        initComponents();
        initArrsOfCombo();
        initDate();
        for (int i = 0; i < arrComboDiscipline.length; i++){
            arrComboDiscipline[i].setSelectedItem("");
        }
    }
    
    /**
     * Устанавливает текущую дату в счётчики
     */
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
    
    /**
     * Добавить значения в comboBox групп
     */
    void setGroupsBox(String[] arr){
        groupShedule.removeAllItems();
        MainDB d = new MainDB();
        String[] a = d.getBoxList("name", "groups");
        for (int i = 0; i < a.length; i++){
            groupShedule.addItem(a[i]);
        }
    }
    
    /**
     * Выбрать группу
     */
    void selectGroup(){
        loads = new LoadsForGroup((String) groupShedule.getSelectedItem());
        setDisciplinesBox(loads.getArrDisciplines());
        if (new SheduleDB().isAvailableShedule(loads.getStringOfTeacherLoadsId(), getDate())){
            System.out.println("Нужно загружать имеющееся");
            AvailableShedule = new SheduleDB().getArrAvailableShedule(loads.getStringOfTeacherLoadsId(), getDate());
            setAvailableShedule();
        }
    }
    void setAvailableShedule(){
        for (int i = 0; i < AvailableShedule.length; i++){
            setAvailableLesson(AvailableShedule[i]);
        }
    }
    void setAvailableLesson(String[] lesson){
        int number = Integer.parseInt(lesson[0]) - 1;
        String type = lesson[1];
        String teacherLoadId = lesson[2];
        if (type.equals("")){
            arrComboDiscipline[number * 2].setSelectedItem(loads.getDisciplineOnTeacherLoadId(teacherLoadId));
            arrCombolName[number * 2].setSelectedItem(loads.getLNameTeacherOnTeacherLoadId(teacherLoadId));
            arrCheck[number].setSelected(true);
        }
        if (type.equals("I")){
            arrComboDiscipline[number * 2].setSelectedItem(loads.getDisciplineOnTeacherLoadId(teacherLoadId));
            arrCombolName[number * 2].setSelectedItem(loads.getLNameTeacherOnTeacherLoadId(teacherLoadId));
            arrCheck[number].setSelected(false);
        }
        if (type.equals("II")){
            arrComboDiscipline[number * 2 + 1].setSelectedItem(loads.getDisciplineOnTeacherLoadId(teacherLoadId));
            arrCombolName[number * 2 + 1].setSelectedItem(loads.getLNameTeacherOnTeacherLoadId(teacherLoadId));
            arrCheck[number].setSelected(false);
        }
        
    }
    
    /**
     * 
     * @param arr 
     */
    public void setDisciplinesBox(String[] arr){
        //Очистить комбобоксы с названиями дисциплин
        for (int i = 0; i < arrComboDiscipline.length; i++){
            arrComboDiscipline[i].removeAllItems();
        }
        //Добавить "окна"
        for (int i = 0; i < arrComboDiscipline.length; i++){
            arrComboDiscipline[i].addItem("");
        }
        //Добавить названия дисциплин в комбобоксы
        for (int i = 0; i < arr.length; i++){
            for (int k = 0; k < arrComboDiscipline.length; k++){
            arrComboDiscipline[k].addItem(arr[i]);
            }
        }
        
    }
    
    public void selectDisciplineLesson(int x){
        arrCombolName[x].removeAllItems();
        String[] lNames = loads.getArrTeacherForDiscipline((String)arrComboDiscipline[x].getSelectedItem());
        for (int i = 0; i < lNames.length; i++){
            arrCombolName[x].addItem(lNames[i]);
        }
                
    }
    
    //Конец интерфейсной части//
    //Обработка добавления расписания//
    
    /**
     * Добавить расписание
     */
    String getQuaryToInsert(){
        initArrToInsert();
        String quary = "INSERT INTO shedule (`date`, `number`, `type`, `teacherLoadId`, `h`) VALUES ";
        String date = getDate();
        for (int i = 0; i < arrToInsert.length; i++){
            if (arrToInsert[i][0] != null){
                quary = quary + "('" + date + "', '" + arrToInsert[i][0] + "', '" + arrToInsert[i][1] + "', '" + arrToInsert[i][2] + "', '2'), ";
            }
        }
        quary = quary.substring(0, quary.length() - 2) + ";";
        return quary;
    }
    public void addShedule(){
        try {
                new MainDB().ins(getQuaryToInsert());
                JOptionPane.showMessageDialog(null,"Расписание добавлено", "Сообщение системы", JOptionPane.DEFAULT_OPTION);
            } catch (SQLException ex) {
                Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    /**
     * Свормировать массив занятий(Включая окна)
     */
    public void initArrToInsert(){
	int number;
        int i = 0;
        while(i < 10){
            if (getValueCheck(i / 2)){
                if (isNotEmptyLesson(i)){
                    number = i / 2 + 1;
                    setValueOfArrToInsert(i, number, "", getValueComboDiscipline(i), getValueCombolName(i));
                }
                i = i + 2;
            }
            else{
                if (isNotEmptyLesson(i)){
                    number = i / 2 + 1;
                    setValueOfArrToInsert(i, number, "I", getValueComboDiscipline(i), getValueCombolName(i));
                }
                i++;
                if (isNotEmptyLesson(i)){
                    number = (i - 1) / 2 + 1;
                    setValueOfArrToInsert(i, number, "II", getValueComboDiscipline(i), getValueCombolName(i));
                }
                i++;
            }
        }
    }
    
    /**
     * 
     * Сформировать строку даты для SQL 
     */
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
    
    /**
     * Установить значение элемента ArrToInsert[][]
     * @param i
     * @param number
     * @param type
     * @param teacherLoad
     */
    public void setValueOfArrToInsert(int i, int number, String type, Object discipline, Object lName){
        arrToInsert[i][0] = "" + number;
                    arrToInsert[i][1] = type;
                    arrToInsert[i][2] = loads.getTeacherLoadId((String)lName, (String) discipline);
    }
    
    
    public boolean getValueCheck(int i){
        return arrCheck[i].isSelected();
    }
    public String getValueComboDiscipline(int i){
        return (String) arrComboDiscipline[i].getSelectedItem();
    }
    public boolean isNotEmptyLesson(int i){
        String v = (String) arrComboDiscipline[i].getSelectedItem();
        return !(v.equals(""));
    }
    public String getValueCombolName(int i){
        return (String) arrCombolName[i].getSelectedItem();
    }
    public void setTotalLesson(int x){
        if (arrCheck[x].isSelected()){
            arrComboDiscipline[x + x + 1].setEnabled(false);
        }
        else{
            arrComboDiscipline[x + x + 1].setEnabled(true);
        }
        
    }
    void resetDisciplineBoxes(){
        for (int i = 0; i < arrComboDiscipline.length; i++){
            arrComboDiscipline[i].setSelectedIndex(0);
        }
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

    //////////////////////////////
    //                          //
    //  Автогенерированный код  //
    //                          //
    //////////////////////////////
    public final void initArrsOfCombo(){
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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jLabel3.setText("1.");

        shedule11.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        shedule11.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                shedule11PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        shedule12.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        shedule12.setEnabled(false);
        shedule12.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                shedule12PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

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

        check1.setSelected(true);
        check1.setText("общ.");
        check1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check1ActionPerformed(evt);
            }
        });

        shedule21.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        shedule21.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                shedule21PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        shedule22.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        shedule22.setEnabled(false);
        shedule22.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                shedule22PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        check2.setSelected(true);
        check2.setText("общ.");
        check2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check2ActionPerformed(evt);
            }
        });

        jLabel13.setText("2.");

        shedule32.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        shedule32.setEnabled(false);
        shedule32.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                shedule32PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        shedule31.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        shedule31.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                shedule31PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        check3.setSelected(true);
        check3.setText("общ.");
        check3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check3ActionPerformed(evt);
            }
        });

        jLabel14.setText("3.");

        jLabel15.setText("4.");

        check4.setSelected(true);
        check4.setText("общ.");
        check4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check4ActionPerformed(evt);
            }
        });

        shedule41.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        shedule41.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                shedule41PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        shedule42.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        shedule42.setEnabled(false);
        shedule42.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                shedule42PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        check5.setSelected(true);
        check5.setText("общ.");
        check5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check5ActionPerformed(evt);
            }
        });

        jLabel16.setText("5.");

        shedule51.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        shedule51.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                shedule51PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        shedule52.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " " }));
        shedule52.setEnabled(false);
        shedule52.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                shedule52PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jSpinner1.setToolTipText("");

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
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void groupShedulePopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_groupShedulePopupMenuWillBecomeVisible
        setGroupsBox(new MainDB().getBoxList("name", "groups"));
    }//GEN-LAST:event_groupShedulePopupMenuWillBecomeVisible

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        addShedule();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void check1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check1ActionPerformed
        setTotalLesson(0);
    }//GEN-LAST:event_check1ActionPerformed

    private void check2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check2ActionPerformed
        setTotalLesson(1);
    }//GEN-LAST:event_check2ActionPerformed

    private void check3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check3ActionPerformed
        setTotalLesson(2);
    }//GEN-LAST:event_check3ActionPerformed

    private void check4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check4ActionPerformed
        setTotalLesson(3);
    }//GEN-LAST:event_check4ActionPerformed

    private void check5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check5ActionPerformed
        setTotalLesson(4);
    }//GEN-LAST:event_check5ActionPerformed

    private void groupShedulePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_groupShedulePopupMenuWillBecomeInvisible
        selectGroup();
    }//GEN-LAST:event_groupShedulePopupMenuWillBecomeInvisible

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        resetDisciplineBoxes();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void shedule11PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_shedule11PopupMenuWillBecomeInvisible
        selectDisciplineLesson(0);
    }//GEN-LAST:event_shedule11PopupMenuWillBecomeInvisible

    private void shedule12PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_shedule12PopupMenuWillBecomeInvisible
        selectDisciplineLesson(1);
    }//GEN-LAST:event_shedule12PopupMenuWillBecomeInvisible

    private void shedule21PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_shedule21PopupMenuWillBecomeInvisible
        selectDisciplineLesson(2);
    }//GEN-LAST:event_shedule21PopupMenuWillBecomeInvisible

    private void shedule22PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_shedule22PopupMenuWillBecomeInvisible
        selectDisciplineLesson(3);
    }//GEN-LAST:event_shedule22PopupMenuWillBecomeInvisible

    private void shedule31PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_shedule31PopupMenuWillBecomeInvisible
        selectDisciplineLesson(4);
    }//GEN-LAST:event_shedule31PopupMenuWillBecomeInvisible

    private void shedule32PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_shedule32PopupMenuWillBecomeInvisible
        selectDisciplineLesson(5);
    }//GEN-LAST:event_shedule32PopupMenuWillBecomeInvisible

    private void shedule41PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_shedule41PopupMenuWillBecomeInvisible
        selectDisciplineLesson(6);
    }//GEN-LAST:event_shedule41PopupMenuWillBecomeInvisible

    private void shedule42PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_shedule42PopupMenuWillBecomeInvisible
        selectDisciplineLesson(7);
    }//GEN-LAST:event_shedule42PopupMenuWillBecomeInvisible

    private void shedule51PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_shedule51PopupMenuWillBecomeInvisible
        selectDisciplineLesson(8);
    }//GEN-LAST:event_shedule51PopupMenuWillBecomeInvisible

    private void shedule52PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_shedule52PopupMenuWillBecomeInvisible
        selectDisciplineLesson(9);
    }//GEN-LAST:event_shedule52PopupMenuWillBecomeInvisible


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JCheckBox check1;
    public javax.swing.JCheckBox check2;
    public javax.swing.JCheckBox check3;
    public javax.swing.JCheckBox check4;
    public javax.swing.JCheckBox check5;
    public javax.swing.JComboBox groupShedule;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
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
