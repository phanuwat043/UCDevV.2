package com.ucdev.gen.traceability;

import com.ucdev.save.control.FileController;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static javassist.CtMethod.ConstParameter.string;
import javax.swing.JOptionPane;
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
    
        ArrayList id = new ArrayList();
        ArrayList des = new ArrayList();
        ArrayList reqrel = new ArrayList();
        
        
    public void createRequirementCategory() throws ParserConfigurationException, SAXException, IOException {
        FileController path = new FileController();
        //String url = "C:\\Users\\5730213057\\Documents\\GitHub\\UCDev_2\\UCDevV.2\\UCDev_Newdesign\\requirement\\requirementXML\\requirement.xml"; //For Test
        //File fXmlFile = new File(url); //For Test
        File fXmlFile = new File(path.getPathXML() + "//requirement.xml"); //ดึงไฟล์ xml จาก path นี้
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("requirement");
       
        

        String trRow = "";

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                String reqID = eElement.getAttribute("id");//id of req...
                System.out.println("REQ ID : " + reqID);
                id.add(reqID);//add to arraylist
                
                String reqDes = eElement.getAttribute("description");//id of req...
                System.out.println("REQ DES : " + reqDes);
                des.add(reqDes);
     
                String reqRel = eElement.getAttribute("reqrelation");//id of req...
                System.out.println("REQ REL : " + reqRel);
                reqrel.add(reqRel);
                 
            }
        }
               String firstColumn = "";
               
               firstColumn = "<tr style='background-color: #4da6ff;color: white;font-family: sans-serif;'><th>Requirement ID</th><th>Requirements</th>"+columnTrace()+"</tr>";
           
        

        ArrayList trList = new ArrayList();
        trList.add(firstColumn);
        for (int i = 0; i < id.size(); i++) {
            String linkToRelation = id.get(i) + ".html";
            trRow = "<tr><td style='background-color: #CED8F6;color: white;font-family: sans-serif;'><a href='" + linkToRelation + "'>" + id.get(i) + "</a></td><td>"+des.get(i)+"</td>"+createRow(reqrel.get(i).toString())+"</tr>";
            trList.add(trRow);
            trRow = "";
        }

        String resultTable = "";
        //list column   
        for (int i = 0; i < trList.size(); i++) {
            resultTable = resultTable + trList.get(i);
        }

        //ใช้ในการสร้างฟอร์มhtml
        String html = "<html><title>Requirement Description</title>"
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
        String pathHTML = path.getPathHTML();
       // String urlTest ="C:\\Users\\5730213057\\Documents\\TestUCDev\\requirementcategories.html"; //For Test
        //File f = new File(urlTest); //For Test
        File f = new File(pathHTML + "//requirementcategories.html");

        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        if (f.canWrite()) {
            JOptionPane.showMessageDialog(null, "Generated requirement description!");
        }
        bw.write(html);
        bw.close(); 
        }
        
       public String columnTrace(){
               String colTrace = ""; 
               for (int i = 0; i < id.size(); i++) {
                    colTrace = colTrace+"<th>"+id.get(i)+"</th>";
               }
               return colTrace;
        }
       
       public String createRow(String reqrel){
               //reqrel[0] = REQ-1,REQ-3
               String colTrace = "";
              
               //track type
            List intCol = new ArrayList(); //ใช้ในการสร้างแถวจาก<td></td>
            ArrayList splitDataReqRel = new ArrayList();
            ArrayList numOfMatch = new ArrayList();
            String[] parts = reqrel.split(",");
            
            for (int i = 0; i < parts.length; i++) { //splited add {REQ-1 REQ-3}
                 splitDataReqRel.add(parts[i]);
            }
            
            for (int i = 0; i < id.size(); i++) { //init column
                intCol.add("<td></td>");
            }
            
            for (int i = 0; i < id.size(); i++) {
                for (int j = 0; j < splitDataReqRel.size(); j++) {
                    if(id.get(i).equals(splitDataReqRel.get(j))){
                       numOfMatch.add(i);
                    }
                }  
            }
           
            for (int i = 0; i < numOfMatch.size(); i++) {
                    int listColCom = numOfMatch.get(i).hashCode();
                    intCol.set(listColCom, "<td>*</td>");//setตำแหน่งใน<td></td>ตามตำแหน่งที่ระบุไว้โดยใช้✓
            }
            
            for (int i = 0; i < intCol.size(); i++) {
                 colTrace = colTrace+intCol.get(i);
            }
            System.out.println("colTrace:"+colTrace);
               return colTrace;
        }
}


