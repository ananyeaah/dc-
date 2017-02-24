/*
 * This class represents the hub's gui.This shows up when the system containing the hub runs the aaplication.
 * It shows two tables - list of all the files uploaded in the hub and the list of clients currently connected to the hub.
 */

package dc;

import static dc.ActiveUserPage.str;
import static dc.HeartBeat.conn;
import static dc.Hub.stmt;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;


public class Hub extends javax.swing.JFrame {

    /**
     * Creates new Hub GUI
     */
    private static ServerSocket serverSocket; 
    private static Socket clientSocket = null;
    
    private static ServerSocket HbServerSocket;
    private static Socket HbclientSocket;
    
    // JDBC details
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
    static final String DB_URL = "jdbc:mysql://localhost/peertopeer"; // jdbc:mysql://server_address/Database_name 

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";
  
    static Connection conn = null;
    static Statement stmt = null;
    DefaultTableModel t;
  
    //Default constructor
    public Hub() {
        initComponents();
        t=(DefaultTableModel)userList.getModel();
        t.setRowCount(0);
      
        
        //Esablishing Server Socket
        try {
            serverSocket = new ServerSocket(4444);
            //HbServerSocket = new ServerSocket(4480);
            System.out.println("Server started.");
        } catch (Exception e) {
            System.err.println("Port already in use.");
            System.exit(1);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        userList = new javax.swing.JTable();
        Show = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        userList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Username", "Ip address"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(userList);

        Show.setText("Show");
        Show.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowActionPerformed(evt);
            }
        });

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        jTextField1.setText("Hub");
        jTextField1.setBorder(new javax.swing.border.MatteBorder(null));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(Show)
                        .addGap(0, 181, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(172, 172, 172)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(Show)
                .addGap(38, 38, 38))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowActionPerformed
        // TODO add your handling code here:
        
        try{
         
            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            
            String sql;
            sql = "SELECT * FROM activeUser";
            ResultSet rs;
            rs = stmt.executeQuery(sql);
            int count=1;
            while(rs.next()){
                //Retrieve by column name
                String user,ip;
                user = rs.getString("username");
                ip = rs.getString("ip");
                
                t.addRow(new String[]{});
                userList.getModel().setValueAt(user,count-1, 0);
                userList.getModel().setValueAt(ip,count-1,1);
                count++;
            }
  
            rs.close();
            stmt.close();
               
                    //stmt = conn.createStatement();
                     //sql = "DELETE FROM `activeUser`";
                    //System.out.println(sql);
                    //stmt.executeUpdate(sql);
                    //stmt.close();
                    conn.close(); 
            
            
            
        } catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }
            catch(SQLException se2){}// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }
            catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }
   
        
        
        
        
        
    }//GEN-LAST:event_ShowActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    public static void main(String args[]) {
        
        //GUI Related function
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Hub().setVisible(true);
            }
        });
        
        System.out.println("Creating statement...");
        try{
         
            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();

            String sql;
            sql = "SELECT * FROM filelist";
            ResultSet rs;
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                //Retrieve by column name
                int id;
                id = rs.getInt("id");
                String name = rs.getString("username");

                 //Display values
                 System.out.print("ID: " + id);
                 //System.out.print(", Age: " + age);
                 System.out.println(", Name: " + name+"\n");
            }
  
            rs.close();
            stmt.close();
               
                    stmt = conn.createStatement();
                     sql = "DELETE FROM `activeUser`";
                    System.out.println(sql);
                    stmt.executeUpdate(sql);
                    stmt.close();
                    conn.close(); 
            
            
            
        } catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }
            catch(SQLException se2){}// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }
            catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
   //   
   
   //code to add active user list 
    
   
   
   
   
   
   
   
   
   
   
   
   
   
        try{
        //Establishing connection with client
        while (true) {
            try {
                clientSocket = serverSocket.accept();
                System.out.println("Accepted connection : " + clientSocket);

                Thread t = new Thread(new CLIENTConnection(clientSocket));
                t.start();
                
                Thread t1 = new Thread(new HeartBeat());
                t1.start();
            } catch (Exception e) {
                System.err.println("Error in connection attempt.");
            }
        }
        }
        finally{
            try{
            clientSocket.close();
            HbclientSocket.close();
            serverSocket.close();
            HbServerSocket.close();
            Class.forName("com.mysql.jdbc.Driver");

                    System.out.println("Connecting to database...");
                    conn = DriverManager.getConnection(DB_URL,USER,PASS);
                    stmt = conn.createStatement();
                    String sql = "DELETE FROM `activeUser`";
                    System.out.println(sql);
                    stmt.executeUpdate(sql);
                    stmt.close();
                    conn.close(); 
            }
            catch(Exception e){}
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Show;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable userList;
    // End of variables declaration//GEN-END:variables
}

