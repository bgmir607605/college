/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.io.FileNotFoundException;

/**
 *
 * @author Andrey
 */
public class main {
    
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        //Filer.write("D:\\test.txt", "This new text \nThis new text2\\nThis new text3\\nThis new text4\\n");
        //String f = Filer.read("D:\\test.txt");
        //System.out.println(f);
        
        //Проверка подключения к БД
        if (Checks.testConnect()){
            System.out.println("Проверка подключения прошла успешно");
            Form mainForm = new Form();
        }
        else {
            System.out.println("Нет подключения к БД");
        }
        
    }
    
}
