/*
 * This class erforms the download operation by sending file to the peer which requires it
*/
/*
package dc;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DownConnection extends Thread{
    Socket clientSocket;
        InputStream in = null;
        OutputStream out = null;
        String loc;
    public DownConnection(Socket client,String l) {
        this.clientSocket = client;
        loc=l;
    }
    
    public void run(){
        try{
          /*  DataInputStream din = new DataInputStream(clientSocket.getInputStream());
            loc = din.readUTF();
            din.close();*/
        //  loc="C:\\Users\\JUHI AGRAWAL\\Documents\\acadcal_2015_16.pdf";
     /*       System.out.println(loc);
        File file = new File(loc);
        // Get the size of the file
        long length = file.length();
        System.out.println("length of file is "+ length);
        byte[] bytes = new byte[16 * 1024];
        in = new FileInputStream(file);
        out = clientSocket.getOutputStream();

        int count;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
        }

        out.close();
        in.close();
        
        clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(downloadClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
*/
/*
 * This class performs the download operation by sending file to the peer which requires it
*/
package dc;
 
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
 
public class DownConnection extends Thread{
    Socket clientSocket;
        InputStream in = null;
        OutputStream out = null;
        String loc;
        String SOURCE_FOLDER="";
        public List<String> fileList;
        String OUTPUT_ZIP_FILE="E:"+File.separator+"Downloads"+File.separator+"send.zip";
 
public void zipIt(String zipFile)
{
   byte[] buffer = new byte[1024];
   String source = "";
   FileOutputStream fos = null;
   ZipOutputStream zos = null;
   try
   {
      try
      {
         source = SOURCE_FOLDER.substring(SOURCE_FOLDER.lastIndexOf(File.separator) + 1, SOURCE_FOLDER.length());
      }
     catch (Exception e)
     {
        source = SOURCE_FOLDER;
     }
     fos = new FileOutputStream(zipFile);
     zos = new ZipOutputStream(fos);
 
     System.out.println("Output to Zip : " + zipFile);
     FileInputStream in = null;
 
     for (String file : this.fileList)
     {
        System.out.println("File Added : " + file);
        ZipEntry ze = new ZipEntry(source + File.separator + file);
        zos.putNextEntry(ze);
        try
        {
           in = new FileInputStream(SOURCE_FOLDER + File.separator + file);
           int len;
           while ((len = in.read(buffer)) > 0)
           {
              zos.write(buffer, 0, len);
           }
        }
        finally
        {
           in.close();
        }
     }
 
     zos.closeEntry();
     System.out.println("Folder successfully compressed");
 
  }
  catch (IOException ex)
  {
     ex.printStackTrace();
  }
  finally
  {
     try
     {
        zos.close();
     }
     catch (IOException e)
     {
        e.printStackTrace();
     }
  }
}
 
public void generateFileList(File node)
{
 
  // add file only
  if (node.isFile())
  {
     fileList.add(generateZipEntry(node.toString()));
 
  }
 
  if (node.isDirectory())
  {
     String[] subNote = node.list();
     for (String filename : subNote)
     {
        generateFileList(new File(node, filename));
     }
  }
}
 
private String generateZipEntry(String file)
{
   return file.substring(SOURCE_FOLDER.length() + 1, file.length());
}
        public DownConnection(Socket client,String l) {
        this.clientSocket = client;
        loc=l;
        SOURCE_FOLDER=loc;
System.out.println(loc);
        }
 
    public void run(){
        try{
            File file;
            /*compress file here*/
            System.out.println("yo");
            if(loc.contains(".")==false){
            fileList = new ArrayList<String>();
            generateFileList(new File(loc));
            zipIt(OUTPUT_ZIP_FILE);
            System.out.println(loc);
        file = new File(OUTPUT_ZIP_FILE);
        // Get the size of the file
        long length = file.length();
            }
            else{
                file = new File(loc);
            }
        //System.out.println("length of file is "+ length);
        byte[] bytes = new byte[16 * 1024];
          
        in = new FileInputStream(file);
        out = clientSocket.getOutputStream();
 
        int count;
        while ((count = in.read(bytes)) > 0) {
            out.write(bytes, 0, count);
        }
 
        out.close();
        in.close();
 
        clientSocket.close();
        if(loc.contains(".")==false){
            File f = new File("E:"+File.separator+"Downloads"+File.separator+"send.zip");
            f.delete();
        }
        } catch (IOException ex) {
            Logger.getLogger(downloadClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}