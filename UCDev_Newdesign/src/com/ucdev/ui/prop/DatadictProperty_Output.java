package com.ucdev.ui.prop;

import com.ucdev.db.control.DBControl;
import static com.ucdev.db.control.DBControl.conn;
import com.ucdev.table.datadict.TableOrdinalOutput;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultListModel;

/**
 *
 * @author filmz
 */
public class DatadictProperty_Output extends javax.swing.JPanel {

    private final DBControl db_control = new DBControl();
    private final String uc_id;
    private final String uc_name;
    private static Statement stmt = null;

    public DatadictProperty_Output(String id, String name) {
        initComponents();

        this.uc_id = id;
        this.uc_name = name;

        DefaultListModel model = new DefaultListModel();
        try {
            db_control.getConnectDB();
            stmt = DBControl.conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from nlp where uc_id='" + id + "'");
            while (results.next()) {
                String word = results.getString(2);
                //System.out.println(word);
                model.addElement(word);
                word_recom_list.setModel(model);

            }
        } catch (SQLException ex) {

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        varname_txt = new javax.swing.JTextField();
        type_panel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        word_recom_list = new javax.swing.JList<>();
        type_combo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        jLabel1.setText("Varname");

        type_panel.setLayout(new java.awt.BorderLayout());

        word_recom_list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                word_recom_listMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(word_recom_list);

        type_combo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Please select type...", "Ordinal" }));
        type_combo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                type_comboActionPerformed(evt);
            }
        });

        jLabel2.setText("Type");

        jLabel3.setText("Recommend");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(varname_txt)
                    .addComponent(type_combo, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(type_panel, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(type_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(22, 22, 22))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(varname_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(type_combo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 52, Short.MAX_VALUE))))
            .addComponent(jSeparator1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void type_comboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_type_comboActionPerformed
        getTableType();
        type_panel.updateUI();
    }//GEN-LAST:event_type_comboActionPerformed

    private void word_recom_listMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_word_recom_listMouseClicked

        varname_txt.setText(word_recom_list.getSelectedValue());
    }//GEN-LAST:event_word_recom_listMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JComboBox<String> type_combo;
    private javax.swing.JPanel type_panel;
    private javax.swing.JTextField varname_txt;
    private javax.swing.JList<String> word_recom_list;
    // End of variables declaration//GEN-END:variables

    public void nlpRecommend(String id) {

    }

    public void getTableType() {
        switch (type_combo.getSelectedIndex()) {
            case 1:
                type_panel.add(new TableOrdinalOutput(uc_id, uc_name, varname_txt.getText(), type_combo.getSelectedItem().toString()));
                break;
        }
    }
}
