    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sd.prjservidorchat_thread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Vector;

/**
 *
 * @author Dionnes
 */
public class GerenciarMensagensClientes extends Thread{
    ServidorFormChat form;
    Socket conexao;
    Vector clientes;
    String meuNome; 
    String palavras[];

    public GerenciarMensagensClientes(ServidorFormChat form, Socket conexao,
            Vector clientes,String palavras[]) {
        this.form = form;
        this.conexao = conexao;
        this.clientes = clientes;
        this.palavras = this.palavras;
    }
    
    public void enviarParaTodos(PrintStream saida, String acao, String linha){
        
        ManipularArquivo ma = new ManipularArquivo();
        Enumeration e = clientes.elements();
        
        for(String palavra : palavras){
            if(linha.toLowerCase().contains(palavra.toLowerCase())){
                ma.escreverArq(meuNome + acao + linha, "campoMensagens");
            }
        }
        
        form.getcampoMensagens().setText(form.getcampoMensagens().getText()+"\n"+meuNome+ acao + linha);
        
        while(e.hasMoreElements()){
            
            PrintStream chat = (PrintStream) e.nextElement();
            if(chat != saida){
                chat.println(meuNome+acao+linha);
            }
                
        }
    }
    
    
    @Override
    public void run(){
        System.out.println(palavras[0]);
        
        try {
            BufferedReader entrada = new BufferedReader( new InputStreamReader(conexao.getInputStream()));
            PrintStream saida = new PrintStream(conexao.getOutputStream());
                meuNome = entrada.readLine();
                if(meuNome == null){
                    return;
                }
                clientes.add(saida);
                
                String linha = entrada.readLine();
                
                while(linha != null && !(linha.trim().equals(""))){
                    enviarParaTodos(saida , "falhou:" , linha);
                    linha = entrada.readLine();
                }
        } catch (Exception e) {
        }
    }
    
}
