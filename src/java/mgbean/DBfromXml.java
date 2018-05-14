/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgbean;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.swing.JFileChooser;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import pojos.Exercise;

/**
 *
 * @author Admin
 */
@ManagedBean
@SessionScoped
public class DBfromXml {

    private String xmlpath = ""; //T:\\Java\\XMLfeldolgozas\\XmlFiles";
    private File xmlFile = null;

    public DBfromXml() {
    }

    public void saveListToDB() {
        System.out.println("NULLADIK");
        List<Exercise> exercises = doListFromXml();
        System.out.println("ELSŐ");
        Session session = null;
        try {
            session = hibernate.HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            System.out.println("MÁSODIK");
            for (Exercise exercise : exercises) {
                try {
                    session.save(exercise);
                } catch (NonUniqueObjectException nuoe) {
                    System.out.println("HIBA VAN A KRÉTA KÖRÜL: " + exercise.toString());
                }
            }
            System.out.println("HARMADIK");
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("GÁZ VAN: " + e.toString());
        } finally {
            session.clear();
            session.disconnect();
            session.close();
            hibernate.HibernateUtil.getSessionFactory().close();
        }

    }

    public void chooseXml() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File(xmlpath));
        chooser.showOpenDialog(null);
        xmlFile = chooser.getSelectedFile();
    }

    public List<Exercise> doListFromXml() {
        SAXBuilder builder = new SAXBuilder();
        File f = new File(xmlFile.getAbsolutePath());
        List<Exercise> exerciselist = new ArrayList<>();
        Document document;
        try {
            document = builder.build(f);
            Element root = document.getRootElement();
            List<Element> exercises = root.getChildren();
            for (Element exercise : exercises) {
                String age = exercise.getChildText("AGE");
                int year = Integer.parseInt(exercise.getChildText("YEAR"));
                String language = exercise.getChildText("LANGUAGE");
                int number = Integer.parseInt(exercise.getChildText("NUMBER"));
                String answer = exercise.getChildText("ANSWER");
                String url = exercise.getChildText("URL");
                Exercise newExercise = new Exercise(age, year, language, number, answer, url);
                exerciselist.add(newExercise);
            }

        } catch (JDOMException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return exerciselist;
    }

    public String getXmlpath() {
        return xmlpath;
    }

    public void setXmlpath(String xmlpath) {
        this.xmlpath = xmlpath;
    }

    public File getXmlFile() {
        return xmlFile;
    }

    public void setXmlFile(File xmlFile) {
        this.xmlFile = xmlFile;
    }

}
