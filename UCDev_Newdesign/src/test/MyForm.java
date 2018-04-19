/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.ucdev.db.control.DBControl;
import com.ucdev.ui.prop.requirementManage;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyForm extends JFrame {
    private final DBControl db_control = new DBControl();
    private DefaultTableModel model;
    private static Statement stmt = null;
    private javax.swing.JTable reqManageTable;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				MyForm frame = new MyForm();
				frame.setVisible(true);
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MyForm() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 580, 242);
		
		getContentPane().setLayout(null);
                
		// ScrollPane for Table
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 41, 494, 90);
		getContentPane().add(scrollPane);

		// Table
		scrollPane.setViewportView(reqManageTable);

		// Model for Table
		DefaultTableModel model = new DefaultTableModel() {

			public Class<?> getColumnClass(int column) {
				switch (column) {
				case 0:
					return Boolean.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
				default:
					return String.class;
				}
			}
		};
		reqManageTable.setModel(model);

		model.addColumn("ID");
		model.addColumn("Description");
		model.addColumn("Link");

		// Data Row
		for (int i = 0; i <= 10; i++) {
			model.addRow(new Object[0]);
			model.setValueAt(false, i, 0);
			model.setValueAt("description", i, 1);
			model.setValueAt("chooseReq", i, 2);
		
		}

		/*** ComboBox ***/
		String[] dataItem = {"req-1", "req-2", "req-3", "req-4"};
		JComboBox countryCombo = new JComboBox(dataItem);
	        TableColumn countryColumn = reqManageTable.getColumnModel().getColumn(2);
	        countryColumn.setCellEditor(new DefaultCellEditor(countryCombo));
                
	        //list data from database
                listRequirement();
		
		//get update
            JButton btnUpdate = new JButton("Update");
            btnUpdate.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    updateRequirement();
                }

            });
		btnUpdate.setBounds(140, 149, 110, 23);
		getContentPane().add(btnUpdate);
                
                
                //get report
	    JButton btnReport = new JButton("Report");
            btnReport.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String url = "C:\\Users\\5730213057\\Documents\\GitHub\\UCDev_2\\UCDevV.2\\UCDev_Newdesign\\requirement\\requirementHTML\\requirementcategories.html";
                    //String url = path.getPathHTML()+"\\traceabilitymatrix.html";
                    File htmlFile = new File(url);
                    try {
                        Desktop.getDesktop().browse(htmlFile.toURI());
                    } catch (IOException ex) {
                        Logger.getLogger(requirementManage.class.getName()).log(Level.SEVERE, null, ex);
                    }
			}

		});
		 btnReport.setBounds(260, 149, 110, 23);
		getContentPane().add( btnReport);

	}
          private void listRequirement() throws SQLException{
        db_control.getConnectDB();
       stmt = db_control.conn.createStatement();
       String data1 = "";
       String data2 = "";
       
       String s = "SELECT * FROM requirement";
       ResultSet rs = stmt.executeQuery(s);
       DefaultTableModel reqModel = (DefaultTableModel) reqManageTable.getModel();
        while (rs.next()) {
            data1 = rs.getString("REQ_ID");
            data2 = rs.getString("REQ_DETAIL");
            Object[] row = { data1, data2};
            reqModel.addRow(row);
        }
      
   } 
          private void updateRequirement() {
        db_control.getConnectDB();
        try {
            stmt = db_control.conn.createStatement();
            stmt.execute("DELETE FROM requirement");
            for (int i = 0; i < reqManageTable.getRowCount(); i++) {
                String req_id = (String) reqManageTable.getValueAt(i, 0);
                String req_detail = (String) reqManageTable.getValueAt(i, 1);
                stmt.execute("insert into requirement values('" + req_id + "','" + req_detail + "')");
            }
            if (stmt.isClosed()) {
                JOptionPane.showMessageDialog(null, "update data success!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
         
}