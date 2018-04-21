package com.ucdev.db.control;

import com.ucdev.save.control.FileController;
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

    FileController instance = FileController.getInstance();

    private static final String dbURL = "jdbc:derby://localhost:1527/UCDev;create=true;user=root;password=root";

    // jdbc Connection
    public static Connection conn = null;
    private static Statement stmt, stmt_flow, stmt_alt, stmt_ext, stmt_uc, stmt_ac, stmt_rel;

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

            StreamResult result = new StreamResult(instance.readFolder() + "\\actor.xml");
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

            StreamResult result = new StreamResult(instance.readFolder() + "\\usecase.xml");
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
            ResultSet results = stmt.executeQuery("select * from requirement");

            // root element
            Element rootElement = doc.createElement("requirements");
            doc.appendChild(rootElement);

            while (results.next()) {

                String req_id = results.getString(1);
                String req_desc = results.getString(2);

                Element req_element = doc.createElement("requirement");
                rootElement.appendChild(req_element);

                Attr attr_id = doc.createAttribute("id");
                attr_id.setValue(req_id);
                req_element.setAttributeNode(attr_id);

                Attr attr_desc = doc.createAttribute("description");
                attr_desc.setValue(req_desc);
                req_element.setAttributeNode(attr_desc);

                //query from database
                stmt_uc = conn.createStatement();
                stmt_ac = conn.createStatement();
                stmt_rel = conn.createStatement();

                ResultSet results_uc = stmt_uc.executeQuery("SELECT usecase.UC_ID, usecase.UC_NAME\n"
                        + "FROM (requirement INNER JOIN usecase ON requirement.REQ_ID = usecase.REQ_ID)\n"
                        + "WHERE usecase.REQ_ID='" + req_id + "'");

                ResultSet results_ac = stmt_ac.executeQuery("SELECT actor.AC_ID, actor.AC_NAME\n"
                        + "FROM (requirement INNER JOIN actor ON requirement.REQ_ID = actor.REQ_ID)\n"
                        + "WHERE actor.REQ_ID='" + req_id + "'");

                ResultSet results_rel = stmt_rel.executeQuery("SELECT actor.AC_ID, actor.AC_NAME, usecase.UC_ID,usecase.UC_NAME "
                        + "FROM ((association "
                        + "INNER JOIN actor ON association.AC_OBJ = actor.AC_OBJ)"
                        + "INNER JOIN usecase ON association.UC_OBJ = usecase.UC_OBJ)"
                        + "where actor.REQ_ID='" + req_id + "'");

                stmt_ext = conn.createStatement();
                ResultSet results_ext = stmt_ext.executeQuery("SELECT usecase.UC_ID,usecase.UC_NAME,extends.UC_TARGET "
                        + "FROM (extends "
                        + "INNER JOIN usecase ON extends.UC_OBJ = usecase.UC_OBJ)"
                        + "where usecase.REQ_ID='" + req_id + "'");

                Statement stmt_inc = conn.createStatement();
                ResultSet results_inc = stmt_inc.executeQuery("SELECT usecase.UC_ID,usecase.UC_NAME,include.UC_TARGET "
                        + "FROM (include "
                        + "INNER JOIN usecase ON include.UC_OBJ = usecase.UC_OBJ)"
                        + "where usecase.REQ_ID='" + req_id + "'");

                Statement stmt_inh = conn.createStatement();
                ResultSet results_inh = stmt_inh.executeQuery("SELECT usecase.UC_ID,usecase.UC_NAME,inherit.UC_TARGET "
                        + "FROM (inherit "
                        + "INNER JOIN usecase ON inherit.UC_OBJ = usecase.UC_OBJ)"
                        + "where usecase.REQ_ID='" + req_id + "'");

                Element actor = doc.createElement("actor");
                req_element.appendChild(actor);

                while (results_ac.next()) {
                    String acid = results_ac.getString(1);
                    String acname = results_ac.getString(2);

                    Element ac = doc.createElement("at");
                    ac.appendChild(doc.createTextNode(acname));
                    actor.appendChild(ac);

                    Attr attr_acid = doc.createAttribute("actor_id");
                    attr_acid.setValue(acid);
                    ac.setAttributeNode(attr_acid);
                }
                results_ac.close();
                stmt_ac.close();

                Element usecase = doc.createElement("usecase");
                req_element.appendChild(usecase);

                while (results_uc.next()) {

                    String ucid = results_uc.getString(1);
                    String ucname = results_uc.getString(2);

                    Element uc = doc.createElement("uc");
                    uc.appendChild(doc.createTextNode(ucname));
                    usecase.appendChild(uc);

                    Attr attr_ucid = doc.createAttribute("usecase_id");
                    attr_ucid.setValue(ucid);
                    uc.setAttributeNode(attr_ucid);
                }
                results_uc.close();
                stmt_uc.close();

                Element relations = doc.createElement("relations");
                req_element.appendChild(relations);

                while (results_rel.next()) {
                    String acname = results_rel.getString(2);
                    String ucname = results_rel.getString(4);

                    Element relation = doc.createElement("relation");
                    relations.appendChild(relation);

                    Element rel_type = doc.createElement("rel-type");
                    rel_type.appendChild(doc.createTextNode("association"));
                    relation.appendChild(rel_type);

                    Element rel1_id = doc.createElement("rel1-id");
                    rel1_id.appendChild(doc.createTextNode(acname));
                    relation.appendChild(rel1_id);

                    Element rel2_id = doc.createElement("rel2-id");
                    rel2_id.appendChild(doc.createTextNode(ucname));
                    relation.appendChild(rel2_id);
                }
                results_rel.close();
                stmt_rel.close();

                while (results_ext.next()) {
                    String ucname = results_ext.getString(2);
                    String ext_target = results_ext.getString(3);

                    Element relation = doc.createElement("relation");
                    relations.appendChild(relation);

                    Element rel_type = doc.createElement("rel-type");
                    rel_type.appendChild(doc.createTextNode("extends"));
                    relation.appendChild(rel_type);

                    Element rel1_id = doc.createElement("rel1-id");
                    rel1_id.appendChild(doc.createTextNode(ucname));
                    relation.appendChild(rel1_id);

                    Statement stmt_ext_uc = conn.createStatement();
                    ResultSet results_ext_uc = stmt_ext_uc.executeQuery("select uc_name from usecase where uc_obj='" + ext_target + "'");

                    while (results_ext_uc.next()) {

                        String target = results_ext_uc.getString(1);

                        Element rel2_id = doc.createElement("rel2-id");
                        rel2_id.appendChild(doc.createTextNode(target));
                        relation.appendChild(rel2_id);
                    }

                }
                results_ext.close();
                stmt_ext.close();

                while (results_inc.next()) {
                    String ucname = results_inc.getString(2);
                    String inc_target = results_inc.getString(3);
                    
                    Element relation = doc.createElement("relation");
                    relations.appendChild(relation);

                    Element rel_type = doc.createElement("rel-type");
                    rel_type.appendChild(doc.createTextNode("include"));
                    relation.appendChild(rel_type);

                    Element rel1_id = doc.createElement("rel1-id");
                    rel1_id.appendChild(doc.createTextNode(ucname));
                    relation.appendChild(rel1_id);

                    Statement stmt_inc_uc = conn.createStatement();
                    ResultSet results_inc_uc = stmt_inc_uc.executeQuery("select uc_name from usecase where uc_obj='" + inc_target + "'");

                    while (results_inc_uc.next()) {

                        String target = results_inc_uc.getString(1);

                        Element rel2_id = doc.createElement("rel2-id");
                        rel2_id.appendChild(doc.createTextNode(target));
                        relation.appendChild(rel2_id);
                    }
                }
                results_inc.close();
                stmt_inc.close();

                while (results_inh.next()) {
                    String ucname = results_inh.getString(2);
                    String inh_target = results_inh.getString(3);
                    
                    Element relation = doc.createElement("relation");
                    relations.appendChild(relation);

                    Element rel_type = doc.createElement("rel-type");
                    rel_type.appendChild(doc.createTextNode("inherit"));
                    relation.appendChild(rel_type);

                    Element rel1_id = doc.createElement("rel1-id");
                    rel1_id.appendChild(doc.createTextNode(ucname));
                    relation.appendChild(rel1_id);

                    Statement stmt_inh_uc = conn.createStatement();
                    ResultSet results_inh_uc = stmt_inh_uc.executeQuery("select uc_name from usecase where uc_obj='" + inh_target + "'");

                    while (results_inh_uc.next()) {

                        String target = results_inh_uc.getString(1);

                        Element rel2_id = doc.createElement("rel2-id");
                        rel2_id.appendChild(doc.createTextNode(target));
                        relation.appendChild(rel2_id);
                    }
                }
                results_inh.close();
                stmt_ext.close();
            }

            results.close();
            stmt.close();

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);

            //StreamResult result = new StreamResult(instance.readFolder() + "\\requirement.xml");
            //transformer.transform(source, result);
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

    public void queryDatadict() {
        try {
            //save xml file
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            //query from database
            Statement stmt_in = conn.createStatement();
            ResultSet results = stmt_in.executeQuery("select uc_id , uc_name from inputdata");

            // root element
            Element rootElement = doc.createElement("datadictionnary");
            doc.appendChild(rootElement);

            while (results.next()) {

                String ucid = results.getString(1);
                String ucname = results.getString(2);

                Element usecase = doc.createElement("Usecase");
                rootElement.appendChild(usecase);

                Attr attr_ucid = doc.createAttribute("id");
                attr_ucid.setValue(ucid);
                usecase.setAttributeNode(attr_ucid);

                Attr attr_ucname = doc.createAttribute("name");
                attr_ucname.setValue(ucname);
                usecase.setAttributeNode(attr_ucname);

                //query from database
                Statement stmt_input = conn.createStatement();
                ResultSet results_input = stmt_input.executeQuery("select * from inputdata where uc_id='" + ucid + "'");

                while (results_input.next()) {

                    String var = results_input.getString(1);
                    String type = results_input.getString(2);
                    String dataset = results_input.getString(3);
                    String id = results_input.getString(4);
                    String max = results_input.getString(5);
                    String min = results_input.getString(6);
                    String value = results_input.getString(7);

                    Element input = doc.createElement("Input");
                    usecase.appendChild(input);

                    Element varname = doc.createElement("Varname");
                    varname.appendChild(doc.createTextNode(var));
                    input.appendChild(varname);

                    Element Type = doc.createElement("Type");
                    Type.appendChild(doc.createTextNode(type));
                    input.appendChild(Type);

                    Element Dataset = doc.createElement("Dataset");
                    Dataset.appendChild(doc.createTextNode(dataset));
                    input.appendChild(Dataset);

                    if ("Range".equals(type)) {
                        Element Condition = doc.createElement("Condition");
                        input.appendChild(Condition);

                        Attr attr_id = doc.createAttribute("id");
                        attr_id.setValue(id);
                        Condition.setAttributeNode(attr_id);

                        Attr attr_min = doc.createAttribute("min");
                        attr_min.setValue(min);
                        Condition.setAttributeNode(attr_min);

                        Attr attr_max = doc.createAttribute("max");
                        attr_max.setValue(max);
                        Condition.setAttributeNode(attr_max);
                    } else {
                        Element Condition = doc.createElement("Condition");
                        input.appendChild(Condition);

                        Attr attr_id = doc.createAttribute("id");
                        attr_id.setValue(id);
                        Condition.setAttributeNode(attr_id);

                        Attr attr_value = doc.createAttribute("value");
                        attr_value.setValue(value);
                        Condition.setAttributeNode(attr_value);
                    }

                }
                results_input.close();
                stmt_input.close();

                //query from database
                Statement stmt_output = conn.createStatement();
                ResultSet results_output = stmt_output.executeQuery("select * from outputdata where uc_id='" + ucid + "'");

                while (results_output.next()) {
                    String var = results_output.getString(1);
                    String type = results_output.getString(2);
                    String dataset = results_output.getString(3);
                    String value = results_output.getString(4);
                    String id = results_output.getString(7);

                    Element output = doc.createElement("Output");
                    usecase.appendChild(output);

                    Element varname = doc.createElement("Varname");
                    varname.appendChild(doc.createTextNode(var));
                    output.appendChild(varname);

                    Element Type = doc.createElement("Type");
                    Type.appendChild(doc.createTextNode(type));
                    output.appendChild(Type);

                    Element Dataset = doc.createElement("Dataset");
                    Dataset.appendChild(doc.createTextNode(dataset));
                    output.appendChild(Dataset);

                    Element Condition = doc.createElement("Action");
                    output.appendChild(Condition);

                    Attr attr_id = doc.createAttribute("id");
                    attr_id.setValue(id);
                    Condition.setAttributeNode(attr_id);

                    Attr attr_value = doc.createAttribute("value");
                    attr_value.setValue(value);
                    Condition.setAttributeNode(attr_value);
                }
                results_output.close();
                stmt_output.close();
            }

            results.close();
            stmt_in.close();

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);

            //StreamResult result = new StreamResult(instance.readFolder() + "\\datadict.xml");
            //transformer.transform(source, result);
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

    public void insertActorProperties(String ac_id, String ac_name, String ac_desc, String ac_type, String ac_obj, String req_id) {
        try {
            stmt = conn.createStatement();
            stmt.execute("insert into actor values('" + ac_id + "','" + ac_name + "','" + ac_desc + "','" + ac_type + "','" + ac_obj + "','" + req_id + "')");
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
        }
    }

    public void insertExtendsRel(Object obj_start, Object obj_target) {
        try {
            stmt = conn.createStatement();
            stmt.execute("insert into extends values('" + obj_start + "','" + obj_target + "')");
            stmt.close();
        } catch (SQLException ex) {
        }
    }

    public void insertIncludeRel(Object obj_start, Object obj_target) {
        try {
            stmt = conn.createStatement();
            stmt.execute("insert into include values('" + obj_start + "','" + obj_target + "')");
            stmt.close();
        } catch (SQLException ex) {
        }
    }

    public void insertInheritRel(Object obj_start, Object obj_target) {
        try {
            stmt = conn.createStatement();
            stmt.execute("insert into inherit values('" + obj_start + "','" + obj_target + "')");
            stmt.close();
        } catch (SQLException ex) {
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
            stmt.execute("DELETE FROM requirement");
            stmt.execute("DELETE FROM inputdata");
            stmt.execute("DELETE FROM outputdata");
            stmt.close();
        } catch (SQLException ex) {
        } catch (Exception ex) {
        }
    }

    public static void main(String[] args) {
        new DBControl().getConnectDB();
        //new DBControl().queryUsecaseProperties();
        //new DBControl().deleteDataFromDatabase();
        //new DBControl().queryRelation();
        //new DBControl().queryRequirement();
        new DBControl().queryDatadict();
    }
}
