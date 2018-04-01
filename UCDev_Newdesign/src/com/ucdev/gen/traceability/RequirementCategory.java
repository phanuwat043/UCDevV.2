/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucdev.gen.traceability;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author 5730213057
 */
public class RequirementCategory {
    public void createRequirementCategory() throws ParserConfigurationException, SAXException, IOException{
          
            String pathXML = "requirement\\requirementXML\\";
            File fXmlFile = new File(pathXML+"requirement.xml"); //ดึงไฟล์ xml จาก path นี้
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("requirement");
             ArrayList id = new ArrayList();
             ArrayList des = new ArrayList();
             
             String trRow = "";
             
             
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
               
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String reqID = eElement.getAttribute("id");//id of req...
                    System.out.println("REQ ID : "+reqID);
                    id.add(reqID);//add to arraylist
                    String reqDes = eElement.getAttribute("description");//id of req...
                    System.out.println("REQ DES : "+reqDes);
                    des.add(reqDes);
                    
                }
            }

           
            
            
           
               String firstColumn = "<tr style='background-color: #4da6ff;color: white;font-family: sans-serif;'><th>Requirement ID</th><th>Requirements</th></tr>";
           
               ArrayList trList = new ArrayList();
               trList.add(firstColumn);
                for (int i = 0; i < id.size(); i++) {
                   String linkToRelation = id.get(i)+".html";
                   trRow = "<tr><td style='background-color: #CED8F6;color: white;font-family: sans-serif;'><a href='"+linkToRelation+"'>" + id.get(i) + "</a></td><td>" + des.get(i)+"</td></tr>";
                   trList.add(trRow);
                   trRow = "";
               }
                


            
            
            String resultTable = "";
            //list column   
            for (int i = 0; i < trList.size(); i++) {
                resultTable = resultTable + trList.get(i);
            }

            //ใช้ในการสร้างฟอร์มhtml
            String html = "<html><title>Requirement Categories</title>"
                    + "<head><meta http-equiv=\"Content-Type\" content=\"text/html;charset=UTF-8\"><style>\n"
                    + "table {\n"
                    + "    border-collapse: collapse;\n"
                    + "    width: 70%;\n"
                    + "    font-family: sans-serif;\n"
                    + "}\n"
                    + "\n"
                    + "h1{ "
                    + "text-align: center;"
                    + "font-family: sans-serif;"
                    + "}"
                    + "th, td {\n"
                    + "    text-align: center;\n"
                    + "    padding: 8px;\n"
                    + "}\n"
                    + "\n"
                    + "tr:nth-child(even){background-color: #f2f2f2}\n"
                    + "\n"
                    + "th {\n"
                    + "    background-color: #4da6ff;\n"
                    + "    color: white;\n"
                    + "}\n"
                    + "</style></head>"
                    + "<body>"
                    + "<h1>Requirement Categories</h1>"
                    + "<table border='1' align='center'>"
                    + resultTable
                    + "</table></body></html>";
            String pathHTML = "requirement\\requirementHTML\\";
            File f = new File(pathHTML+"requirementcategories.html");

            
                BufferedWriter bw = new BufferedWriter(new FileWriter(f));
                if(f.canWrite()){
                    System.out.println("requirement categories success!!!"); 
                }
                bw.write(html);
                bw.close();
           
    }
} 
   

