/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dc;

/**
 *
 * @author priyanka
 */
import
import dc.ChatPage.MessageBox;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
 class ReadThread extends Thread{
     
     String ip;
     Socket sock;
     DataInputStream din;
     DataOutputStream dout;
     String str;
     ReadThread(String i)
     {
         ip = i;
     }
     public void run(){
         
         try {
             sock = new Socket(ip,4900);
             din  =  new DataInputStream(sock.getInputStream());
             dout = new DataOutputStream(sock.getOutputStream());
             while(true)
             {
                 if(din.available()>0){
                     str = din.readUTF();
                     MessageBox.setText(str);
                             
                     
                 }
             }
             
         } catch (IOException ex) {
             Logger.getLogger(ReadThread.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         
     }
     
     
     
     
    
    
    
}
