/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucdev.gen.report;

import com.ucdev.save.control.FileController;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author FilmKhonDee
 */
public class TransUcInfoHtmlRe {

<<<<<<< HEAD
    FileController instance = FileController.getInstance();

    public void GenHTML(String linkpath, String imgpath, String filename) throws ParserConfigurationException, SAXException, IOException {
        String pathXML = instance.getPathXML();
        String pathHTML = instance.getPathHTML();
        File fXmlFile = new File(pathXML + "\\usecase.xml"); //ดึงไฟล์ xml จาก path นี้
=======
    public void GenHTML(String linkpath, String imgpath, String filename) throws ParserConfigurationException, SAXException, IOException {
        FileController path = new FileController();
        String pathXML = path.getPathXML();
        File fXmlFile = new File(pathXML + "//usecase.xml"); //ดึงไฟล์ xml จาก path นี้
>>>>>>> 04f12ea258de04955ae857ff753444a2eb700c8a
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("usecases");
        ArrayList usecaseName = new ArrayList();
        ArrayList usecaseGoal = new ArrayList();
        ArrayList usecasePre = new ArrayList();
        ArrayList usecasePost = new ArrayList();
        ArrayList usecasePri = new ArrayList();
        ArrayList usecaseObject = new ArrayList();
        ArrayList usecaseFlow = new ArrayList();
        ArrayList usecaseAlt = new ArrayList();
        ArrayList usecaseExc = new ArrayList();

        //link "C:\\Users\\Home\\Documents\\NetBeansProjects\\UCDev_Project_2\\AddItemUseCase.html\"
        //img "C:\\Users\\Home\\Documents\\NetBeansProjects\\UCDev_Project_2\\image\\requirement.jpg\"
        System.out.println(linkpath);
        System.out.println(imgpath);

        String createBodyHTML = "";
        String link = "<a href=\"" + linkpath + "\">UseCaseinfo</a>";
        String img = "<img src=\"" + imgpath + "\" alt=\"Mountain\"></img>";
        //System.out.println(img);
        
        createBodyHTML = createBodyHTML+ "<p>" + img + "</p>\n";
        for (int temp = 0; temp < nList.getLength(); temp++) {//for add to arraylist
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                usecaseName.add(eElement.getElementsByTagName("usecase_name").item(0).getTextContent());//add to arraylist
                usecaseGoal.add(eElement.getElementsByTagName("usecase_goal").item(0).getTextContent());//add to arraylist
                usecasePre.add(eElement.getElementsByTagName("usecase_pre").item(0).getTextContent());//add to arraylist
                usecasePost.add(eElement.getElementsByTagName("usecase_post").item(0).getTextContent());//add to arraylist
                usecasePri.add(eElement.getElementsByTagName("usecase_pri").item(0).getTextContent());//add to arraylist
            }
        }

        for (int temp = 0; temp < nList.getLength(); temp++) {//for list from arraylist
            String bodyForm = "<article>\n"
                    + "    <p> <strong> Use Case Name  </strong> : <i>" + usecaseName.get(temp) + "</i> </p>\n"
                    + "    <p> <strong> Use Case Goal  </strong> : <i>" + usecaseGoal.get(temp) + "</i> </p>\n"
                    + "    <p> <strong> Pre-Condition  </strong> : <i>" + usecasePre.get(temp) + "</i> </p>\n"
                    + "    <p> <strong> Post-Condition  </strong> : <i>" + usecasePost.get(temp) + "</i> </p>\n"
                    + "    <p> <strong> Priority  </strong> : <i>" + usecasePri.get(temp) + "</i> </p>\n"
                    //+ "<p>" + img + "</p>\n"
                    + link
                    + "\n"
                    + "</article>";
            createBodyHTML = createBodyHTML + bodyForm;
        }

        String html = "<html xsl:version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\">\n"
                + "<body style=\"background-color:#EEEEEE\">\n"
                + "<style>\n"
                + "div.container {\n"
                + "    width: 100%;\n"
                + "    border: 1px solid gray;\n"
                + "}\n"
                + "\n"
                + "header, footer {\n"
                + "    padding: 1em;\n"
                + "    color: white;\n"
                + "    background-color: #1a8cff;\n"
                + "    clear: left;\n"
                + "    text-align: center;\n"
                + "}\n"
                + "\n"
                + "nav {\n"
                + "    float: left;\n"
                + "    max-width: 160px;\n"
                + "    margin: 0;\n"
                + "    padding: 1em;\n"
                + "}\n"
                + "\n"
                + "nav ul {\n"
                + "    list-style-type: none;\n"
                + "    padding: 0;\n"
                + "}\n"
                + "   \n"
                + "nav ul a {\n"
                + "    text-decoration: none;\n"
                + "}\n"
                + "\n"
                + "article {\n"
                + "    margin-left: 170px;\n"
                + "    border-left: 1px solid gray;\n"
                + "    padding: 1em;\n"
                + "    overflow: hidden;\n"
                + "}\n"
                + "\n"
                + "p {\n"
                + "        color: black;\n"
                + "        font-family: verdana;\n"
                + "        font-size: 100%;\n"
                + "    }\n"
                + "</style>\n"
                + "\n"
                + "<div class=\"container\">\n"
                + "\n"
                + "<header>\n"
                + "   <h2>Usecase HTML Report</h2>\n"
                + "</header>\n"
                + "  \n"
<<<<<<< HEAD
=======
                + "<nav>\n"
                + "  <ul>\n"
                + "    <li><p>Use Case</p></li>\n"
                + "  </ul>\n"
                + "</nav>\n"
                + "\n"
>>>>>>> 04f12ea258de04955ae857ff753444a2eb700c8a
                + createBodyHTML
                + "\n"
                + "<footer>Copyright UCDEV</footer>\n"
                + "\n"
                + "</div>\n"
                + "\n"
                + "\n"
                + "</body>\n"
                + "</html>";

<<<<<<< HEAD
        //String pathHTML = "C:\\Users\\5730213057\\Documents\\GitHub\\UCDev_2\\UCDevV.2_integrated\\UCDev_Newdesign\\";
        String fullfilename = filename + ".html";
        File f = new File(pathHTML + "\\" + fullfilename);
=======
        String pathHTML = path.getPathHTML();
        String fullfilename = filename + ".html";
        File f = new File(pathHTML + "//" + fullfilename);
>>>>>>> 04f12ea258de04955ae857ff753444a2eb700c8a

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            if (f.canWrite()) {
                JOptionPane.showMessageDialog(null, "Generated usecase!");
                //System.out.println("success!!!");
            }
            bw.write(html);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
