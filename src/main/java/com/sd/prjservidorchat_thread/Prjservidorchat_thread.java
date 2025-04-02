
package com.sd.prjservidorchat_thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Vector;
import javax.imageio.IIOException;
import javax.net.ssl.SSLServerSocket;

/**
 *
 * @author Dionnes
 */
public class Prjservidorchat_thread extends Thread{

    public static void main(String[] args) {
         clientes = new Vector();
        
        try{
            ServerSocket ss = new ServerSocket(1234);
            while(true){
                System.out.println("Esperendo um cliente realizar a conexao");
                Socket con = ss.accept();
                System.out.println("Conexao realizada!");
                
                Thread t = new Prjservidorchat_thread(con);
                
                t.start();
            }
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    private  static Vector clientes;
    private Socket conexao; 
    private String meuNome;
    
    public Prjservidorchat_thread(Socket ss) {
        
        conexao = ss; 
    }
    @Override
    public  void run(){
        try{
            BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            PrintStream saida = new PrintStream(conexao.getOutputStream());
            
            meuNome = entrada.readLine();
            
            if(meuNome == null){
                return;
            }
            clientes.add(saida);
            String linha = entrada.readLine();
            
            while(linha != null && !(linha.trim().equals(""))){
                
                enviarParatodos(saida, "Falou", linha);
                linha = entrada.readLine();
            }
            
            enviarParatodos(saida, "saiu", "do chat");
            clientes.remove(saida);
            conexao.close();
        }
        
        catch(IOException ex){
        
    }
    }
        
    public void enviarParatodos(PrintStream saida, String acao, String linha){
        
        Enumeration e = clientes.elements();
        
        while(e.hasMoreElements()){
            
            PrintStream chat = (PrintStream) e.nextElement();
            if(chat != saida){
                chat.println(meuNome+acao+linha);
            }
                
        }
    }
    
    
}
