package com.ucdev.draw.control;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;

/**
 *
 * @author UCDev <panuwat16692@gmail.com>
 */
public interface Paintable {

    public void paint(JComponent parent, Graphics2D g2d);

    public boolean contains(Point p);

    public void moveTo(Point2D p);

    public Rectangle2D getBounds();
    
    public void setName(String name);
    
    public String getName();
}
