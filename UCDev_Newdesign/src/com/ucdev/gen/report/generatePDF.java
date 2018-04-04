/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucdev.gen.report;

import java.io.File;
import java.io.Reader;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FilterInputStream;

/**
 *
 * @author Home
 */
public class generatePDF {
    public void GenPDF(String file) throws DocumentException, IOException{
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("test.pdf"));
        document.open();
        FileInputStream inputStream = new FileInputStream(file);
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, inputStream,null);
        document.close();
        System.out.println("PDF created!!!");
        
    }
}
