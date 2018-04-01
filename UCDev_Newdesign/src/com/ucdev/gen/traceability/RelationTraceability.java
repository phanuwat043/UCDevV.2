/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucdev.gen.traceability;

import java.io.BufferedWriter;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author Sorrasak Kaewyao
 */
public class RelationTraceability{

    /**
     * @param args the command line arguments
     */
    public  void createRelationToHTML(int numOfReq,String nameHTMLofReq,int firstOfRel,int lastOfRel) {
       
        
       try {
            List<String> relColumn = new ArrayList(); //เก็บcolumnทั้งหมดแบบไม่คัดตัวซ้ำออก
            List<String> relRow = new ArrayList(); //เก็บRowทั้งหมดแบบไม่คัดตัวซ้ำออก
            List<String> relColumnResult = new ArrayList(); //เก็บcolumnที่เอาตัวซ้ำออกแล้ว
            List<String> relRowResult = new ArrayList(); //เก็บRowที่เอาตัวซ้ำออกแล้ว
            List<String> trList = new ArrayList(); //ใช้เก็บRowที่เป็น<td>เพื่อจัดcolumn
            List<Integer> noCol = new ArrayList(); //เก็บตำแหน่งcolumnที่ทำการtraceไว้
            List<String> typeRelation = new ArrayList(); //เก็บชนิดของความสัมพันธ์แล้วแยกเป็นตัวอักษรI E A

            Map<String, String> map1 = new HashMap<String, String>(); //map เพื่อไว้หาคู่ที่ทำการเซ็ทไว้
             String pathXML = "requirement\\requirementXML\\";
            File fXmlFile = new File(pathXML+"requirement.xml"); //ดึงไฟล์ xml จาก path นี้
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("requirement");
            NodeList relList = doc.getElementsByTagName("relation");
            
            for (int temp = numOfReq; temp < numOfReq+1 ; temp++) {
                Node nNode = nList.item(temp);
                //Element eElement = (Element) nNode;
                
                for (int i = firstOfRel; i < lastOfRel ; i++) {
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Node relNode = relList.item(i);
                    Element relElement = (Element) relNode;
                    
                    relRow.add(relElement.getElementsByTagName("rel2-id").item(0).getTextContent());
                    relColumn.add(relElement.getElementsByTagName("rel1-id").item(0).getTextContent());
                    typeRelation.add(relElement.getElementsByTagName("rel-type").item(0).getTextContent());
                    String row = relElement.getElementsByTagName("rel1-id").item(0).getTextContent();
                    String col = relElement.getElementsByTagName("rel2-id").item(0).getTextContent();
                    map1.put(col, row);
                }
                }
            }
            String firstTdColumn = "<td></td>";
            String trRow = "";
            String resultTable = "";

            //clear column data   
            LinkedHashSet<String> relColumnNo = new LinkedHashSet<String>();
            relColumnNo.addAll(relColumn);
            relColumnResult.addAll(relColumnNo);
            Collections.sort(relColumnResult);

            //clear row data
            LinkedHashSet<String> relRowNo = new LinkedHashSet<String>();
            relRowNo.addAll(relRow);
            relRowResult.addAll(relRowNo);
            Collections.sort(relRowResult);

            for (int i = 0; i < relColumnResult.size(); i++) {
                firstTdColumn = firstTdColumn + "<th style='text-align: center;'>" + relColumnResult.get(i) + "</th>";
            }
            trList.add(firstTdColumn);//เพิ่มcolumnแรกในแถว<td></td>

            String tdAdd = "";

            //add list of rowและระบุตำแหน่งcolumnที่traceในcolumnนั้นๆโดยใช้j
            for (int i = 0; i < relRowResult.size(); i++) {
                String rowCompare = relRowResult.get(i);
                for (int j = 0; j < relColumnResult.size(); j++) {
                    String colCompare = relColumnResult.get(j);
                    if ((map1.get(rowCompare)).equals(colCompare)) {
                        System.out.println(rowCompare + " : " + j);
                        noCol.add(j);
                    }
                }
            }

            //track type
            List intArray = new ArrayList(); //ใช้ในการสร้างแถวจาก<td></td>
            for (int i = 0; i < relColumnResult.size(); i++) {
                intArray.add("<td></td>");
            }

            //track type เป็นการระบุในตำแหน่งที่trace ว่าเป็นความสัมพันธ์แบบไหน
            for (int j = 0; j < relRowResult.size(); j++) {

                for (int i = 0; i < noCol.get(j); i++) {
                    tdAdd = tdAdd + "<td></td>";
                }
                if (typeRelation.get(j).equals("extend")) {
                    tdAdd = tdAdd + "<td>E</td>";
                } else if (typeRelation.get(j).equals("association")) {
                    tdAdd = tdAdd + "<td>A</td>";
                } else if (typeRelation.get(j).equals("inherit")) {
                    tdAdd = tdAdd + "<td>I</td>";
                }
                for (int i = 0; i < (relColumnResult.size()) - (noCol.get(j) + 1); i++) {
                    tdAdd = tdAdd + "<td></td>";
                }
                trRow = "<td style='background-color: #4da6ff;color: white;font-family: sans-serif;'>" + relRowResult.get(j) + "</td>" + tdAdd;
                tdAdd = "";
                trList.add(trRow);
                trRow = "";

            }

            //list column เพื่อสร้างฟอร์มhtml โดยการเขียนทับไฟล์html   
            for (int i = 0; i < trList.size(); i++) {
                resultTable = resultTable + "<tr>" + trList.get(i) + "</tr>";
            }

            String html = "<html><title>Traceability Matrix Requirement</title>"
                    + "<head><style>\n"
                    + "table {\n"
                    + "    border-collapse: collapse;\n"
                    + "    width: 50%;\n"
                    + "    font-family: sans-serif;\n"
                    + "}\n"
                    + "\n"
                    + "th, td {\n"
                    + "    text-align: center;\n"
                    + "    padding: 8px;\n"
                    + "}\n"
                    + "h1{ "
                    + "text-align: center;"
                    + "font-family: sans-serif;"
                    + "}"
                    + "\n"
                    + "tr:nth-child(even){background-color: #f2f2f2}\n"
                    + "\n"
                    + "th {\n"
                    + "    background-color: #4da6ff;\n"
                    + "    color: white;\n"
                    + "}\n"
                    + "</style></head>"
                    + "<body>"
                    + "<h1>"+nameHTMLofReq+"</h1>"
                    + "<table border='1' align='center'>"
                    + resultTable
                    + "</table></body></html>";
            String pathHTML = "requirement\\requirementHTML\\";
            File f = new File(pathHTML+nameHTMLofReq+".html");

            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(f));
                if(f.canWrite()){
                    //JOptionPane.showMessageDialog(null, "Generate Traceability Metrix Success!");
                    System.out.println("success!!!");
                }
                bw.write(html);
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
   }
    }
    
