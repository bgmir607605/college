/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import javax.swing.JTable;

/**
 *
 * @author Andrey
 */
public class MyTable extends javax.swing.table.DefaultTableModel {
    String[][]v;
    void getData(String t, String[] c){
        DB d = new DB();
        v = d.getTab(t);//получаем таблицу
        this.setColumnIdentifiers(c);//устанавливаем названия столбцов из массива
        this.setRowCount(v.length);//устанавливаем количество строк равное количеству записей в таблице
    }
    MyTable(String tab){
        switch(tab){
            case "teachers" :    
            String[] c1 = {"Фамилия", "Имя", "Отчество"};
            getData(tab, c1);
            for(int i = 0; i < v.length; i++){
                this.setValueAt(v[i][3], i, 0);
                this.setValueAt(v[i][1], i, 1);
                this.setValueAt(v[i][2], i, 2);
            }
            break;
            case "discipline":
            String[] c2 = {"Краткое название", "Полное название"};
            getData(tab, c2);
            for(int i = 0; i < v.length; i++){
                this.setValueAt(v[i][1], i, 0);
                this.setValueAt(v[i][2], i, 1);
            }
            break;
            case "specialty":
            String[] c3 = {"Шифр", "Наименование"};
            getData(tab, c3);
            for(int i = 0; i < v.length; i++){
                this.setValueAt(v[i][1], i, 0);
                this.setValueAt(v[i][2], i, 1);
            }
            break;
            case "groups":
            String[] c4 = {"Группа", "Специальность"};
            getData(tab, c4);
            for(int i = 0; i < v.length; i++){
                this.setValueAt(v[i][1], i, 0);
                this.setValueAt(v[i][2], i, 1);
            }
            break;
        }
    }
    
    
    
}
