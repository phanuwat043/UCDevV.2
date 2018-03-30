package com.ucdev.ui;

import com.ucdev.db.control.DBControl;
import com.ucdev.draw.control.Association;
import com.ucdev.draw.control.DrawActor;
import com.ucdev.draw.control.DrawPanel;
import com.ucdev.draw.control.DrawUsecase;
import com.ucdev.draw.control.Extends;
import com.ucdev.draw.control.Include;
import com.ucdev.draw.control.Inherit;
import com.ucdev.ui.prop.ActorPropPanelForm;
import com.ucdev.ui.prop.UsecasePropPanelForm;
import com.ucdev.gen.report.GenPDF;
import com.ucdev.gen.report.TransAtInfoHtml;
import com.ucdev.gen.report.TransUcInfoHtml;
import com.ucdev.gen.traceability.RequirementTraceability;
import com.ucdev.ui.prop.DataDict_UI;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;


/**
 *
 * @author UCDev <panuwat16692@gmail.com>
 */
public class MainForm extends javax.swing.JFrame {

    private final MainPanel panel = new MainPanel();
    private final DrawPanel draw_panel = new DrawPanel();
    private final DBControl db_control = new DBControl();
    private DrawActor actor;
    private DrawUsecase usecase;

    private Association asso;
    //private static Extends ext;

    private ActorPropPanelForm ac_prop;
    private UsecasePropPanelForm uc_prop;
    private DataDict_UI datadict_ui;

    private final String[] ac_name = {"Actor 1", "Actor 2", "Actor 3", "Actor 4", "Actor 5", "Actor 6",
        "Actor 7", "Actor 8", "Actor 9", "Actor 10"};
    private final String[] uc_name = {"Usecase 1", "Usecase 2", "Usecase 3", "Usecase 4", "Usecase 5", "Usecase 6",
        "Usecase 7", "Usecase 8", "Usecase 9", "Usecase 10"};
    private int ac = 0;
    private int uc = 0;

    private final Map map = new HashMap();

    public MainForm() {
        initComponents();
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        jPanel1.add(panel);
        setEnableButton(false);

        this.setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent we) {
                String ObjButtons[] = {"Yes", "No"};
                int PromptResult = JOptionPane.showOptionDialog(null,
                        "Are you sure you want to exit?", "UCDev",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                        ObjButtons, ObjButtons[1]);
                if (PromptResult == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        systemBtn = new javax.swing.JButton();
        actorBtn = new javax.swing.JButton();
        usecaseBtn = new javax.swing.JButton();
        associationBtn = new javax.swing.JButton();
        extendBtn = new javax.swing.JButton();
        includeBtn = new javax.swing.JButton();
        inheritBtn = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jFile = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        save_item = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        gen_actor_jmenu = new javax.swing.JMenuItem();
        gen_usecase_jmenu = new javax.swing.JMenuItem();
        gen_pdf_jmenu = new javax.swing.JMenuItem();
        diagram_capture_jmenu = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        TraceMatrix_jMenu = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        data_dict_item = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UCDev new design");

        jPanel1.setLayout(new java.awt.BorderLayout());

        jToolBar1.setRollover(true);

        systemBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ucdev/image/Object.gif"))); // NOI18N
        systemBtn.setToolTipText("System");
        systemBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        systemBtn.setFocusable(false);
        systemBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        systemBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        systemBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                systemBtnActionPerformed(evt);
            }
        });
        jToolBar1.add(systemBtn);

        actorBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ucdev/image/Actor.gif"))); // NOI18N
        actorBtn.setToolTipText("Actor");
        actorBtn.setFocusable(false);
        actorBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        actorBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        actorBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actorBtnActionPerformed(evt);
            }
        });
        jToolBar1.add(actorBtn);

        usecaseBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ucdev/image/UseCase.gif"))); // NOI18N
        usecaseBtn.setToolTipText("UseCase");
        usecaseBtn.setFocusable(false);
        usecaseBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        usecaseBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        usecaseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usecaseBtnActionPerformed(evt);
            }
        });
        jToolBar1.add(usecaseBtn);

        associationBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ucdev/image/Association.gif"))); // NOI18N
        associationBtn.setToolTipText("Association");
        associationBtn.setFocusable(false);
        associationBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        associationBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        associationBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                associationBtnActionPerformed(evt);
            }
        });
        jToolBar1.add(associationBtn);

        extendBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ucdev/image/DependencyRight.gif"))); // NOI18N
        extendBtn.setToolTipText("Extend");
        extendBtn.setFocusable(false);
        extendBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        extendBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        extendBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                extendBtnActionPerformed(evt);
            }
        });
        jToolBar1.add(extendBtn);

        includeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ucdev/image/DependencyRight.gif"))); // NOI18N
        includeBtn.setToolTipText("Include");
        includeBtn.setFocusable(false);
        includeBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        includeBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        includeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                includeBtnActionPerformed(evt);
            }
        });
        jToolBar1.add(includeBtn);

        inheritBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ucdev/image/UniAssociation.gif"))); // NOI18N
        inheritBtn.setToolTipText("Inherit");
        inheritBtn.setFocusable(false);
        inheritBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        inheritBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        inheritBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inheritBtnActionPerformed(evt);
            }
        });
        jToolBar1.add(inheritBtn);

        jPanel1.add(jToolBar1, java.awt.BorderLayout.PAGE_START);

        jFile.setText("File");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("New...");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jFile.add(jMenuItem2);
        jFile.add(jSeparator1);

        save_item.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        save_item.setText("Save...");
        save_item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_itemActionPerformed(evt);
            }
        });
        jFile.add(save_item);

        jMenuBar1.add(jFile);

        jMenu2.setText("Report Mgt");

        gen_actor_jmenu.setText("Gen Actor HTML");
        gen_actor_jmenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gen_actor_jmenuActionPerformed(evt);
            }
        });
        jMenu2.add(gen_actor_jmenu);

        gen_usecase_jmenu.setText("Gen Usecase HTML");
        gen_usecase_jmenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gen_usecase_jmenuActionPerformed(evt);
            }
        });
        jMenu2.add(gen_usecase_jmenu);

        gen_pdf_jmenu.setText("Gen PDF");
        gen_pdf_jmenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gen_pdf_jmenuActionPerformed(evt);
            }
        });
        jMenu2.add(gen_pdf_jmenu);

        diagram_capture_jmenu.setText("Diagram Capture");
        diagram_capture_jmenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diagram_capture_jmenuActionPerformed(evt);
            }
        });
        jMenu2.add(diagram_capture_jmenu);

        jMenuBar1.add(jMenu2);

        jMenu1.setText("Traceability");

        TraceMatrix_jMenu.setText("Traceability Matrix");
        TraceMatrix_jMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TraceMatrix_jMenuActionPerformed(evt);
            }
        });
        jMenu1.add(TraceMatrix_jMenu);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Datadictionary");

        data_dict_item.setText("Datadict");
        data_dict_item.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                data_dict_itemActionPerformed(evt);
            }
        });
        jMenu3.add(data_dict_item);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void actorBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actorBtnActionPerformed
        drawActor();
    }//GEN-LAST:event_actorBtnActionPerformed

    private void usecaseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usecaseBtnActionPerformed
        drawUsecase();
    }//GEN-LAST:event_usecaseBtnActionPerformed

    private void systemBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_systemBtnActionPerformed

    }//GEN-LAST:event_systemBtnActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        createPanel();
        panel.updateUI();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void gen_usecase_jmenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gen_usecase_jmenuActionPerformed
        try {
            new TransUcInfoHtml().GenHTML();
        } catch (SAXException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_gen_usecase_jmenuActionPerformed

    private void associationBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_associationBtnActionPerformed
        drawAssociation();
    }//GEN-LAST:event_associationBtnActionPerformed

    private void extendBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_extendBtnActionPerformed
        drawExtends();
    }//GEN-LAST:event_extendBtnActionPerformed

    private void includeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_includeBtnActionPerformed
        drawIncludes();
    }//GEN-LAST:event_includeBtnActionPerformed

    private void inheritBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inheritBtnActionPerformed
        drawInherits();
    }//GEN-LAST:event_inheritBtnActionPerformed

    private void save_itemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_itemActionPerformed
        new DBControl().getConnectDB();
        new DBControl().queryActorProperties();
        new DBControl().queryUsecaseProperties();
        new DBControl().queryRelation();
        new DBControl().queryRequirement();
    }//GEN-LAST:event_save_itemActionPerformed

    private void gen_pdf_jmenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gen_pdf_jmenuActionPerformed
        genPDF();
    }//GEN-LAST:event_gen_pdf_jmenuActionPerformed

    private void gen_actor_jmenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gen_actor_jmenuActionPerformed
        try {
            new TransAtInfoHtml().GenHTML();
        } catch (SAXException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_gen_actor_jmenuActionPerformed

    private void TraceMatrix_jMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TraceMatrix_jMenuActionPerformed
         traceabilityMetrixToHTML();
    }//GEN-LAST:event_TraceMatrix_jMenuActionPerformed

    private void diagram_capture_jmenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diagram_capture_jmenuActionPerformed
        captureDiagram();
    }//GEN-LAST:event_diagram_capture_jmenuActionPerformed

    private void data_dict_itemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_data_dict_itemActionPerformed
        openDatadictionary();
    }//GEN-LAST:event_data_dict_itemActionPerformed

    /*public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem TraceMatrix_jMenu;
    private javax.swing.JButton actorBtn;
    private javax.swing.JButton associationBtn;
    private javax.swing.JMenuItem data_dict_item;
    private javax.swing.JMenuItem diagram_capture_jmenu;
    private javax.swing.JButton extendBtn;
    private javax.swing.JMenuItem gen_actor_jmenu;
    private javax.swing.JMenuItem gen_pdf_jmenu;
    private javax.swing.JMenuItem gen_usecase_jmenu;
    private javax.swing.JButton includeBtn;
    private javax.swing.JButton inheritBtn;
    private javax.swing.JMenu jFile;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JMenuItem save_item;
    private javax.swing.JButton systemBtn;
    private javax.swing.JButton usecaseBtn;
    // End of variables declaration//GEN-END:variables

    private void createPanel() {
        panel.getSplitPane("untitle", draw_panel);
        setEnableButton(true);
    }

    private void setEnableButton(boolean b) {
        systemBtn.setEnabled(b);
        actorBtn.setEnabled(b);
        associationBtn.setEnabled(b);
        extendBtn.setEnabled(b);
        includeBtn.setEnabled(b);
        inheritBtn.setEnabled(b);
        usecaseBtn.setEnabled(b);
    }

    private void drawActor() {
        actor = new DrawActor(ac_name[ac], 50, 50);
        draw_panel.addActor(actor);
        ac += 1;

        if (actor != null) {
            ac_prop = new ActorPropPanelForm(actor);
            ac_prop.setActorID(actor);
            panel.addPanelToTabPaneDescription(actor.getName(), ac_prop);
        }
    }

    private void drawUsecase() {
        usecase = new DrawUsecase(uc_name[uc], 50, 50);
        draw_panel.addUsecase(usecase);
        uc += 1;

        if (usecase != null) {
            uc_prop = new UsecasePropPanelForm(usecase);
            uc_prop.setUsecaseID(usecase);
            panel.addPanelToTabPaneDescription(usecase.getName(), uc_prop);
        }
    }

    private void drawAssociation() {
        asso = new Association(DrawPanel.actor, DrawPanel.usecase);
        draw_panel.addAssociation(asso.ac, asso.uc);
        db_control.getConnectDB();
        db_control.insertAssoRel(asso.ac, asso.uc);
    }

    private void drawExtends() {
        Extends ext = new Extends(DrawPanel.uc_ext_1, DrawPanel.uc_ext_2);
        draw_panel.addExtends(ext.getUc_first(), ext.getUc_second());
        List map = new ArrayList();
        map.add(ext.getUc_first());
        map.add(ext.getUc_second());
        db_control.getConnectDB();
        db_control.insertExtendsRel(map);
    }

    private void drawIncludes() {
        Include inc = new Include(DrawPanel.uc_inc_1, DrawPanel.uc_inc_2);
        draw_panel.addIncludes(inc.getUc_first(), inc.getUc_second());
        db_control.getConnectDB();
        List map = new ArrayList();
        map.add(inc.getUc_first());
        map.add(inc.getUc_second());
        db_control.insertIncludeRel(map);
    }

    private void drawInherits() {
        Inherit inh = new Inherit(DrawPanel.uc_inh_1, DrawPanel.uc_inh_2);
        draw_panel.addInherits(inh.getUc_first(), inh.getUc_second());
        db_control.getConnectDB();
        List map = new ArrayList();
        map.add(inh.getUc_first());
        map.add(inh.getUc_second());
        db_control.insertInheritRel(map);
    }

    private void openDatadictionary() {
        datadict_ui = new DataDict_UI();
        datadict_ui.show();
    }

  
    private void genPDF() {
        new GenPDF().show();
    }

    private void captureDiagram() {
        try {
            BufferedImage image = new BufferedImage(draw_panel.getWidth(), draw_panel.getHeight(), BufferedImage.TYPE_INT_RGB);
            draw_panel.paint(image.getGraphics());
            ImageIO.write(image, "png", new File("diagram.png"));
        } catch (IOException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void traceabilityMetrixToHTML() {
        new RequirementTraceability().RequirementTraceability();
    }
}
