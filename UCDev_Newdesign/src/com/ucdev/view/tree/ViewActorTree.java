package com.ucdev.view.tree;

import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.File;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author filmz
 */
public class ViewActorTree extends DefaultHandler {

    private JTree xmlJTree;
    DefaultTreeModel treeModel;
    int lineCounter;
    DefaultMutableTreeNode base = new DefaultMutableTreeNode("Usecases");
    static ViewActorTree treeViewer = null;

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
        base = new DefaultMutableTreeNode("Usecase");
        ((DefaultTreeModel) xmlJTree.getModel()).setRoot(base);
    }

    public void characters(char[] ch, int start, int length) throws SAXException {

        String s = new String(ch, start, length).trim();
        if (!s.equals("")) {
            DefaultMutableTreeNode current = new DefaultMutableTreeNode("Usecase name : " + s);
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
}
