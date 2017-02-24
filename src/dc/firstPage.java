/*
 * This class represents the first pane which the client will view when open the application.
 * It will allow the client to connect to a Hub
 */

/*

package dc;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class firstPage extends javax.swing.JFrame {
    
    private static Socket sock;
    private static String fileName;
    private static BufferedReader stdin;

    public static Thread h;
    //Default Constructor
    public firstPage() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        ip = new javax.swing.JTextField();
        connect = new javax.swing.JButton();
        username = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        connect.setText("Connect");
        connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectActionPerformed(evt);
            }
        });

        jLabel1.setText("Hub's IP Address:");

        jLabel2.setText("Username:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(connect)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ip, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(124, 124, 124))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(60, 60, 60)
                .addComponent(connect)
                .addContainerGap(104, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        
    
    //Connect button function -- connects client to Hub(running on port 4444)
    private void connectActionPerformed(java.awt.event.ActionEvent evt) {                                        
        
        try {
            String ipAdd = ip.getText();
            sock = new Socket(ipAdd, 4444);
            h = new Thread(new HeartBeatClient(ipAdd));
            h.start();
            stdin = new BufferedReader(new InputStreamReader(System.in));
        } catch (Exception e) {
            System.err.println("Cannot connect to the server, try again later.");
            System.exit(1);
        }
        System.out.println("Connected");
        DataOutputStream dout;  
        
        //Sending username of the client to the Hub
        try {
            dout = new DataOutputStream(sock.getOutputStream());
            dout.writeUTF("#@#"+username.getText());  
            dout.flush();
            dout.close();
            sock.close();
        } catch (IOException ex) {
        } 
        
        //Moving to second pane to provide option for upload or search
        secPage ob=new secPage(this,username.getText(),ip.getText());
        this.setVisible(false);
        ob.setVisible(true);
    
        //New thread is opened -- this will provide download facility to other peers
        Thread t =new Thread(new Download());
        t.start();
    }                                       

    public static void main(String args[]) {
        
        //GUI related function
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new firstPage().setVisible(true);
            }
        });
        
         
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton connect;
    private javax.swing.JTextField ip;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField username;
    // End of variables declaration                   
}

*/

/*
 * This class represents the first pane which the client will view when open the application.
 * It will allow the client to connect to a Hub
 */
/*
 * This class represents the first pane which the client will view when open the application.
 * It will allow the client to connect to a Hub
 */

/*
package dc;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class firstPage extends javax.swing.JFrame {
    
    private static Socket sock;
    private static String fileName;
    private static BufferedReader stdin;

    public static Thread h;
    //Default Constructor
    public firstPage() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ip = new javax.swing.JTextField();
        connect = new javax.swing.JButton();
        username = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 204));
        setMaximumSize(new java.awt.Dimension(400, 400));
        setMinimumSize(new java.awt.Dimension(400, 400));
        setPreferredSize(new java.awt.Dimension(450, 400));
        getContentPane().setLayout(null);

        ip.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(ip);
        ip.setBounds(230, 220, 140, 16);

        connect.setText("Connect");
        connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectActionPerformed(evt);
            }
        });
        getContentPane().add(connect);
        connect.setBounds(110, 280, 130, 30);

        username.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(username);
        username.setBounds(230, 180, 140, 16);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Hub's IP Address:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 220, 180, 20);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Username:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(30, 180, 160, 20);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel4.setText("CONNECT TO SERVER");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(40, 30, 440, 40);

        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\welcome\\Desktop\\raat ka final dc\\dn2.jpg")); // NOI18N
        jLabel3.setPreferredSize(new java.awt.Dimension(500, 550));
        getContentPane().add(jLabel3);
        jLabel3.setBounds(-10, -20, 510, 400);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    //Connect button function -- connects client to Hub(running on port 4444)
    private void connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectActionPerformed
        
        try {
            String ipAdd = ip.getText();
            sock = new Socket(ipAdd, 4444);
            h = new Thread(new HeartBeatClient(ipAdd));
            h.start();
            stdin = new BufferedReader(new InputStreamReader(System.in));
        } catch (Exception e) {
            System.err.println("Cannot connect to the server, try again later.");
            System.exit(1);
        }
        System.out.println("Connected");
        DataOutputStream dout;  
        
        //Sending username of the client to the Hub
        try {
            dout = new DataOutputStream(sock.getOutputStream());
            dout.writeUTF("#@#"+username.getText());  
            dout.flush();
            dout.close();
            sock.close();
        } catch (IOException ex) {
        } 
        
        //Moving to second pane to provide option for upload or search
        secPage ob=new secPage(this,username.getText(),ip.getText());
        this.setVisible(false);
        ob.setVisible(true);
    
        //New thread is opened -- this will provide download facility to other peers
        Thread t =new Thread(new Download());
        t.start();
    }//GEN-LAST:event_connectActionPerformed

    public static void main(String args[]) {
        
        //GUI related function
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new firstPage().setVisible(true);
            }
        });
        
         
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton connect;
    private javax.swing.JTextField ip;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
*/
/*
 * This class represents the first pane which the client will view when open the application.
 * It will allow the client to connect to a Hub
 */
package dc;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class firstPage extends javax.swing.JFrame {
    
    private static Socket sock;
    private static String fileName;
    private static BufferedReader stdin;

    public static Thread h;
    //Default Constructor
    public firstPage() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ip = new javax.swing.JTextField();
        connect = new javax.swing.JButton();
        username = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 204));
        setMaximumSize(new java.awt.Dimension(400, 400));
        setMinimumSize(new java.awt.Dimension(400, 400));
        setPreferredSize(new java.awt.Dimension(450, 400));
        getContentPane().setLayout(null);

        ip.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(ip);
        ip.setBounds(230, 220, 140, 16);

        connect.setText("Connect");
        connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectActionPerformed(evt);
            }
        });
        getContentPane().add(connect);
        connect.setBounds(110, 280, 130, 30);

        username.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(username);
        username.setBounds(230, 180, 140, 16);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Hub's IP Address:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 220, 180, 20);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Username:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(30, 180, 160, 20);

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel4.setText("CONNECT TO SERVER");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(40, 30, 440, 40);

        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\priyanka\\Desktop\\final final dc\\raat ka final dc muskan\\dn2.jpg")); // NOI18N
        jLabel3.setPreferredSize(new java.awt.Dimension(500, 550));
        getContentPane().add(jLabel3);
        jLabel3.setBounds(-10, -20, 510, 400);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    //Connect button function -- connects client to Hub(running on port 4444)
    private void connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectActionPerformed
        
        try {
            String ipAdd = ip.getText();
            sock = new Socket(ipAdd, 4444);
            h = new Thread(new HeartBeatClient(ipAdd));
            h.start();
            stdin = new BufferedReader(new InputStreamReader(System.in));
        } catch (Exception e) {
            System.err.println("Cannot connect to the server, try again later.");
            System.exit(1);
        }
        System.out.println("Connected");
        DataOutputStream dout;  
        
        //Sending username of the client to the Hub
        try {
            dout = new DataOutputStream(sock.getOutputStream());
            dout.writeUTF("#@#"+username.getText());  
            dout.flush();
            dout.close();
            sock.close();
        } catch (IOException ex) {
        } 
        
        //Moving to second pane to provide option for upload or search
        secPage ob=new secPage(this,username.getText(),ip.getText());
        this.setVisible(false);
        ob.setVisible(true);
    
        //New thread is opened -- this will provide download facility to other peers
        Thread t =new Thread(new Download());
        t.start();
    }//GEN-LAST:event_connectActionPerformed

    public static void main(String args[]) {
        
        //GUI related function
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new firstPage().setVisible(true);
            }
        });
        
         
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton connect;
    private javax.swing.JTextField ip;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}

