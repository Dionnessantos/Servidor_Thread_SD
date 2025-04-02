/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sd.prjservidorchat_thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

/**
 *
 * @author Dionnes
 */
public class GerenciarConexao extends Thread {
    
    ServidorFormChat form;
    Vector clientes;
    int porta;
    String palavras[];

    public GerenciarConexao(ServidorFormChat form, Vector clientes, int porta, String palavras[]) {
        
        this.form = form;
        this.clientes = clientes;
        this.porta = porta;
        this.palavras = palavras;
    }
    
    @Override
    public void run(){
        
        clientes = new Vector();
        try {
            ServerSocket ss = new ServerSocket(porta);
            int contaClientes = 0;
            
            while(true){
                
                form.getLbSituacao().setText("Aguardando conexão !!!");
                
                Socket con = ss.accept();
                contaClientes += 1;
                form.gettxtNumeroCliente().setText(String.valueOf(contaClientes));
                
                Thread t = new GerenciarMensagensClientes(form, con, clientes, palavras);
                t.start();
            }
        } catch (IOException ex) {
          ex.printStackTrace();
        }
    }
    
}
