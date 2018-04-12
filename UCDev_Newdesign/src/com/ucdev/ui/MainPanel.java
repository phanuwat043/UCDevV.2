package com.ucdev.ui;

import com.ucdev.draw.control.DrawPanel;
import com.ucdev.save.control.FileController;
import java.io.File;
import java.io.IOException;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author UCDev <panuwat16692@gmail.com>
 */
public final class MainPanel extends javax.swing.JPanel {

    private DocumentBuilderFactory factory;
    private DocumentBuilder builder;
    private JTree tree;
    private FileTreeModel model;
    private File file;

    public MainPanel() {
        initComponents();

        setFile(file);
    }

    public void setFile(File file) {
        model = new FileTreeModel(file);
        file_tree.setModel(model);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jSplitPane2 = new javax.swing.JSplitPane();
        bottom_panel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dataTabpane = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        drawTabpane = new javax.swing.JTabbedPane();
        jSplitPane3 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        file_tree = new javax.swing.JTree();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        xml_view_tabpane = new javax.swing.JTabbedPane();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.BorderLayout());

        jSplitPane1.setDividerLocation(100);

        jSplitPane2.setDividerLocation(300);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        bottom_panel.setBackground(new java.awt.Color(255, 255, 255));
        bottom_panel.setLayout(new javax.swing.BoxLayout(bottom_panel, javax.swing.BoxLayout.LINE_AXIS));

        jScrollPane1.setViewportView(dataTabpane);

        bottom_panel.add(jScrollPane1);

        jSplitPane2.setRightComponent(bottom_panel);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        jScrollPane2.setViewportView(drawTabpane);

        jPanel2.add(jScrollPane2);

        jSplitPane2.setLeftComponent(jPanel2);

        jSplitPane1.setRightComponent(jSplitPane2);

        jSplitPane3.setDividerLocation(250);
        jSplitPane3.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.BorderLayout());

        file_tree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                file_treeMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(file_tree);

        jPanel1.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jSplitPane3.setTopComponent(jPanel1);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new java.awt.BorderLayout());

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane4.setViewportView(xml_view_tabpane);

        jPanel3.add(jScrollPane4, java.awt.BorderLayout.CENTER);

        jSplitPane3.setRightComponent(jPanel3);

        jSplitPane1.setLeftComponent(jSplitPane3);

        add(jSplitPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void file_treeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_file_treeMouseClicked
        file_tree.updateUI();
        File file_select = (File) file_tree.getLastSelectedPathComponent();
        if (file_select == null) {

        } else {
            ViewTree view_tree = new ViewTree();
            view_tree.setFile_(file_select);
            view_tree.createUI();
        }
    }//GEN-LAST:event_file_treeMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottom_panel;
    private javax.swing.JTabbedPane dataTabpane;
    private javax.swing.JTabbedPane drawTabpane;
    private javax.swing.JTree file_tree;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JTabbedPane xml_view_tabpane;
    // End of variables declaration//GEN-END:variables

    public void getSplitPane(String name, DrawPanel p) { //dataTabpane is part for add tab details of usecase and actor 
        drawTabpane.addTab(name, p);
    }

    public void addPanelToTabPaneDescription(String name, JPanel panel) {
        dataTabpane.addTab(name, panel);
    }

    public void setTitle(String name) {
        dataTabpane.setTitleAt(getSelectedIndex(), name);
    }

    public int getSelectedIndex() {
        return dataTabpane.getModel().getSelectedIndex();
    }

    class FileTreeModel implements TreeModel {

        protected File root;

        public FileTreeModel(File root) {
            this.root = root;
        }

        @Override
        public Object getRoot() {
            return root;
        }

        @Override
        public Object getChild(Object parent, int index) {
            String[] children = ((File) parent).list();
            if ((children == null) || (index >= children.length)) {
                return null;
            }
            return new File((File) parent, children[index]);
        }

        @Override
        public int getChildCount(Object parent) {
            String[] children = ((File) parent).list();
            if (children == null) {
                return 0;
            }
            return children.length;
        }

        @Override
        public boolean isLeaf(Object node) {
            return ((File) node).isFile();
        }

        @Override
        public void valueForPathChanged(TreePath path, Object newValue) {

        }

        @Override
        public int getIndexOfChild(Object parent, Object child) {
            String[] children = ((File) parent).list();
            if (children == null) {
                return -1;
            }
            String childname = ((File) child).getName();
            for (int i = 0; i < children.length; i++) {
                if (childname.equals(children[i])) {
                    return i;
                }
            }
            return -1;
        }

        @Override
        public void addTreeModelListener(TreeModelListener l) {

        }

        @Override
        public void removeTreeModelListener(TreeModelListener l) {

        }

    }

    class ViewTree extends DefaultHandler {

        private JTree xmlJTree;
        DefaultTreeModel treeModel;
        int lineCounter;
        DefaultMutableTreeNode base = new DefaultMutableTreeNode("XML Viewer");
        File file_;

        @Override
        public void startElement(String uri, String localName, String tagName, Attributes attr) throws SAXException {

            DefaultMutableTreeNode current = new DefaultMutableTreeNode(tagName);

            base.add(current);
            base = current;

            for (int i = 0; i < attr.getLength(); i++) {
                DefaultMutableTreeNode currentAtt = new DefaultMutableTreeNode(attr.getLocalName(i) + " = "
                        + attr.getValue(i));
                base.add(currentAtt);
            }
        }

        @Override
        public void startDocument() throws SAXException {
            super.startDocument();
            base = new DefaultMutableTreeNode("XML Viewer");
            ((DefaultTreeModel) xmlJTree.getModel()).setRoot(base);
        }

        public void characters(char[] ch, int start, int length) throws SAXException {

            String s = new String(ch, start, length).trim();
            if (!s.equals("")) {
                DefaultMutableTreeNode current = new DefaultMutableTreeNode(s);
                base.add(current);

            }
        }

        public void endElement(String namespaceURI, String localName, String qName) throws SAXException {

            base = (DefaultMutableTreeNode) base.getParent();
        }

        @Override
        public void endDocument() throws SAXException {
            // Refresh JTree
            ((DefaultTreeModel) xmlJTree.getModel()).reload();
            expandAll(xmlJTree);
        }

        public void expandAll(JTree tree) {
            int row = 0;
            while (row < tree.getRowCount()) {
                tree.expandRow(row);
                row++;
            }
        }

        public void xmlSetUp(File xmlFile) {
            try {
                SAXParserFactory fact = SAXParserFactory.newInstance();
                SAXParser parser = fact.newSAXParser();
                parser.parse(xmlFile, this);

            } catch (Exception e) {
            }
        }

        public void createUI() {
            treeModel = new DefaultTreeModel(base);
            xmlJTree = new JTree(treeModel);

            xmlSetUp(getFile_());
            xml_view_tabpane.addTab(getFile_().getPath(), xmlJTree);
            xml_view_tabpane.updateUI();
        }

        public void getSplitPane(String name, JComponent component) {
            xml_view_tabpane.addTab(name, component);
        }

        public void setFile_(File file_) {
            this.file_ = file_;
        }

        public File getFile_() {
            return file_;
        }
    }
}
