/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

/**
 *
 * @author Andrey
 */
public class MyTable extends javax.swing.table.DefaultTableModel {
    MyTable(){
        DB d = new DB();
        String[][] v = d.getTeachers();
        String[] c = {"Фамилия", "Имя", "Отчество"};
        this.setColumnIdentifiers(c);
        this.setRowCount(v.length);
        for(int i = 0; i < v.length; i++){
            this.setValueAt(v[i][0], i, 0);
            this.setValueAt(v[i][1], i, 1);
            this.setValueAt(v[i][2], i, 2);
        }
    }
    
    
    
}
