/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sd.prjservidorchat_thread;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Dionnes
 */
public class ManipularArquivo {
    
    public void escreverArq(String texto, String nomeArq){
        
        try {
            File f = new File("c:\\"+nomeArq+".txt");
            
            FileWriter fw = new FileWriter(f, false);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(texto);
            fw.close();
        } catch (IOException ex) {
           ex.printStackTrace();
        }
    }
    
    public BufferedReader lerArq(String nomeArq){
        
        BufferedReader br = null;
        try {
            File f = new File("c:\\"+nomeArq+".txt");
            
            if(f.exists()){
                FileReader fr = new FileReader(f);
                br = new BufferedReader(fr);
            }
        } catch (Exception e) {
        }
        
        return br;
    }
    
    
}
