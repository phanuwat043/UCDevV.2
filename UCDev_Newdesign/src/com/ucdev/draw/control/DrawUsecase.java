package com.ucdev.draw.control;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;

/**
 *
 * @author UCDev
 */
public class DrawUsecase implements Paintable {

    private String uc_name;
    private Ellipse2D bounds;

    public DrawUsecase() {
        //Overload
    }

    public DrawUsecase(String uc_name, int x, int y) {
        this.uc_name = uc_name;
        bounds = new Ellipse2D.Double(x, y, 100, 50);
    }

    @Override
    public void paint(JComponent parent, Graphics2D g2d) {
        Graphics2D g2 = (Graphics2D) g2d.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //g2.setFont(font);
        FontMetrics fm = g2.getFontMetrics();
        int height = fm.getHeight();
        int width = fm.stringWidth(uc_name);

        g2.setColor(Color.WHITE);
        g2.fill(bounds);
        g2.setColor(Color.BLACK);
        g2.draw(bounds);

        double centreX = bounds.getX() + bounds.getWidth() / 2d;
        double centreY = bounds.getY() + bounds.getHeight() / 2d;
        g2.drawString(uc_name, (int) (centreX - width / 2), (int) (centreY + height / 4));
        g2.dispose();
    }

    @Override
    public boolean contains(Point p) {
        return bounds.contains(p);
    }

    @Override
    public void moveTo(Point2D p) {
        bounds = new Ellipse2D.Double(p.getX(), p.getY(), 100, 50);
    }

    @Override
    public Rectangle2D getBounds() {
        return bounds.getBounds2D();
    }

    @Override
    public void setName(String name) {
        this.uc_name = name;
    }

    @Override
    public String getName() {
        return uc_name;
    }
}
