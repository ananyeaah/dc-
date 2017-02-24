/*
 * This class is the second pane the peer sees.
 * It will contain option for upload and search. It will also show the list of online users
 */
package dc;

import java.io.File;
import javax.naming.Context;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;


public class secPage extends javax.swing.JFrame {

    //Variables for c=sharing info with hub
    firstPage parentPage;
    String name, path,username,ip,abt;
    long size;
    
    //Default constructor
    public secPage() {
        initComponents();
    }
    
    //Constructor -- initializes ip and username of the peer
    public secPage(firstPage p,String u,String i) {
        initComponents();
        username=u;
        ip=i;
        parentPage=p;
        about.setText("N/A");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        search = new javax.swing.JButton();
        upload = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        filename = new javax.swing.JLabel();
        activeUser = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        about = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(475, 325));
        setPreferredSize(new java.awt.Dimension(475, 325));
        getContentPane().setLayout(null);

        search.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        search.setText("Search");
        search.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });
        getContentPane().add(search);
        search.setBounds(57, 109, 103, 31);

        upload.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        upload.setText("Upload");
        upload.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        upload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadActionPerformed(evt);
            }
        });
        getContentPane().add(upload);
        upload.setBounds(323, 232, 99, 31);

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("Browse");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(317, 109, 99, 31);
        getContentPane().add(filename);
        filename.setBounds(308, 146, 151, 33);

        activeUser.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        activeUser.setText("View Active Users");
        activeUser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        activeUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activeUserActionPerformed(evt);
            }
        });
        getContentPane().add(activeUser);
        activeUser.setBounds(57, 231, 103, 34);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("SEARCH");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(57, 33, 99, 33);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setText("UPLOAD");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(326, 33, 101, 33);

        jLabel3.setText("About:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(253, 190, 40, 14);

        about.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutActionPerformed(evt);
            }
        });
        getContentPane().add(about);
        about.setBounds(315, 185, 122, 20);

        jLabel4.setIcon(new javax.swing.ImageIcon("C:\\Users\\priyanka\\Desktop\\final final dc\\raat ka final dc muskan\\dn2.jpg")); // NOI18N
        jLabel4.setText("jLabel4");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(0, 0, 460, 290);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //This button will start the upload of given file in new thread named Upload
    private void uploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadActionPerformed
        if(about.isDisplayable())
            abt=about.getText();
        else
            abt="Not Available";
        System.out.println("About"+abt);
        Thread t = new Thread(new Upload(username,ip,path,size,abt));
        t.start();
        
        JOptionPane.showMessageDialog(this,"File Successfully Uploaded!!");
    }//GEN-LAST:event_uploadActionPerformed

    //Browse button -- for selecting file from computer file list
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        JFileChooser browseFile = new JFileChooser();
        browseFile.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        browseFile.showOpenDialog(null);
        File selected = browseFile.getSelectedFile();
        System.out.println(selected.getName()+" "+selected.getAbsolutePath()+" "+selected.length());
        
        filename.setText(selected.getName());
        path=selected.getAbsolutePath();
        if(selected.getName().contains(".")==false)
            size=FileUtils.sizeOfDirectory(new File(path));
        else
            size=selected.length();
       
    }//GEN-LAST:event_jButton1ActionPerformed
    //Search button -- opens a new pane for search options
    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed

        searchPage ob=new searchPage(this,ip);
        this.setVisible(false);
        ob.setVisible(true);
    }//GEN-LAST:event_searchActionPerformed

    private void activeUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activeUserActionPerformed
        // TODO add your handling code here:
        ActiveUserPage ob = new ActiveUserPage(this,ip);
        this.setVisible(false);
        ob.setVisible(true);
    }//GEN-LAST:event_activeUserActionPerformed

    private void aboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_aboutActionPerformed

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new secPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField about;
    private javax.swing.JButton activeUser;
    private javax.swing.JLabel filename;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton search;
    private javax.swing.JButton upload;
    // End of variables declaration//GEN-END:variables
}
