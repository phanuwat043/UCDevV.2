package com.ucdev.save.control;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author filmz
 */
public class FileController {

    private static FileController instance = null;

    private static String FILE_NAME;

    public static FileController getInstance() {
        if (instance == null) {
            instance = new FileController();
        }
        return instance;
    }

    public String getPathXML(){
        String path = "";  
       if (getFILE_NAME() != null) {   
       path = "C:\\UCDev\\" + getFILE_NAME();
       }
       return path;
    }
    
    public String getPathHTML(){
           String path = "";  
       if (getFILE_NAME() != null) {   
       path = "C:\\UCDev\\" + getFILE_NAME()+"\\Documents";
       }
       return path;
    }
    
    public void createFolder() {
        if (getFILE_NAME() != null) {
            Path path = Paths.get("C:\\UCDev\\" + getFILE_NAME() + "\\Documents");
            //if directory exists?
            if (!Files.exists(path)) {
                try {
                    Files.createDirectories(path);
                } catch (IOException e) {
                    //fail to create directory
                    e.printStackTrace();
                }
            }
        }
    }

    public File readFolder() {
        File root = null;
        if (getFILE_NAME() != null) {
            root = new File("C:\\UCDev\\" + getFILE_NAME());
        }
        return root;
    }

    public File readFolderToExportData() {
        File root = null;
        if (getFILE_NAME() != null) {
            root = new File("C:\\UCDev\\" + getFILE_NAME() + "\\Documents");
        }
        return root;
    }

    public void readFileXml(String file) {
        try {
            File fXmlFile = new File(file);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeValue());
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getFILE_NAME() {
        return FILE_NAME;
    }

    public void setFILE_NAME(String FILE_NAME) {
        FileController.FILE_NAME = FILE_NAME;
    }
}
