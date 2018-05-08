/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucdev.gen.report;

import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.ucdev.save.control.FileController;

/**
 *
 * @author Home
 */
public class generatePDF {

    public void GenPDF(String file, String filename) throws DocumentException, IOException {
        Document document = new Document();
        FileController instance = FileController.getInstance();
        String fullfilename = instance.getPathHTML() + "//" + filename + ".pdf";
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fullfilename));
        document.open();
        FileInputStream inputStream = new FileInputStream(file);
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, inputStream, null);
        document.close();
        System.out.println("PDF created!!!");

    }
}
