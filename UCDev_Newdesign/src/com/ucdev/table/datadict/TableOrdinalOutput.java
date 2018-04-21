package com.ucdev.table.datadict;

import com.ucdev.db.control.DBControl;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author filmz
 */
public class TableOrdinalOutput extends javax.swing.JPanel {

    private DefaultTableModel model;
    private final DBControl db_control = new DBControl();
    private static Statement stmt = null;

    private final String varname;
    private final String type;
    private final String uc_id;
    private final String uc_name;

    public TableOrdinalOutput(String id, String name, String varname, String type) {
        initComponents();

        this.varname = varname;
        this.type = type;
        this.uc_id = id;
        this.uc_name = name;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        ordinal_table = new javax.swing.JTable();
        add = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        add_btn = new javax.swing.JButton();

        ordinal_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(ordinal_table);

        add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ucdev/image/if_add_134224.png"))); // NOI18N
        add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addMouseClicked(evt);
            }
        });
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/ucdev/image/if_minus_309051.png"))); // NOI18N
        delete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteMouseClicked(evt);
            }
        });

        add_btn.setText("Add>>>");
        add_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(add_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addComponent(add)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(delete)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(delete)
                    .addComponent(add))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(add_btn))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addMouseClicked
        model = (DefaultTableModel) ordinal_table.getModel();
        for (int i = 0; i < evt.getClickCount(); i++) {
            model.addRow(new Object[0]);
        }
    }//GEN-LAST:event_addMouseClicked

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addActionPerformed

    private void deleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteMouseClicked
        model = (DefaultTableModel) ordinal_table.getModel();

        int select_row = ordinal_table.getSelectedRow();
        model.removeRow(select_row);
    }//GEN-LAST:event_deleteMouseClicked

    private void add_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_btnActionPerformed
        addDataToDB();
    }//GEN-LAST:event_add_btnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JButton add_btn;
    private javax.swing.JButton delete;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable ordinal_table;
    // End of variables declaration//GEN-END:variables

    private void addDataToDB() {
        db_control.getConnectDB();
        try {
            stmt = db_control.conn.createStatement();
            for (int i = 0; i < ordinal_table.getRowCount(); i++) {
                int id = (Integer) ordinal_table.getValueAt(i, 0);
                String value = (String) ordinal_table.getValueAt(i, 1);

                stmt.execute("insert into outputdata values('" + varname + "','" + type + "'"
                        + ",'dataset','" + value + "','" + uc_id + "','" + uc_name + "'," + id + ")");
            }
            stmt.close();
            if (stmt.isClosed()) {
                JOptionPane.showMessageDialog(null, "save data success!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
