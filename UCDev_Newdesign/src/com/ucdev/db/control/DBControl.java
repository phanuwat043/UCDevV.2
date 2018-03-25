package com.ucdev.db.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author filmz
 */
public class DBControl {

    private static final String dbURL = "jdbc:derby://localhost:1527/UCDev;create=true;user=root;password=root";

    // jdbc Connection
    public static Connection conn = null;
    private static Statement stmt, stmt_flow, stmt_alt, stmt_ext, stmt_uc = null;

    public void getConnectDB() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            //Get a connection
            conn = DriverManager.getConnection(dbURL);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void queryActorProperties() {
        try {
            //save xml file
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            //query from database
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from actor");
            ResultSetMetaData rsmd = results.getMetaData();

            // root element
            Element rootElement = doc.createElement("actors");
            doc.appendChild(rootElement);
            while (results.next()) {
                //id
                String id = results.getString(1);
                //name
                String name = results.getString(2);
                //description
                String desc = results.getString(3);
                //type
                String type = results.getString(4);

                // actor name element
                Element id_element = doc.createElement("actor");
                rootElement.appendChild(id_element);

                // setting attribute to element
                Attr attr = doc.createAttribute("actor_id");
                attr.setValue(id);
                id_element.setAttributeNode(attr);

                // actor name element
                Element act_name = doc.createElement("actor_name");
                act_name.appendChild(doc.createTextNode(name));
                id_element.appendChild(act_name);

                // actor desc element
                Element act_desc = doc.createElement("actor_description");
                act_desc.appendChild(doc.createTextNode(desc));
                id_element.appendChild(act_desc);

                // actor name element
                Element act_type = doc.createElement("actor_type");
                act_type.appendChild(doc.createTextNode(type));
                id_element.appendChild(act_type);
            }
            results.close();
            stmt.close();

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult("C:\\UCDev\\actor.xml");
            transformer.transform(source, result);
            // Output to console for testing
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);

        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DBControl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(DBControl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(DBControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void queryUsecaseProperties() {
        try {
            //save xml file
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            //query from database
            stmt = conn.createStatement();
            stmt_flow = conn.createStatement();
            stmt_alt = conn.createStatement();
            stmt_ext = conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from usecase");
            ResultSet results_flow = null;
            ResultSet results_alt = null;
            ResultSet results_ext = null;

            //ResultSetMetaData rsmd = results.getMetaData();
            // root element
            Element rootElement = doc.createElement("usecases");
            doc.appendChild(rootElement);
            while (results.next()) {
                String uc_id = results.getString(1);
                String uc_name = results.getString(3);
                String uc_obj = results.getString(8);
                String goal = results.getString(4);
                String pre = results.getString(5);
                String post = results.getString(6);
                String pri = results.getString(7);

                // usecase id element
                Element id_element = doc.createElement("usecase");
                rootElement.appendChild(id_element);

                // setting attribute to element
                Attr attr = doc.createAttribute("usecase_id");
                attr.setValue(uc_id);
                id_element.setAttributeNode(attr);

                // usecase name element
                Element act_name = doc.createElement("usecase_name");
                act_name.appendChild(doc.createTextNode(uc_name));
                id_element.appendChild(act_name);

                // usecase goal element
                Element act_desc = doc.createElement("usecase_goal");
                act_desc.appendChild(doc.createTextNode(goal));
                id_element.appendChild(act_desc);

                // usecase pre element
                Element act_type = doc.createElement("usecase_pre");
                act_type.appendChild(doc.createTextNode(pre));
                id_element.appendChild(act_type);

                // usecase post element
                Element uc_post = doc.createElement("usecase_post");
                uc_post.appendChild(doc.createTextNode(post));
                id_element.appendChild(uc_post);

                // usecase priority element
                Element uc_pri = doc.createElement("usecase_pri");
                uc_pri.appendChild(doc.createTextNode(pri));
                id_element.appendChild(uc_pri);

                // usecase obj element
                Element uc_obj_ = doc.createElement("usecase_object");
                uc_obj_.appendChild(doc.createTextNode(uc_obj));
                id_element.appendChild(uc_obj_);

                // flow of event element
                Element flow = doc.createElement("flow_of_event");
                id_element.appendChild(flow);

//flow of event data
                results_flow = stmt_flow.executeQuery("select * from flow_event where uc_id='" + uc_id + "'");

                while (results_flow.next()) {
                    String flow_id = results_flow.getString(2);
                    String flow_detail = results_flow.getString(3);

                    // flow of event element
                    Element flow_node = doc.createElement("value");
                    flow_node.appendChild(doc.createTextNode(flow_detail));
                    flow.appendChild(flow_node);

                    // node of flow event to element
                    Attr attr_flow = doc.createAttribute("flow_id");
                    attr_flow.setValue(flow_id);
                    flow_node.setAttributeNode(attr_flow);

                }

                // flow of event element
                Element alt = doc.createElement("alternative_flow");
                id_element.appendChild(alt);
//alternative flow data
                results_alt = stmt_alt.executeQuery("select * from alternative where uc_id='" + uc_id + "'");
                while (results_alt.next()) {
                    String alt_id = results_alt.getString(5);
                    String detail = results_alt.getString(1);
                    String out = results_alt.getString(4);
                    String in = results_alt.getString(3);

                    // alternative event element
                    Element alt_node = doc.createElement("value");
                    alt_node.appendChild(doc.createTextNode(detail));
                    alt.appendChild(alt_node);

                    // node of flow event to element
                    Attr attr_alt = doc.createAttribute("alt_id");
                    attr_alt.setValue(alt_id);
                    alt_node.setAttributeNode(attr_alt);

                    // alternative event element
                    Element ref_node = doc.createElement("ref");
                    alt.appendChild(ref_node);

                    // node of flow event to element
                    Attr attr_node = doc.createAttribute("ref_id");
                    attr_node.setValue(alt_id);
                    ref_node.setAttributeNode(attr_node);

                    // alternative event element
                    Element ref_out = doc.createElement("flow-ref_out");
                    ref_node.appendChild(ref_out);

                    // node of flow event to element
                    Attr attr_out = doc.createAttribute("at-step");
                    attr_out.setValue(out);
                    ref_out.setAttributeNode(attr_out);

                    // alternative event element
                    Element ref_in = doc.createElement("flow-ref_in");
                    ref_node.appendChild(ref_in);

                    // node of flow event to element
                    Attr attr_in = doc.createAttribute("at-step");
                    attr_in.setValue(in);
                    ref_in.setAttributeNode(attr_in);
                }

//exception flow
                // flow of event element
                Element ext = doc.createElement("exception_flow");
                id_element.appendChild(ext);

                results_ext = stmt_ext.executeQuery("select * from exception_flow where uc_id='" + uc_id + "'");
                while (results_ext.next()) {
                    String ex_id = results_ext.getString(4);
                    String detail = results_ext.getString(1);
                    String ex_flow = results_ext.getString(3);

                    // exception flow element
                    Element ext_node = doc.createElement("value");
                    ext_node.appendChild(doc.createTextNode(detail));
                    ext.appendChild(ext_node);

                    // node of flow event to element
                    Attr attr_ext = doc.createAttribute("ext_id");
                    attr_ext.setValue(ex_id);
                    ext_node.setAttributeNode(attr_ext);

                    // alternative event element
                    Element ref_node = doc.createElement("ref");
                    ext.appendChild(ref_node);

                    // node of exception to element
                    Attr attr_node = doc.createAttribute("ref_id");
                    attr_node.setValue(ex_id);
                    ref_node.setAttributeNode(attr_node);

                    // alternative event element
                    Element ref = doc.createElement("flow-ref");
                    ref_node.appendChild(ref);

                    // node of flow event to element
                    Attr attr_flow = doc.createAttribute("at-step");
                    attr_flow.setValue(ex_flow);
                    ref.setAttributeNode(attr_flow);
                }
            }
            results_alt.close();
            stmt_alt.close();

            results_flow.close();
            stmt_flow.close();

            results.close();
            stmt.close();

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult("C:\\UCDev\\usecase.xml");
            transformer.transform(source, result);
            // Output to console for testing
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);

        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DBControl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(DBControl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(DBControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void queryRequirement() {
        try {
            //save xml file
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            //query from database
            stmt = conn.createStatement();
            stmt_uc = conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from actor");
            ResultSet results_uc = stmt_uc.executeQuery("select * from usecase");

            // root element
            Element rootElement = doc.createElement("requirements");
            doc.appendChild(rootElement);

            Element req_element = doc.createElement("requirement");
            rootElement.appendChild(req_element);

            Attr attr_req = doc.createAttribute("id");
            attr_req.setValue("req01");
            req_element.setAttributeNode(attr_req);

            Element ac_element = doc.createElement("actor");
            req_element.appendChild(ac_element);
            while (results.next()) {
                //id
                String id = results.getString(1);
                //name
                String name = results.getString(2);

                // actor name element
                Element id_element = doc.createElement("at");
                id_element.appendChild(doc.createTextNode(name));
                ac_element.appendChild(id_element);

                // setting attribute to element
                Attr attr = doc.createAttribute("actor_id");
                attr.setValue(id);
                id_element.setAttributeNode(attr);

            }
            results.close();
            stmt.close();

            Element uc_element = doc.createElement("usecase");
            req_element.appendChild(uc_element);

            while (results_uc.next()) {
                //id
                String id = results_uc.getString(1);
                //name
                String name = results_uc.getString(3);

                // usecase id element
                Element id_element = doc.createElement("uc");
                id_element.appendChild(doc.createTextNode(name));
                uc_element.appendChild(id_element);

                // setting attribute to element
                Attr attr = doc.createAttribute("usecase_id");
                attr.setValue(id);
                id_element.setAttributeNode(attr);
            }
            stmt_uc.close();
            results_uc.close();

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult("C:\\UCDev\\requirement.xml");
            transformer.transform(source, result);
            // Output to console for testing
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);

        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DBControl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(DBControl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(DBControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//relation
    public void queryRelation() {
        try {
            //save xml file
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            //query from database
            Statement stmt_ac_id, stmt_uc_id, stmt_ex, stmt_in, stmt_inher;

            stmt = conn.createStatement();
            stmt_ac_id = conn.createStatement();
            stmt_uc_id = conn.createStatement();

            ResultSet result_ac = stmt_ac_id.executeQuery("select ac_id from actor");
            ResultSet result_uc = stmt_uc_id.executeQuery("select uc_obj from extends");
            ResultSet results = null;

            // root element
            Element rootElement = doc.createElement("relations");
            doc.appendChild(rootElement);

            while (result_ac.next()) {
                String ac_id = result_ac.getString(1);
                results = stmt.executeQuery("SELECT actor.AC_ID, actor.AC_NAME, usecase.UC_ID,usecase.UC_NAME "
                        + "FROM ((association "
                        + "INNER JOIN actor ON association.AC_OBJ = actor.AC_OBJ)"
                        + "INNER JOIN usecase ON association.UC_OBJ = usecase.UC_OBJ)"
                        + "where actor.AC_ID='" + ac_id + "'");

                while (results.next()) {
                    String ac_name = results.getString(2);
                    String uc_name = results.getString(4);

                    Element req_element = doc.createElement("relation");
                    rootElement.appendChild(req_element);

                    Element type_element = doc.createElement("rel-type");
                    type_element.appendChild(doc.createTextNode("association"));
                    req_element.appendChild(type_element);

                    Element ac_element = doc.createElement("rel1-id");
                    ac_element.appendChild(doc.createTextNode(ac_name));
                    req_element.appendChild(ac_element);

                    Element uc_element = doc.createElement("rel2-id");
                    uc_element.appendChild(doc.createTextNode(uc_name));
                    req_element.appendChild(uc_element);
                }
            }

            /*while (result_uc.next()) {
                String uc_obj = result_uc.getString(1);
                System.out.println("Test : "+uc_obj);
                result_uc = stmt_uc_id.executeQuery("SELECT usecase.UC_ID,usecase.UC_NAME "
                        + "FROM extends "
                        + "INNER JOIN usecase ON extends.UC_OBJ = usecase.UC_OBJ "
                        + "where usecase.UC_OBJ='" + uc_obj + "'");
                System.out.println(result_uc);
                for (int i = 0; i < result_uc.getRow(); i++) {
                    System.out.println(result_uc.getRow());
                }
                while (result_uc.next()) {
                    System.out.println(result_uc.getString(2));
                    String uc_name = result_uc.getString(2);

                    Element req_element = doc.createElement("relation");
                    rootElement.appendChild(req_element);

                    Element type_element = doc.createElement("rel-type");
                    type_element.appendChild(doc.createTextNode("extends"));
                    req_element.appendChild(type_element);

                    Element uc_element = doc.createElement("rel2-id");
                    uc_element.appendChild(doc.createTextNode(uc_name));
                    req_element.appendChild(uc_element);
                }
            }
            result_uc.close();
            stmt_uc_id.close();*/
            results.close();
            stmt.close();
            result_ac.close();
            stmt_ac_id.close();

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult("C:\\UCDev\\relation.xml");
            transformer.transform(source, result);
            // Output to console for testing
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DBControl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(DBControl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(DBControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List queryFlowOfEvent(String uc_id) {
        List list = new ArrayList();
        try {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select flow_id from flow_event where uc_id='" + uc_id + "'");
            ResultSetMetaData rsmd = results.getMetaData();

            while (results.next()) {
                list.add(results.getString(1));
            }
            results.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
        return list;
    }

    public void queryAlternativeByUcID(String uc_id) {
        try {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from alternative where uc_id='" + uc_id + "'");

            while (results.next()) {
                results.getString(1);
            }
            results.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }

    public void queryRelationship() {
        try {
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("select * from association");
            ResultSetMetaData rsmd = results.getMetaData();

            while (results.next()) {
                System.out.println(results.getString(1) + " : " + results.getString(2));
            }
            results.close();
            stmt.close();
        } catch (SQLException sqlExcept) {
            sqlExcept.printStackTrace();
        }
    }

    public void insertActorProperties(String ac_id, String ac_name, String ac_desc, String ac_type, String ac_obj) {
        try {
            stmt = conn.createStatement();
            stmt.execute("insert into actor values('" + ac_id + "','" + ac_name + "','" + ac_desc + "','" + ac_type + "','" + ac_obj + "')");
            stmt.close();
            if (stmt.isClosed()) {
                JOptionPane.showMessageDialog(null, "save data success!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void insertUsecaseProperties(String uc_id, String req_id, String uc_name, String uc_goal, String uc_pre, String uc_post, String uc_pri, String uc_obj) {
        try {
            stmt = conn.createStatement();
            stmt.execute("insert into usecase values('" + uc_id + "','"
                    + req_id + "','"
                    + uc_name + "','"
                    + uc_goal + "','"
                    + uc_pre + "','"
                    + uc_post + "','"
                    + uc_pri + "','"
                    + uc_obj + "')");
            stmt.close();
            if (stmt.isClosed()) {
                JOptionPane.showMessageDialog(null, "save data success!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void insertFlowOfEvent(String uc_id, Map map) {
        String data = null;
        String flow_id = null;
        try {
            stmt = conn.createStatement();
            for (int i = 0; i < map.keySet().size(); i++) {
                data = (String) map.get(i);
                flow_id = String.valueOf(i);
                stmt.execute("insert into flow_event values('" + uc_id + "','flow" + flow_id + "','" + data + "')");
            }
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void insertAssoRel(Object obj_ac, Object obj_uc) {
        try {
            String ac = String.valueOf(obj_ac);
            String uc = String.valueOf(obj_uc);

            stmt = conn.createStatement();
            stmt.execute("insert into association values('" + uc + "','" + ac + "')");
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void insertExtendsRel(List list) {
        try {
            stmt = conn.createStatement();
            for (int i = 0; i < list.size(); i++) {
                stmt.execute("insert into extends values('" + list.get(i) + "')");
            }
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void insertIncludeRel(List list) {
        try {
            stmt = conn.createStatement();
            for (int i = 0; i < list.size(); i++) {
                stmt.execute("insert into include values('" + list.get(i) + "')");
            }
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void insertInheritRel(List list) {
        try {
            stmt = conn.createStatement();
            for (int i = 0; i < list.size(); i++) {
                stmt.execute("insert into inherit values('" + list.get(i) + "')");
            }
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteDataFromDatabase() {
        try {
            stmt = conn.createStatement();
            stmt.execute("DELETE FROM actor");
            stmt.execute("DELETE FROM usecase");
            stmt.execute("DELETE FROM flow_event");
            stmt.execute("DELETE FROM alternative");
            stmt.execute("DELETE FROM exception_flow");
            stmt.execute("DELETE FROM association");
            stmt.execute("DELETE FROM extends");
            stmt.execute("DELETE FROM include");
            stmt.execute("DELETE FROM inherit");
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new DBControl().getConnectDB();
        //new DBControl().queryAlternativeByUcID("002");
        //new DBControl().queryUsecaseProperties();
        new DBControl().deleteDataFromDatabase();
        //new DBControl().queryRelation();
        //new DBControl().queryRequirement();
    }
}
