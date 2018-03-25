package com.ucdev.ui.prop;

import com.ucdev.db.control.DBControl;
import com.ucdev.draw.control.DrawUsecase;
import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author filmz
 */
public final class DataDict_UI extends javax.swing.JFrame {

    private final DBControl dbControl = new DBControl();
    private static Statement stmt;

    public DataDict_UI() {
        initComponents();

        createUC();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Datadictionary");
        setMinimumSize(new java.awt.Dimension(700, 500));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 456, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 307, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel1);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    public void createUC() {
        dbControl.getConnectDB();
        try {
            stmt = DBControl.conn.createStatement();
            ResultSet results = stmt.executeQuery("select UC_ID,UC_NAME from usecase");
            while (results.next()) {
                GridLayout experimentLayout = new GridLayout(results.getRow(), 0);
                jPanel1.setLayout(experimentLayout);
                String uc_id = results.getString(1);
                String uc_name = results.getString(2);

                jPanel1.add(new DatadictionaryPanel(uc_id, uc_name));
            }
        } catch (SQLException ex) {
        }
    }
}
