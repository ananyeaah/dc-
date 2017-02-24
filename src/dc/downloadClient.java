/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dc;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import static java.lang.System.out;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import static sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl.ThreadStateMap.Byte0.runnable;

/**
 *
 * @author JUHI AGRAWAL
 */
public class downloadClient extends Thread{
    
    
    static Socket socket,nClientSocket;
    static ServerSocket nameSocket;
    String ip, location, filename;
    long Tsize;
    InputStream in ;
    OutputStream out ;
    DataOutputStream dout;
    JDialog dlg;
    JLabel j1;
    JProgressBar dpb;
    JFrame parentFrame;
    
    public downloadClient(String i,String l,long s){
        ip=i;
        location=l;
        filename=l.substring(l.lastIndexOf(File.separator));
        Tsize=s;
        
        System.out.println("Cons start");
        parentFrame = new JFrame();
        parentFrame.setSize(500,150);
        j1 = new JLabel();
        j1.setText("Downloading 0");
     
        parentFrame.add(BorderLayout.CENTER, j1);
    parentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    parentFrame.setVisible(true);

    dlg = new JDialog(parentFrame, "Progress Dialog", true);
    dpb = new JProgressBar(0, 100);
    dlg.add(BorderLayout.CENTER, dpb);
    dlg.add(BorderLayout.NORTH, new JLabel("Progress..."));
    dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    dlg.setSize(300, 75);
    dlg.setLocationRelativeTo(parentFrame);
    //dlg.setVisible(true);
    System.out.println("Con finish");
    }
    
    public void unzip(String s){
        		try {
			ZipFile zipFile = new ZipFile(s);
			Enumeration<?> enu = zipFile.entries();
			while (enu.hasMoreElements()) {
				ZipEntry zipEntry = (ZipEntry) enu.nextElement();
				String name = zipEntry.getName();
				long size = zipEntry.getSize();
				long compressedSize = zipEntry.getCompressedSize();
				System.out.printf("name: %-20s | size: %6d | compressed size: %6d\n", 
						name, size, compressedSize);
				File file = new File("E:"+File.separator+"Downloads"+File.separator+name);
				if (name.endsWith(File.separator)) {
					file.mkdirs();
					continue;
				}
 
				File parent = file.getParentFile();
				if (parent != null) {
					parent.mkdirs();
				}
 
				InputStream is = zipFile.getInputStream(zipEntry);
				FileOutputStream fos = new FileOutputStream(file);
				byte[] bytes = new byte[1024];
				int length;
				while ((length = is.read(bytes)) >= 0) {
					fos.write(bytes, 0, length);
				}
				is.close();
				fos.close();
 
			}
			zipFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @Override
    synchronized public void run(){
        System.out.println("run start");
        Thread t = new Thread(new Runnable(){
            public void run(){
                dlg.setVisible(true);
            }
        });
        t.start();
        //dlg.setVisible(true);
        try {
            socket = new Socket(ip, 5100);
            
            
            nClientSocket = new Socket(ip,5112);
            System.out.println(location);
            dout = new DataOutputStream(nClientSocket.getOutputStream());
            dout.writeUTF(location);
            dout.flush();
            dout.close();
            nClientSocket.close();
            if(filename.contains(".")==false)
                filename=filename+".zip";
            
            in = socket.getInputStream();
            out = new FileOutputStream("E:"+File.separator+"Downloads"+File.separator+filename);
            

        byte[] bytes = new byte[16*1024];
        int tcount=0;
        int count,per;
        dpb.setValue(50);
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
            tcount+=count;
            // System.out.println(tcount);
            per = (int) ((tcount*100.0)/Tsize);
            j1.setText("Downloading: "+per);
            dpb.setValue(per);
            //System.out.println(per);
            if(dpb.getValue()==100){
                this.sleep(200);
                dlg.setVisible(false);
                parentFrame.setVisible(false);
            }
            
            
            
        }
        //parentFrame.setVisible(false);
        out.close();
        in.close();	
        if(filename.contains(".zip")){
            unzip("E:"+File.separator+"Downloads"+File.separator+filename);
            try {
                this.sleep(100000);
            } catch (InterruptedException ex) {
                Logger.getLogger(downloadClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        File f = new File("E:"+File.separator+"Downloads"+File.separator+filename);
        f.delete();
        }
        socket.close();
        } catch (IOException ex) {
            Logger.getLogger(downloadClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(downloadClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("khatam program client ka ");
    }
}
