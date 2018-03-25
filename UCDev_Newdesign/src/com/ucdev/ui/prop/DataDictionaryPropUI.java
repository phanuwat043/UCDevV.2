package com.ucdev.ui.prop;

import com.ucdev.draw.control.DrawUsecase;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author filmz
 */
public class DataDictionaryPropUI extends javax.swing.JFrame {

    private DefaultTableModel model;
    private final DrawUsecase usecase;
    
    public DataDictionaryPropUI(DrawUsecase uc,String id) {
        initComponents();
        
        this.usecase = uc;
        
        uc_id_txt.setText(id);
        uc_name_txt.setText(uc.getName());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        uc_id_txt = new javax.swing.JLabel();
        uc_name_txt = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        input_table = new javax.swing.JTable();
        add_input_btn = new javax.swing.JButton();
        remove_intput_btn = new javax.swing.JButton();
        add_input_xml = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        output_table = new javax.swing.JTable();
        remove_output_btn = new javax.swing.JButton();
        add_output_btn = new javax.swing.JButton();
        add_output_xml = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Datadictionary properties");

        jLabel2.setText("Usecase ID");

        jLabel1.setText("Usecase name");

        jLabel3.setText("Input");

        jLabel4.setText("Output");

        uc_id_txt.setText("UC001");

        uc_name_txt.setText("Order price");

        input_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Varname", "Type", "Dataset", "Condition"
            }
        ));
        input_table.setToolTipText("");
        input_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                input_tableMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                input_tableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(input_table);

        add_input_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ucdev/image/if_add_134224.png"))); // NOI18N
        add_input_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                add_input_btnMouseClicked(evt);
            }
        });

        remove_intput_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ucdev/image/if_minus_309051.png"))); // NOI18N
        remove_intput_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                remove_intput_btnMouseClicked(evt);
            }
        });
        remove_intput_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remove_intput_btnActionPerformed(evt);
            }
        });

        add_input_xml.setText("Add");
        add_input_xml.setToolTipText("Add to xml text");

        output_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Varname", "Type", "Dataset", "Action ID"
            }
        ));
        jScrollPane2.setViewportView(output_table);

        remove_output_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ucdev/image/if_minus_309051.png"))); // NOI18N
        remove_output_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remove_output_btnActionPerformed(evt);
            }
        });

        add_output_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ucdev/image/if_add_134224.png"))); // NOI18N
        add_output_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                add_output_btnMouseClicked(evt);
            }
        });

        add_output_xml.setText("Add");
        add_output_xml.setToolTipText("Add to xml text");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1))
                            .addGap(46, 46, 46)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(uc_name_txt)
                                .addComponent(uc_id_txt)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(add_input_xml, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(remove_intput_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(add_input_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(add_output_xml, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(remove_output_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(add_output_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(uc_id_txt))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(uc_name_txt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(add_input_btn)
                    .addComponent(remove_intput_btn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(add_input_xml)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(add_output_btn)
                    .addComponent(remove_output_btn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(add_output_xml)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void add_input_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_input_btnMouseClicked
        model = (DefaultTableModel) input_table.getModel();
        for (int i = 0; i < evt.getClickCount(); i++) {
            model.addRow(new Object[0]);
        }
    }//GEN-LAST:event_add_input_btnMouseClicked

    private void add_output_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_output_btnMouseClicked
        model = (DefaultTableModel) output_table.getModel();
        for (int i = 0; i < evt.getClickCount(); i++) {
            model.addRow(new Object[0]);
        }
    }//GEN-LAST:event_add_output_btnMouseClicked

    private void remove_intput_btnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_remove_intput_btnMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_remove_intput_btnMouseClicked

    private void remove_intput_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remove_intput_btnActionPerformed
        model = (DefaultTableModel) input_table.getModel();

        int select_row = input_table.getSelectedRow();
        model.removeRow(select_row);
    }//GEN-LAST:event_remove_intput_btnActionPerformed

    private void remove_output_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remove_output_btnActionPerformed
        model = (DefaultTableModel) output_table.getModel();

        int select_row = output_table.getSelectedRow();
        model.removeRow(select_row);
    }//GEN-LAST:event_remove_output_btnActionPerformed

    private void input_tableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_input_tableMousePressed
        /*String tip = null;
        java.awt.Point p = evt.getPoint();
        int rowIndex = input_table.rowAtPoint(p);
        int colIndex = input_table.columnAtPoint(p);
        int realColumnIndex = input_table.convertColumnIndexToModel(colIndex);

        if (realColumnIndex == 0) { //Sport column
            tip = ""+input_table.getValueAt(rowIndex, colIndex);
        } else {
            tip = input_table.getToolTipText(evt);
        }*/
    }//GEN-LAST:event_input_tableMousePressed

    private void input_tableMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_input_tableMouseExited
        /*String tip = null;
        java.awt.Point p = evt.getPoint();
        int rowIndex = input_table.rowAtPoint(p);
        int colIndex = input_table.columnAtPoint(p);
        int realColumnIndex = input_table.convertColumnIndexToModel(colIndex);

        if (realColumnIndex == 0) { //Sport column
            tip = ""+input_table.getValueAt(rowIndex, colIndex);
        } else {
            tip = input_table.getToolTipText(evt);
        }
        System.out.println(tip);*/
    }//GEN-LAST:event_input_tableMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add_input_btn;
    private javax.swing.JButton add_input_xml;
    private javax.swing.JButton add_output_btn;
    private javax.swing.JButton add_output_xml;
    private javax.swing.JTable input_table;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable output_table;
    private javax.swing.JButton remove_intput_btn;
    private javax.swing.JButton remove_output_btn;
    private javax.swing.JLabel uc_id_txt;
    private javax.swing.JLabel uc_name_txt;
    // End of variables declaration//GEN-END:variables

}
