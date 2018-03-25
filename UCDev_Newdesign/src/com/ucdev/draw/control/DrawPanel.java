package com.ucdev.draw.control;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

/**
 *
 * @author UCDev <panuwat16692@gmail.com>
 */
public class DrawPanel extends JPanel {

    private final List<DrawActor> actors;
    private final List<DrawUsecase> usecases;
    private final List<Association> associations;
    private final List<Extends> extend_s;
    private final List<Include> inc_s;
    private final List<Inherit> inh_s;

    private final Map map = new HashMap();

    private final Extends ext = new Extends();
    private final Include inc = new Include();
    private final Inherit inh = new Inherit();

    public static DrawUsecase usecase;
    public static DrawActor actor;
    public static DrawUsecase uc_ext_1, uc_ext_2;//Extend
    public static DrawUsecase uc_inc_1, uc_inc_2;//Include
    public static DrawUsecase uc_inh_1, uc_inh_2;//Inherit

    private Paintable selectedShape;

    private Point2D offset;

    public DrawPanel() {
        setBackground(Color.WHITE);

        actors = new ArrayList<DrawActor>();
        usecases = new ArrayList<DrawUsecase>();
        associations = new ArrayList<Association>();
        extend_s = new ArrayList<Extends>();
        inc_s = new ArrayList<Include>();
        inh_s = new ArrayList<Inherit>();

        this.addMouseListener(new MouseEvent());
        this.addMouseMotionListener(new MouseEvent());
    }

    protected List<Paintable> getShape() {
        ArrayList<Paintable> shapes = new ArrayList<Paintable>(usecases);
        shapes.addAll(actors);
        return shapes;
    }

    public void addActor(DrawActor actor) {
        actors.add(actor);
        repaint();
    }

    public void addUsecase(DrawUsecase usecase) {
        usecases.add(usecase);
        repaint();
    }

    public void addAssociation(DrawActor ac, DrawUsecase uc) {
        associations.add(new Association(ac, uc));
        repaint();
    }

    public void addExtends(DrawUsecase uc_1, DrawUsecase uc_2) {
        extend_s.add(new Extends(uc_1, uc_2));
        repaint();
    }

    public void addIncludes(DrawUsecase uc_1, DrawUsecase uc_2) {
        inc_s.add(new Include(uc_1, uc_2));
        repaint();
    }

    public void addInherits(DrawUsecase uc_1, DrawUsecase uc_2) {
        inh_s.add(new Inherit(uc_1, uc_2));
        repaint();
    }

    public Map assoRel(DrawActor ac, DrawUsecase uc) {
        map.put(ac, uc);
        return map;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        for (DrawActor a : actors) {
            a.paint(this, g2);
        }

        for (DrawUsecase u : usecases) {
            u.paint(this, g2);
        }

        for (Association asso : associations) {
            asso.paint(this, g2);
        }

        for (Extends ex : extend_s) {
            ex.paint(this, g2);
        }

        for (Include in : inc_s) {
            in.paint(this, g2);
        }

        for (Inherit in : inh_s) {
            in.paint(this, g2);
        }

        g2.dispose();
    }

    class MouseEvent extends MouseAdapter {

        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {

            for (DrawActor ac : actors) {
                if (ac.contains(e.getPoint())) {
                    if (ac instanceof DrawActor) {
                        actor = ac;
                    }
                }
            }

            for (DrawUsecase uc : usecases) {
                if (uc.contains(e.getPoint())) {
                    if (uc instanceof DrawUsecase) {
                        usecase = uc;
                        popup();
                    }
                }
            }
        }

        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {
            for (Paintable p : getShape()) {
                if (p.contains(e.getPoint())) {
                    // Selected
                    selectedShape = p;
                    offset = new Point2D.Double(e.getX() - p.getBounds().getX(), e.getY() - p.getBounds().getY());
                    break;
                }
            }
        }

        @Override
        public void mouseDragged(java.awt.event.MouseEvent e) {
            if (selectedShape != null) {
                Point2D p = new Point2D.Double(e.getX() - offset.getX(), e.getY() - offset.getY());
                selectedShape.moveTo(p);
                repaint();
            } else {

            }
        }
    }

    public void popup() {
        JPopupMenu popup = new JPopupMenu();
        JMenuItem first_ext = new JMenuItem("Base usecase extend");
        JMenuItem last_ext = new JMenuItem("Target usecase extend");
        JMenuItem first_inc = new JMenuItem("Base usecase include");
        JMenuItem last_inc = new JMenuItem("Target usecase include");
        JMenuItem first_inh = new JMenuItem("Base usecase inherit");
        JMenuItem last_inh = new JMenuItem("Target usecase inherit");
//extends
        first_ext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uc_ext_1 = usecase;
                ext.setUc_first(uc_ext_1);
                uc_ext_2 = null;
                uc_inc_1 = null;
                uc_inc_2 = null;
                uc_inh_1 = null;
                uc_inh_2 = null;
            }
        });

        last_ext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uc_ext_2 = usecase;
                ext.setUc_second(uc_ext_2);
                uc_inc_1 = null;
                uc_inc_2 = null;
                uc_inh_1 = null;
                uc_inh_2 = null;
            }
        });
//include
        first_inc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uc_ext_1 = null;
                uc_ext_2 = null;
                uc_inc_1 = usecase;
                inc.setUc_first(uc_inc_1);
                uc_inc_2 = null;
                uc_inh_1 = null;
                uc_inh_2 = null;
            }
        });

        last_inc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uc_ext_1 = null;
                uc_ext_2 = null;
                uc_inc_2 = usecase;
                inc.setUc_second(uc_inc_2);
                uc_inh_1 = null;
                uc_inh_2 = null;
            }
        });
//inherit
        first_inh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uc_ext_1 = null;
                uc_ext_2 = null;
                uc_inc_1 = null;
                uc_inc_2 = null;
                uc_inh_1 = usecase;
                inh.setUc_first(uc_inh_1);
                uc_inh_2 = null;
            }
        });

        last_inh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uc_ext_1 = null;
                uc_ext_2 = null;
                uc_inc_1 = null;
                uc_inc_2 = null;
                uc_inh_2 = usecase;
                inh.setUc_second(uc_inh_2);
            }
        });

        popup.add(first_ext);
        popup.add(last_ext);
        popup.add(first_inc);
        popup.add(last_inc);
        popup.add(first_inh);
        popup.add(last_inh);
        this.setComponentPopupMenu(popup);
    }
}
