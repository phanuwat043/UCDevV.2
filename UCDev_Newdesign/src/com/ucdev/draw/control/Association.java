package com.ucdev.draw.control;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import javax.swing.JComponent;

public class Association {

    public DrawActor ac;
    public DrawUsecase uc;

    private Point2D p1, p2;

    public Association(DrawActor ac, DrawUsecase uc) {
        this.ac = ac;
        this.uc = uc;
    }

    public void paint(JComponent parent, Graphics2D g2d) {
        Graphics2D g2 = (Graphics2D) g2d.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        p1 = new Point2D.Double(ac.getBounds().getCenterX(), ac.getBounds().getCenterY());
        p2 = new Point2D.Double(uc.getBounds().getCenterX() - 50, uc.getBounds().getCenterY());

        g2.draw(new Line2D.Double(p1, p2));
    }
}
