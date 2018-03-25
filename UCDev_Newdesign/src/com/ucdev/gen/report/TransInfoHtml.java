/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucdev.gen.report;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author FilmKhonDee
 */
public class TransInfoHtml extends GenActor{
    public void GenHTML(String file)
{
try {
        TransformerFactory tFactory=TransformerFactory.newInstance();
        
        Source xslDoc=new StreamSource("D:\\project_book\\newDesignForActor.xslt");
        Source xmlDoc=new StreamSource(file);

        String outputFileName="ActorInfo.html";

        OutputStream htmlFile=new FileOutputStream(outputFileName);
        Transformer trasform=tFactory.newTransformer(xslDoc);
        trasform.transform(xmlDoc, new StreamResult(htmlFile));
    } 
    catch (FileNotFoundException e) 
    {
        e.printStackTrace();
    }
    catch (TransformerConfigurationException e) 
    {
        e.printStackTrace();
    }
    catch (TransformerFactoryConfigurationError e) 
    {
        e.printStackTrace();
    }
    catch (TransformerException e) 
    {
        e.printStackTrace();
    }
}
}
