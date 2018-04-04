package com.ucdev.draw.control;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;

/**
 *
 * @author UCDev
 */
public class DrawActor implements Paintable {

    private String ac_name;
    private Rectangle2D bounds;

    public DrawActor() {
        //Overload
    }

    public DrawActor(String ac_name, int x, int y) {
        this.ac_name = ac_name;
        bounds = new Rectangle2D.Double(x, y, 40, 90);
    }

    @Override
    public void paint(JComponent parent, Graphics2D g2d) {
        Graphics2D g2 = (Graphics2D) g2d.create();
        g2.translate(bounds.getX(), bounds.getY());
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawOval(0, 0, 20, 20); // head
        g2.drawLine(10, 20, 10, 50); // bodbounds.getY()
        g2.drawLine(10, 20, 25, 40); // right hand
        g2.drawLine(10, 20, 0 - 5, 40); // left hand
        g2.drawLine(10, 50, 0 - 5, 70); // left leg
        g2.drawLine(10, 50, 25, 70); // right leg
        g2.drawString(ac_name, 0 - 15, 85);
        g2.dispose();
    }

    @Override
    public boolean contains(Point p) {
        return bounds.contains(p);
    }

    @Override
    public void moveTo(Point2D p) {
        bounds = new Rectangle2D.Double(p.getX(), p.getY(), 40, 90);
    }

    @Override
    public Rectangle2D getBounds() {
        return bounds.getBounds2D();
    }

    @Override
    public void setName(String name) {
        this.ac_name = name;
    }

    @Override
    public String getName() {
        return ac_name;
    }
}
