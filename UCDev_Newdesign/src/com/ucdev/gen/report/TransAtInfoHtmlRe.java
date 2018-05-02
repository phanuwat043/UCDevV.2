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
public class TransAtInfoHtmlRe{
    public void GenHTML(String linkpath,String imgpath,String filename) throws SAXException, IOException, ParserConfigurationException {
        FileController path = new FileController();
        String pathXML = path.getPathXML();
        
        File fXmlFile = new File(pathXML + "//actor.xml"); //ดึงไฟล์ xml จาก path นี้
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("actor");
        ArrayList actorName = new ArrayList();
        ArrayList actorDes = new ArrayList();
        ArrayList actorType = new ArrayList();
        
        String createBodyHTML = "";
        String link = "<a href=\""+linkpath+"\">UseCaseinfo</a>";
        String img = "<img src=\""+imgpath+"\" alt=\"Mountain\"></img>";
        
        for (int temp = 0; temp < nList.getLength(); temp++) {//for add to arraylist
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                actorName.add(eElement.getElementsByTagName("actor_name").item(0).getTextContent());//add to arraylist
                actorDes.add(eElement.getElementsByTagName("actor_description").item(0).getTextContent());//add to arraylist
                actorType.add(eElement.getElementsByTagName("actor_type").item(0).getTextContent());//add to arraylist
            }
        }
 
        for (int temp = 0; temp < nList.getLength(); temp++) {//for list from arraylist
            String bodyForm = "<article>\n"
                    + "    <p> <strong> Actor Name </strong> : <i>" + actorName.get(temp) + "</i> </p>\n"
                    + "    <p> <strong> Description </strong> : <i>" + actorDes.get(temp) + "</i> </p>\n"
                    + "    <p> <strong> Type </strong> : <i>" + actorType.get(temp) + "</i> </p>\n"
                    + "<p>" + img + "</p>\n"
                    + link
                    + "\n"
                    + "</article>";
            createBodyHTML = createBodyHTML+bodyForm;
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
                + "   <h1>UC DEV : use case development for supporting scenario-based requirement</h1>\n"
                + "</header>\n"
                + "  \n"
                + "<nav>\n"
                + "  <ul>\n"
                + "    <li><p>Actor</p></li>\n"
                + "  </ul>\n"
                + "</nav>\n"
                + "\n"
                +createBodyHTML
                + "\n"
                + "<footer>Copyright UCDEV</footer>\n"
                + "\n"
                + "</div>\n"
                + "\n"
                + "\n"
                + "</body>\n"
                + "</html>";
        String pathHTML = path.getPathHTML();
        String fullfilename = filename+".html";
        File f = new File(pathHTML + "//" + fullfilename);

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            if (f.canWrite()) {
                //JOptionPane.showMessageDialog(null, "Generate Traceability Metrix Success!");
                System.out.println("success!!!");
            }
            bw.write(html);
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


