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
    MyTable(String tab){
        DB d = new DB();
        switch(tab){
            case "teachers" :
            String[][] v = d.getTab(tab);
            String[] c = {"Фамилия", "Имя", "Отчество"};
            this.setColumnIdentifiers(c);
            this.setRowCount(v.length);
            for(int i = 0; i < v.length; i++){
                this.setValueAt(v[i][3], i, 0);
                this.setValueAt(v[i][1], i, 1);
                this.setValueAt(v[i][2], i, 2);
            }
            break;
        }
    }
    
    
    
}
