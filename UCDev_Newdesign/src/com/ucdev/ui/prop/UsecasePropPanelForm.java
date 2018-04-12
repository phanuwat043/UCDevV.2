package com.ucdev.ui.prop;

import com.ucdev.db.control.DBControl;
import com.ucdev.draw.control.DrawUsecase;
import com.ucdev.model.Alternative;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
//import opennlp.tools.cmdline.postag.POSModelLoader;
//import opennlp.tools.postag.POSModel;
//import opennlp.tools.postag.POSTaggerME;

/**
 *
 * @author filmz
 */
public class UsecasePropPanelForm extends javax.swing.JPanel {

    private final DBControl db_control = new DBControl();
    private final Alternative atl_map = new Alternative();
    private final DrawUsecase usecase;

    private DefaultTableModel model;

    private Map map = new HashMap();
    private final List list = new ArrayList();

    private static Statement stmt = null;

    public UsecasePropPanelForm(DrawUsecase uc) {
        initComponents();

        this.usecase = uc;

        uc_name_txt.setText(usecase.getName());

        add_alternative.setVisible(false);
        remove_alternative.setVisible(false);
        show_ext.setVisible(false);

        remove_exception.setVisible(false);
        add_ext.setVisible(false);
        add_exception.setVisible(false);

        readRequirement();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        uc_id_txt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        uc_name_txt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        uc_goal_txt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        uc_pre_txt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        uc_post_txt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cancel_btn = new javax.swing.JButton();
        save_btn = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        event_table = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        exception_table = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        alternative_table = new javax.swing.JTable();
        add_event = new javax.swing.JButton();
        add_alternative = new javax.swing.JButton();
        add_exception = new javax.swing.JButton();
        remove_event = new javax.swing.JButton();
        remove_alternative = new javax.swing.JButton();
        remove_exception = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        priority_list = new javax.swing.JComboBox<>();
        show_alt = new javax.swing.JButton();
        show_ext = new javax.swing.JButton();
        add_ext = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        uc_id_rel = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        req_combo = new javax.swing.JComboBox<>();
        req_detail_txt = new javax.swing.JLabel();

        jLabel1.setText("Use case ID");

        jLabel2.setText("Use case name");

        jLabel3.setText("Goal");

        jLabel4.setText("Pre-conditions");

        jLabel5.setText("Post-conditions");

        jLabel6.setText("Flow of event");

        jLabel7.setText("Alterrnative flow");

        jLabel8.setText("Exception event");

        cancel_btn.setText("Cancel");

        save_btn.setText("Save");
        save_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_btnActionPerformed(evt);
            }
        });

        event_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Event"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        event_table.setToolTipText("");
        event_table.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                event_tableMouseMoved(evt);
            }
        });
        jScrollPane4.setViewportView(event_table);

        exception_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Exception", "Flow ID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane5.setViewportView(exception_table);

        alternative_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Alternative", "IN", "OUT"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane6.setViewportView(alternative_table);

        add_event.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ucdev/image/if_add_134224.png"))); // NOI18N
        add_event.setToolTipText("Add row");
        add_event.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                add_eventMouseClicked(evt);
            }
        });
        add_event.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_eventActionPerformed(evt);
            }
        });

        add_alternative.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ucdev/image/if_add_134224.png"))); // NOI18N
        add_alternative.setToolTipText("Add row");
        add_alternative.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                add_alternativeMouseClicked(evt);
            }
        });
        add_alternative.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_alternativeActionPerformed(evt);
            }
        });

        add_exception.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ucdev/image/if_add_134224.png"))); // NOI18N
        add_exception.setToolTipText("Add row");
        add_exception.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                add_exceptionMouseClicked(evt);
            }
        });

        remove_event.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ucdev/image/if_minus_309051.png"))); // NOI18N
        remove_event.setToolTipText("Delete row");
        remove_event.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                remove_eventMouseClicked(evt);
            }
        });
        remove_event.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remove_eventActionPerformed(evt);
            }
        });

        remove_alternative.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ucdev/image/if_minus_309051.png"))); // NOI18N
        remove_alternative.setToolTipText("Delete row");
        remove_alternative.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remove_alternativeActionPerformed(evt);
            }
        });

        remove_exception.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ucdev/image/if_minus_309051.png"))); // NOI18N
        remove_exception.setToolTipText("Delete row");
        remove_exception.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remove_exceptionActionPerformed(evt);
            }
        });

        jLabel9.setText("Priority");

        priority_list.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Very high", "High", "Medium", "Low" }));

        show_alt.setText("Add>>>");
        show_alt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                show_altActionPerformed(evt);
            }
        });

        show_ext.setText("Add>>>");
        show_ext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                show_extActionPerformed(evt);
            }
        });

        add_ext.setText("Add>>>");
        add_ext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_extActionPerformed(evt);
            }
        });

        jLabel10.setText("Use case relation");

        uc_id_rel.setEditable(false);

        jLabel11.setText("Requirement");

        req_combo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                req_comboMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel1)
                            .addComponent(jLabel11))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(uc_id_rel, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                            .addComponent(uc_id_txt, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                            .addComponent(req_combo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5))
                                        .addGap(8, 8, 8))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(uc_goal_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(uc_pre_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(uc_post_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(priority_list, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(uc_name_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(45, 45, 45))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(save_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cancel_btn)))
                        .addComponent(req_detail_txt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(219, 219, 219)
                                .addComponent(jLabel7)
                                .addGap(178, 178, 178))
                            .addComponent(remove_alternative, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(add_alternative, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(remove_event, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(add_event, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(show_alt))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(246, 246, 246)
                                .addComponent(show_ext)))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(remove_exception, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(add_exception, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(add_ext, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8))
                .addGap(0, 11, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(uc_id_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(uc_id_rel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(req_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(req_detail_txt, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(uc_name_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(uc_goal_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(uc_pre_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(uc_post_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(priority_list, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(save_btn)
                            .addComponent(cancel_btn)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(show_alt)
                                    .addComponent(show_ext)))
                            .addComponent(remove_event)
                            .addComponent(add_event)
                            .addComponent(remove_alternative)
                            .addComponent(add_alternative)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(add_exception)
                                .addGap(12, 12, 12)
                                .addComponent(add_ext))
                            .addComponent(remove_exception))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void add_eventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_eventActionPerformed

    }//GEN-LAST:event_add_eventActionPerformed

    private void add_eventMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_eventMouseClicked
        model = (DefaultTableModel) event_table.getModel();
        for (int i = 0; i < evt.getClickCount(); i++) {
            model.addRow(new Object[0]);
        }
    }//GEN-LAST:event_add_eventMouseClicked

    private void add_alternativeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_alternativeMouseClicked
        model = (DefaultTableModel) alternative_table.getModel();
        for (int i = 0; i < evt.getClickCount(); i++) {
            model.addRow(new Object[0]);
            setDropDownColumn(alternative_table.getColumnModel().getColumn(1));
            setDropDownColumn(alternative_table.getColumnModel().getColumn(2));
        }
    }//GEN-LAST:event_add_alternativeMouseClicked

    private void add_exceptionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_exceptionMouseClicked
        model = (DefaultTableModel) exception_table.getModel();

        for (int i = 0; i < evt.getClickCount(); i++) {
            model.addRow(new Object[0]);
            setDropDownColumn(exception_table.getColumnModel().getColumn(1));
        }
    }//GEN-LAST:event_add_exceptionMouseClicked

    private void add_alternativeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_alternativeActionPerformed

    }//GEN-LAST:event_add_alternativeActionPerformed

    private void remove_eventMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_remove_eventMouseClicked

    }//GEN-LAST:event_remove_eventMouseClicked

    private void remove_alternativeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remove_alternativeActionPerformed
        model = (DefaultTableModel) alternative_table.getModel();

        int select_row = alternative_table.getSelectedRow();
        model.removeRow(select_row);
    }//GEN-LAST:event_remove_alternativeActionPerformed

    private void remove_eventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remove_eventActionPerformed
        model = (DefaultTableModel) event_table.getModel();

        int select_row = event_table.getSelectedRow();

        model.removeRow(select_row);
    }//GEN-LAST:event_remove_eventActionPerformed

    private void remove_exceptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remove_exceptionActionPerformed
        model = (DefaultTableModel) exception_table.getModel();

        int select_row = exception_table.getSelectedRow();

        model.removeRow(select_row);
    }//GEN-LAST:event_remove_exceptionActionPerformed

    private void save_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_btnActionPerformed
        saveObject();
    }//GEN-LAST:event_save_btnActionPerformed

    private void show_altActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_show_altActionPerformed
        String uc_id = uc_id_txt.getText();
        if (!"".equals(uc_id) && uc_id != null) {
            db_control.getConnectDB();
            db_control.insertFlowOfEvent(uc_id, event_flow_read());

            add_alternative.setVisible(true);
            remove_alternative.setVisible(true);
            show_ext.setVisible(true);
        }
    }//GEN-LAST:event_show_altActionPerformed

    private void show_extActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_show_extActionPerformed
        String uc_id = uc_id_txt.getText();
        if (!"".equals(uc_id) && uc_id != null) {
            alternative_flow_read();

            remove_exception.setVisible(true);
            add_exception.setVisible(true);
            add_ext.setVisible(true);
        }
    }//GEN-LAST:event_show_extActionPerformed

    private void event_tableMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_event_tableMouseMoved
        setToolTipNLP(evt);
    }//GEN-LAST:event_event_tableMouseMoved

    private void add_extActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_extActionPerformed
        String uc_id = uc_id_txt.getText();
        if (!"".equals(uc_id) && uc_id != null) {
            exception_flow_read();
        }
    }//GEN-LAST:event_add_extActionPerformed

    private void req_comboMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_req_comboMouseClicked
        try {
            getSelectReqDetail(req_combo.getSelectedItem().toString());
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_req_comboMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add_alternative;
    private javax.swing.JButton add_event;
    private javax.swing.JButton add_exception;
    private javax.swing.JButton add_ext;
    private javax.swing.JTable alternative_table;
    private javax.swing.JButton cancel_btn;
    private javax.swing.JTable event_table;
    private javax.swing.JTable exception_table;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JComboBox<String> priority_list;
    private javax.swing.JButton remove_alternative;
    private javax.swing.JButton remove_event;
    private javax.swing.JButton remove_exception;
    private javax.swing.JComboBox<String> req_combo;
    private javax.swing.JLabel req_detail_txt;
    private javax.swing.JButton save_btn;
    private javax.swing.JButton show_alt;
    private javax.swing.JButton show_ext;
    private javax.swing.JTextField uc_goal_txt;
    private javax.swing.JTextField uc_id_rel;
    private javax.swing.JTextField uc_id_txt;
    private javax.swing.JTextField uc_name_txt;
    private javax.swing.JTextField uc_post_txt;
    private javax.swing.JTextField uc_pre_txt;
    // End of variables declaration//GEN-END:variables

    public void setUsecaseID(Object obj) {
        String id = String.valueOf(obj);
        uc_id_rel.setText(id);
    }

    private void saveObject() {

        String name = uc_name_txt.getText();
        usecase.setName(name);

        String uc_id = uc_id_txt.getText();
        String uc_name = uc_name_txt.getText();
        String uc_goal = uc_goal_txt.getText();
        String uc_pre = uc_pre_txt.getText();
        String uc_post = uc_post_txt.getText();
        String uc_priority = priority_list.getItemAt(priority_list.getSelectedIndex());
        String uc_obj = uc_id_rel.getText();

        if (!"".equals(uc_id) && uc_id != null) {
            db_control.getConnectDB();
            if (req_combo.getSelectedItem() == null) {
                db_control.insertUsecaseProperties(uc_id, "", uc_name, uc_goal, uc_pre, uc_post, uc_priority, uc_obj);
            } else {
                db_control.insertUsecaseProperties(uc_id, req_combo.getSelectedItem().toString(), uc_name, uc_goal, uc_pre, uc_post, uc_priority, uc_obj);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Input usecase id");
        }
    }

    private Map event_flow_read() {
        for (int i = 0; i < event_table.getRowCount(); i++) {
            map.put(i, event_table.getValueAt(i, 0));
        }
        return map;
    }

    private void alternative_flow_read() {
        String uc_id = uc_id_txt.getText();
        db_control.getConnectDB();
        try {
            stmt = db_control.conn.createStatement();
            for (int i = 0; i < alternative_table.getRowCount(); i++) {
                String detail = (String) alternative_table.getValueAt(i, 0);
                String in = (String) alternative_table.getValueAt(i, 1);
                String out = (String) alternative_table.getValueAt(i, 2);
                stmt.execute("insert into alternative values('" + detail + "','" + uc_id + "'"
                        + ",'" + in + "','" + out + "','A" + i + "')");
            }
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void exception_flow_read() {
        String uc_id = uc_id_txt.getText();
        db_control.getConnectDB();
        try {
            stmt = db_control.conn.createStatement();
            for (int i = 0; i < exception_table.getRowCount(); i++) {
                String detail = (String) exception_table.getValueAt(i, 0);
                String flow = (String) exception_table.getValueAt(i, 1);
                stmt.execute("insert into exception_flow values('" + detail + "','" + uc_id + "'"
                        + ",'" + flow + "','E" + i + "')");
            }
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void setDropDownColumn(TableColumn column) {
        db_control.getConnectDB();
        String uc_id = uc_id_txt.getText();
        List list = db_control.queryFlowOfEvent(uc_id);
        JComboBox comboBox = new JComboBox();

        for (int i = 0; i < list.size(); i++) {
            comboBox.addItem(list.get(i));
        }

        column.setCellEditor(new DefaultCellEditor(comboBox));

        DefaultTableCellRenderer renderer
                = new DefaultTableCellRenderer();
        renderer.setToolTipText("Select flow id");
        column.setCellRenderer(renderer);
    }

    private void setToolTipNLP(MouseEvent evt) {

        //POSModel model = new POSModelLoader().load(new File("en-pos-maxent.bin"));
        //POSTaggerME tagger = new POSTaggerME(model);

        String tip = null;
        String sent[] = null;
        String tags[] = null;

        java.awt.Point p = evt.getPoint();
        int rowIndex = event_table.rowAtPoint(p);
        int colIndex = event_table.columnAtPoint(p);

        try {
            tip = event_table.getValueAt(rowIndex, colIndex).toString();
            String[] split = tip.split(" ");
            String word_recom = "";
            String tag_recom = "";

            for (int i = 0; i < split.length; i++) {
                String[] word = new String[]{split[i]};
                for (int j = 0; j < word.length; j++) {
                    sent = new String[]{word[j]};
                    //tags = tagger.tag(sent);

                    for (String tag : tags) {
                        if ("NN".equals(tag) || "NNS".equals(tag) || "NNP".equals(tag) || "NNPS".equals(tag)
                                || "NP".equals(tag)) {
                            tag_recom = tag_recom + "," + tag;
                            word_recom = word_recom + "," + word[j];
                            String word_ = "<html>"
                                    + "<h3><u>Part of speech</u></h3>"
                                    + "Part of speech tags : <u>" + tag_recom + "</u><br>"
                                    + "Word to use variable : <u>" + word_recom + "</u><br>"
                                    + "</html>";
                            event_table.setToolTipText(word_);
                        }
                    }
                }
            }
        } catch (Exception ex) {
        }
    }

    public void readRequirement() {
        db_control.getConnectDB();
        try {
            stmt = DBControl.conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from requirement");
            while (results.next()) {
                String req_id = results.getString(1);

                req_combo.addItem(req_id);
            }
        } catch (SQLException ex) {
        } catch (Exception ex) {
        }
    }

    public void getSelectReqDetail(String req_id) {
        req_detail_txt.repaint();
        db_control.getConnectDB();
        try {
            stmt = DBControl.conn.createStatement();
            ResultSet results = stmt.executeQuery("select req_detail from requirement where req_id='" + req_id + "'");
            while (results.next()) {
                String detail = results.getString(1);
                req_detail_txt.setText("Detail : " + detail);
            }
        } catch (SQLException ex) {
        } catch (Exception ex) {
        }
    }
}
