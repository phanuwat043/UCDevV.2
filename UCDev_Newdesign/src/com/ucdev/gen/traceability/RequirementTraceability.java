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


/**
 *
 * @author Sorrasak Kaewyao
 */
public class RequirementTraceability {

    /**
     * @param args the command line arguments
     */
    public void RequirementTraceability(){
           try {
            RequirementTraceability req = new RequirementTraceability();
            

            List<String> relColumn = new ArrayList();//เก็บcolumnทั้งหมดแบบไม่คัดตัวซ้ำออก
            List<String> relRow = new ArrayList(); //เก็บRowทั้งหมดแบบไม่คัดตัวซ้ำออก
            List<String> relColumnResult = new ArrayList(); //เก็บcolumnที่เอาตัวซ้ำออกแล้ว
            List<String> relRowResult = new ArrayList(); //เก็บRowที่เอาตัวซ้ำออกแล้ว
            List<String> trList = new ArrayList(); //ใช้เก็บRowที่เป็น<td>เพื่อจัดcolumn
            Map noCol = new HashMap(); //map ตำแหน่งโดยcolumnของแต่ละแถว

            Map map1 = new HashMap(); //map เพื่อไว้หาคู่ที่ทำการเซ็ทไว้ ซึ่งในนี้เป็นแบบmultivalues
            String pathXML = "requirement\\requirementXML\\";
            File fXmlFile = new File(pathXML+"requirement.xml"); //ดึงไฟล์ xml จาก path นี้
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("requirement");
            
            RelationTraceability trace = new RelationTraceability();
            
            int allCountOfRel = 0;
            
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                ArrayList colList = new ArrayList();
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    relRow.add(eElement.getAttribute("id"));//add to arraylist
                    String row = eElement.getAttribute("id");//prepare for put
                    int numRel = eElement.getElementsByTagName("relation").getLength();
                    
                    allCountOfRel = allCountOfRel+numRel;
                    int firstNum = allCountOfRel-numRel;
                    int lastNum = allCountOfRel;
                    
                    System.out.println(firstNum+" : "+lastNum);
                    req.createHTML(row);
                    trace.createRelationToHTML(temp, row,firstNum,lastNum);
                    
                    for (int ucList = 0; ucList < eElement.getElementsByTagName("uc").getLength(); ucList++) {
                        relColumn.add(eElement.getElementsByTagName("uc").item(ucList).getTextContent());//add to arraylist
                        String col = eElement.getElementsByTagName("uc").item(ucList).getTextContent();//prepare for put
                        colList.add(col);
                    }
                    for (int atList = 0; atList < eElement.getElementsByTagName("at").getLength(); atList++) {
                        relColumn.add(eElement.getElementsByTagName("at").item(atList).getTextContent());//add to arraylist
                        String col = eElement.getElementsByTagName("at").item(atList).getTextContent();//prepare for put
                        colList.add(col);
                    }
                    map1.put(row, colList);//multivalues map   
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
            //first column 
            //relRow is all
            for (int i = 0; i < relColumnResult.size(); i++) {
                firstTdColumn = firstTdColumn + "<th>" + relColumnResult.get(i) + "</th>";
            }
            trList.add(firstTdColumn);//เพิ่มcolumnแรกในแถว<td></td>

            String tdAdd = "";

            //add list of row
            ArrayList listTraceByColumn = new ArrayList();
            for (int i = 0; i < relRow.size(); i++) {
                String rowCompare = relRow.get(i); //ชื่อของRow
                for (int j = 0; j < relColumnResult.size(); j++) {
                    String colCompare = relColumnResult.get(j);
                    ArrayList listAllValues = (ArrayList) map1.get(rowCompare);
                    for (int k = 0; k < listAllValues.size(); k++) {
                        String values = (String) listAllValues.get(k);
                        if (colCompare.equals(values)) {
                            listTraceByColumn.add(j);//เป็นตำแหน่งที่traceตามcolumn เก็บเป็นชุดตัวเลข
                        }
                    }//k
                }//j 
                noCol.put(rowCompare, listTraceByColumn); //use to traceโดยชื่อของreqจะมีmultivalues คือตำแหน่งของcolumnที่trace
                listTraceByColumn = new ArrayList(); //new object ของ listTraceByColumn
            }//i

            //track type
            List intArray = new ArrayList(); //ใช้ในการสร้างแถวจาก<td></td>
            for (int i = 0; i < relColumnResult.size(); i++) {
                intArray.add("<td></td>");
            }

            for (int j = 0; j < relRowResult.size(); j++) {
                List listAllColumn = (ArrayList) noCol.get(relRowResult.get(j));
                for (int k = 0; k < listAllColumn.size(); k++) {
                    int listColCom = listAllColumn.get(k).hashCode();
                    intArray.set(listColCom, "<td>*</td>");//setตำแหน่งใน<td></td>ตามตำแหน่งที่ระบุไว้โดยใช้✓

                }

                //System.out.println(tdAdd);
                for (int i = 0; i < intArray.size(); i++) {
                    tdAdd = tdAdd + intArray.get(i);
                }
                String linkToRelation = relRowResult.get(j)+".html";
                trRow = "<td style='background-color: #4da6ff;color: white;font-family: sans-serif;'><a href='"+linkToRelation+"'>" + relRowResult.get(j) + "</a></td>" + tdAdd;
                tdAdd = "";
                trList.add(trRow);
                trRow = "";

                //add new trace
                intArray = new ArrayList();//new object ของ intArray
                for (int i = 0; i < relColumnResult.size(); i++) {
                    intArray.add("<td></td>");
                }

            }

            //list column   
            for (int i = 0; i < trList.size(); i++) {
                resultTable = resultTable + "<tr>" + trList.get(i) + "</tr>";
            }

            //ใช้ในการสร้างฟอร์มhtml
            String html = "<html><title>Traceability Matrix Requirement</title>"
                    + "<head><style>\n"
                    + "table {\n"
                    + "    border-collapse: collapse;\n"
                    + "    width: 80%;\n"
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
                    + "<h1>Traceability Matrix (Requirement : Actor&Usecase)</h1>"
                    + "<table border='1' align='center'>"
                    + resultTable
                    + "</table></body></html>";
            String pathHTML = "requirement\\requirementHTML\\";
            File f = new File(pathHTML+"traceabilitymatrix.html");

            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(f));
                if(f.canWrite()){
                    
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
    
    public void createHTML(String name) throws IOException{
        File htmlFile = new File(name+".html");
        BufferedWriter bw = new BufferedWriter(new FileWriter(htmlFile));
        String html = "<html><title>Traceability Matrix Requirement</title></html>";
              if(htmlFile.canWrite()){
                  System.out.println("created html !!!");  
                }
                bw.write(html);
                bw.close();
            }
    }
    
