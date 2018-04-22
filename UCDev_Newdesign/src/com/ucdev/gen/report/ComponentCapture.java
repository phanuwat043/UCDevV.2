/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucdev.gen.report;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/**
 *
 * @author Home
 */
public class ComponentCapture {
    public static BufferedImage getScreenShot(Component component){
        BufferedImage image = new BufferedImage(component.getWidth(),component.getHeight(),BufferedImage.TYPE_INT_RGB);
        component.paint(image.getGraphics());
        return image;
    }
    
    public static void SaveScreenShot(Component component,String path,String filename) throws Exception{
        BufferedImage img = getScreenShot(component);
        String fullfilename = filename+".png";
        String finalpath = path+""+"/"+fullfilename;
        File f = new File(finalpath);
        System.out.println(path);
        System.out.println(filename);
        System.out.println(finalpath);
        ImageIO.write(img, "png", f);
    }
}
