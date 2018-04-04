package com.ucdev.draw.control;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import javax.swing.JComponent;

/**
 *
 * @author filmz
 */
public class Inherit {

    private DrawUsecase uc_first;
    private DrawUsecase uc_second;

    private static final float dash[] = {4f, 0f, 2f};

    public Inherit() {
    }

    public Inherit(DrawUsecase uc_first, DrawUsecase uc_second) {
        this.uc_first = uc_first;
        this.uc_second = uc_second;
    }

    public DrawUsecase getUc_first() {
        return uc_first;
    }

    public DrawUsecase getUc_second() {
        return uc_second;
    }

    public void setUc_first(DrawUsecase uc_first) {
        this.uc_first = uc_first;
    }

    public void setUc_second(DrawUsecase uc_second) {
        this.uc_second = uc_second;
    }

    public void paint(JComponent parent, Graphics2D g2d) {
        Graphics2D g2 = (Graphics2D) g2d.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int x = (int) uc_first.getBounds().getCenterX() + 50;
        int y = (int) uc_first.getBounds().getCenterY();
        int endX = (int) uc_second.getBounds().getCenterX() - 50;
        int endY = (int) uc_second.getBounds().getCenterY();

        g2.drawLine(x, y, endX, endY);
        drawArrow(g2);
    }

    private void drawArrow(Graphics2D g2d) {
        int x = (int) uc_first.getBounds().getCenterX();
        int y = (int) uc_first.getBounds().getCenterY();
        int endX = (int) uc_second.getBounds().getCenterX() - 50;
        int endY = (int) uc_second.getBounds().getCenterY();

        double angle = Math.atan2(endY - y, endX - x);
        AffineTransform tx = g2d.getTransform();
        tx.translate(endX, endY);
        tx.rotate(angle - Math.PI / 2d);
        g2d.setTransform(tx);

        Polygon arrowHead = new Polygon();
        arrowHead.addPoint(0, 5);
        arrowHead.addPoint(-5, -5);
        arrowHead.addPoint(5, -5);
        g2d.draw(arrowHead);
    }
}
