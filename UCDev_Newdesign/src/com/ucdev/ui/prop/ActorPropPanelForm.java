package com.ucdev.ui.prop;

import com.ucdev.db.control.DBControl;
import com.ucdev.draw.control.DrawActor;
import com.ucdev.draw.control.DrawPanel;
import javax.swing.JOptionPane;

/**
 *
 * @author filmz
 */
public class ActorPropPanelForm extends javax.swing.JPanel {

    private final DrawPanel draw_panel = new DrawPanel();
    private final DrawActor actor;
    private final DBControl db_control = new DBControl();

    public ActorPropPanelForm(DrawActor ac) {
        initComponents();

        this.actor = ac;

        ac_name_txt.setText(actor.getName());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        ac_id_txt = new javax.swing.JTextField();
        ac_name_txt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ac_desc_txt = new javax.swing.JTextField();
        sterio_type_list = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        save_btn = new javax.swing.JButton();
        cancel_btn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        ac_id_rel = new javax.swing.JTextField();

        jLabel1.setText("Actor ID");

        jLabel2.setText("Actor name");

        jLabel3.setText("Description");

        sterio_type_list.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Person", "System" }));
        sterio_type_list.setToolTipText("");

        jLabel4.setText("Sterio types");

        save_btn.setText("Save");
        save_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_btnActionPerformed(evt);
            }
        });

        cancel_btn.setText("Cancel");

        jLabel5.setText("Usecase Rel");

        ac_id_rel.setEditable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(save_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(cancel_btn))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel1))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(sterio_type_list, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ac_id_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ac_name_txt, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                            .addComponent(ac_desc_txt)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(ac_id_rel, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(ac_id_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(ac_id_rel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ac_name_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(ac_desc_txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(sterio_type_list, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(save_btn)
                    .addComponent(cancel_btn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void save_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_btnActionPerformed
        saveObject();
    }//GEN-LAST:event_save_btnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ac_desc_txt;
    private javax.swing.JTextField ac_id_rel;
    private javax.swing.JTextField ac_id_txt;
    private javax.swing.JTextField ac_name_txt;
    private javax.swing.JButton cancel_btn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JButton save_btn;
    private javax.swing.JComboBox<String> sterio_type_list;
    // End of variables declaration//GEN-END:variables

    public void setActorID(Object obj) {
        String id = String.valueOf(obj);
        ac_id_rel.setText(id);
    }

    private void saveObject() {
        String name = ac_name_txt.getText();
        actor.setName(name);

        String ac_id = ac_id_txt.getText();
        String ac_name = ac_name_txt.getText();
        String ac_desc = ac_desc_txt.getText();
        String ac_type = sterio_type_list.getItemAt(sterio_type_list.getSelectedIndex());
        String ac_obj = ac_id_rel.getText();

        if (!"".equals(ac_id) && ac_id != null) {
            db_control.getConnectDB();
            db_control.insertActorProperties(ac_id, ac_name, ac_desc, ac_type, ac_obj);
        } else {
            JOptionPane.showMessageDialog(this, "Input actor id");
        }
    }
}
