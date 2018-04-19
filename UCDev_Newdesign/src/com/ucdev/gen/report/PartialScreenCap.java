/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucdev.gen.report;
import java.awt.AWTException;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Home
 */
public class PartialScreenCap extends JFrame {
    private static final long serialVersionUID = 1L;
    
    public static void main(String[] args) {
        PartialScreenCap p = new PartialScreenCap();
        try{
            Thread.sleep(5000);
            Robot robot = new Robot();
            String filename = "C:\\Users\\Home\\Documents\\NetBeansProjects\\UCDev_Project_2\\test.jpg";
            
            Rectangle rectArea = new Rectangle(400,50,800,500);
            BufferedImage ScreenFullImage = robot.createScreenCapture(rectArea);
            ImageIO.write(ScreenFullImage, "jpg", new File(filename));
            
            p.setLocation(500,500);
            JLabel text = new JLabel("A portion of the screen saved!!");
            p.add(text);
            p.setSize(200,100);
            p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            p.getContentPane().setLayout(new FlowLayout());
            p.setVisible(true);
        }catch(AWTException | IOException | InterruptedException ex){
            System.err.println(ex);
        }
    }
}
