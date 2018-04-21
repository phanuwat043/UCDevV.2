/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ucdev.ui.prop;

import com.ucdev.db.control.DBControl;
import java.awt.Desktop;
import java.awt.EventQueue;
import static java.awt.Frame.NORMAL;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class reqManagement extends JFrame {
        String req_Link = "";
        String req_Boolean = "";
        String resultLink = "";
        String resultBoolean = "";
        private int reqCount = 0;
        private ArrayList reqIDList = new ArrayList();
        private ArrayList reqDesList = new ArrayList();
        private ArrayList reqLinkList = new ArrayList();
        private ArrayList reqBoolean = new ArrayList();
        
        DefaultTableModel model = null;
        private final DBControl db_control = new DBControl();
        private static Statement stmt = null;
        final JTable table = new JTable();
        
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
                            try {
                                reqManagement frame = new reqManagement();
                                frame.setVisible(true);
                            } catch (SQLException ex) {
                                Logger.getLogger(reqManagement.class.getName()).log(Level.SEVERE, null, ex);
                            }
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public reqManagement() throws SQLException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 580, 242);
 
		setTitle("Requirement Management");
		getContentPane().setLayout(null);

		// ScrollPane for Table
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 41, 494, 90);
		getContentPane().add(scrollPane);

		// Table
		
		scrollPane.setViewportView(table);
                 
                //init
                initTableData();
                
		// Model for Table
		model = new DefaultTableModel() {

			public Class<?> getColumnClass(int column) { //embed code
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return Boolean.class;
                                case 3:
					return Boolean.class;
                                case 4:
					return Boolean.class;  
                                case 5:
					return Boolean.class;
                                case 6:
					return Boolean.class;
                                case 7:
					return Boolean.class;
                                case 8:
					return Boolean.class;
                                case 9:
					return Boolean.class;  
                                case 10:
					return Boolean.class;        
				default:
					return Boolean.class;
				}
			}
		};
		table.setModel(model);
		model.addColumn("ID");
		model.addColumn("Description");
                addTableColumn();//add from database
                
                
                
		// create Data Row (pull from database)
		for (int i = 0; i < reqCount; i++) { 
 
                      setValueColumn(i); //set Value to each column
		// Get Row Selected
		JButton updateBtn = new JButton("Update");
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            try {
                                stmt.execute("DELETE FROM requirement");
                                for (int i = 0; i < table.getRowCount(); i++) {
                                    for (int j = 2; j < table.getColumnCount(); j++) {
                                        Boolean chked = Boolean.valueOf(table.getValueAt(i, j).toString());
                                        String dataCol1 = table.getColumnName(j);
                                        
                                        if (chked) { //if check show dataColl
                                            req_Link = getResultLink(dataCol1);
                                            req_Boolean = getResultBoolean("true");
                                        }else{
                                            req_Boolean = getResultBoolean("false");
                                        }
                                    }
                                    updateRequirementLink(req_Link,req_Boolean,i);
                                    clearResultReq();
                                }
                                JOptionPane.showMessageDialog(null, "Update Success!!!");
                            } catch (SQLException ex) {
                                Logger.getLogger(reqManagement.class.getName()).log(Level.SEVERE, null, ex);
                            }
                                
                                 
			}
		});
		updateBtn.setBounds(120, 149, 131, 23);
		getContentPane().add(updateBtn);
                
                JButton reportBtn = new JButton("Report");
		reportBtn.addActionListener(new ActionListener() {
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
		reportBtn.setBounds(310, 149, 131, 23);
		getContentPane().add(reportBtn);
	}
                String n = "null";
                if((reqBoolean.get(0).toString()).equals(n)){  
                }else{
                     prepareRequirement();
                }
    }
        
    
        
    private void prepareRequirement() throws SQLException {
       db_control.getConnectDB();
        stmt = db_control.conn.createStatement();
        
        for (int i = 0; i < table.getRowCount(); i++) {
            for (int j = 2; j < table.getColumnCount(); j++) {
                int num = j - 2;
                ArrayList reqBooleanList = getSplitReq(reqBoolean.get(i).toString());
                String compare = "true";
                if ((reqBooleanList.get(num).toString()).equals(compare)) { //if check show dataColl
                    model.setValueAt(true, i, j);
                } else {
                    model.setValueAt(false, i, j);
                }
            } 
        }
    } 
    
   
    private ArrayList getSplitReq(String reqLinkList){ //set boolean
            ArrayList postReqLink = new ArrayList();
            String[] preReqLink = reqLinkList.split(",");
            for (int i = 0; i < preReqLink.length; i++) {
                 postReqLink.add(preReqLink[i]);
            }
            return postReqLink;
    }
    

    private void updateRequirementLink(String req_link,String req_boolean,int i) {
        db_control.getConnectDB();
        try {
            stmt = db_control.conn.createStatement();
               
                String req_id = (String) table.getValueAt(i, 0);
                String req_detail = (String) table.getValueAt(i, 1);
                stmt.execute("insert into requirement values('" + req_id + "','" + req_detail + "','" + req_link + "','" + req_boolean + "')");
            
            if (stmt.isClosed()) {
                JOptionPane.showMessageDialog(null, "update data success!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    
    private String getResultLink(String link) {
        resultLink = resultLink + "," + link;
        if (resultLink.startsWith(",")) {
            resultLink = resultLink.replace(",", "");
        }
        return resultLink;
    }
    
     private String getResultBoolean(String boo) {
        resultBoolean = resultBoolean + "," + boo;
        if (resultBoolean.startsWith(",")) {
            resultBoolean = resultBoolean.replace(",", "");
        }
        return resultBoolean;
    }
    
    //เปลี่ยนค่าให้0
    private void clearResultReq() {
        this.resultLink = "";
        this.resultBoolean = "";
    }

    private void addTableColumn() throws SQLException{
        db_control.getConnectDB();
        stmt = db_control.conn.createStatement();
        String s = "SELECT * FROM requirement";
        ResultSet rs = stmt.executeQuery(s);
        while(rs.next()){
             model.addColumn(rs.getString("REQ_ID")); 
        }
    }
    
     private void setValueColumn(int i) throws SQLException {
             model.addRow(new Object[0]);
	     model.setValueAt(this.reqIDList.get(i).toString(), i, 0);
	     model.setValueAt(this.reqDesList.get(i).toString(), i, 1);
           for (int j = 2; j < table.getColumnCount(); j++) {
             model.setValueAt(false, i, j);
            }  
    }
     
     
      
     private void initTableData() throws SQLException{
        db_control.getConnectDB();
        stmt = db_control.conn.createStatement();
        String s = "SELECT * FROM requirement";
        ResultSet rs = stmt.executeQuery(s);
        while(rs.next()){
             this.reqCount += 1 ;
             reqIDList.add(rs.getString("REQ_ID"));
             reqDesList.add(rs.getString("REQ_DETAIL"));
             reqLinkList.add(rs.getString("REQ_LINK"));
             reqBoolean.add(rs.getString("REQ_BOOLEAN"));
        } 
     }      
}
