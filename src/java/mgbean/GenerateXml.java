/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgbean;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.swing.JFileChooser;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author Admin
 */
@ManagedBean
@SessionScoped
public class GenerateXml {

    private String defaultPathToKanguru = null;//"T:\\Kanguru";
    private File XmlFile = null;
    private String UrlDir = "http:\\46.101.206.87\\";
    private String defaultUrl = "http://46.101.206.87";
    private String placeOfXml = null; //"T:\\Java\\XMLfeldolgozas\\XmlFiles";
    private ArrayList<String> filelist = new ArrayList<>();

    public GenerateXml() {
    }

    public void doXmlFileAbsPath() {
        Element exercises = new Element("exercises");
        Document doc = new Document(exercises);
        for (String fileabspath : filelist) {
            String url = setUrl(fileabspath, defaultUrl);
            // String url = defaultUrl + "\\" + filename;
            Element staff = new Element("exercise");
            String[] partsAbsPath = fileabspath.split("\\\\");
            String filename = partsAbsPath[partsAbsPath.length - 1];
            String[] parts = filename.split("_");
            if (parts.length > 4) {
                staff = getStaff(staff, parts);
                staff.addContent(new Element("URL").setText(url));
                doc.getRootElement().addContent(staff);
            } else {
                System.out.println("hoppa, itt egy rossz file");
            }

        }

        XMLOutputter xmlOutput = new XMLOutputter();
        // display nice nice
        xmlOutput.setFormat(Format.getPrettyFormat());
        try {
            xmlOutput.output(doc, new FileWriter(placeOfXml));
        } catch (IOException ex) {
            Logger.getLogger(GenerateXml.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String setUrl(String txt, String defaultUrl) {
        String url = txt.substring(txt.indexOf(":") + 2);
        defaultUrl += "\\" + url;
        return defaultUrl;
    }

    public void doXmlFile() {
        Element exercises = new Element("exercises");
        Document doc = new Document(exercises);
        for (String filename : filelist) {
            String url = defaultUrl + "\\" + filename;
            Element staff = new Element("exercise");
            String[] parts = filename.split("_");
            if (parts.length > 4) {
                staff = getStaff(staff, parts);
                staff.addContent(new Element("URL").setText(url));
                doc.getRootElement().addContent(staff);
            } else {
                System.out.println("hoppa, itt egy rossz file");
            }

        }

        XMLOutputter xmlOutput = new XMLOutputter();
        // display nice nice
        xmlOutput.setFormat(Format.getPrettyFormat());
        try {
            xmlOutput.output(doc, new FileWriter(placeOfXml));
        } catch (IOException ex) {
            Logger.getLogger(GenerateXml.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setExercisesPath() {
        JFileChooser chooser = new JFileChooser();
      //  chooser.setCurrentDirectory(new java.io.File(placeOfXml));
        chooser.setCurrentDirectory(null);
        chooser.showOpenDialog(null);
        XmlFile = chooser.getSelectedFile();
        placeOfXml = XmlFile.getAbsolutePath();
    }

    public void setExercises() {
        JFileChooser chooser = new JFileChooser();
       // chooser.setCurrentDirectory(new java.io.File(defaultPathToKanguru));
        chooser.setCurrentDirectory(null);
        chooser.setDialogTitle("Tuti file chooser");
        chooser.setMultiSelectionEnabled(true);
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.showOpenDialog(null);

        File[] selectedFiles = null;
        selectedFiles = chooser.getSelectedFile().listFiles();

        for (File file : selectedFiles) {
            if (file.isDirectory()) {
                findPicInDirRek(file);
            } else {
                if (file.getName().endsWith(".png")) {
                    filelist.add(file.getAbsolutePath());
                }
            }
        }
    }

    public void findPicInDirRek(File directory) { // rekurzivan keresse meg a megadott könyvtárban a összes file -t
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                findPicInDirRek(file);
            } else {
                if (file.getName().endsWith(".png")) {
                    filelist.add(file.getAbsolutePath());
                }
            }
        }
    }

    public Element getStaff(Element staff, String[] parts) {
        staff.addContent(new Element("AGE").setText(parts[0]));
        staff.addContent(new Element("YEAR").setText(parts[1]));
        staff.addContent(new Element("LANGUAGE").setText(parts[2]));
        staff.addContent(new Element("NUMBER").setText(parts[3]));
        staff.addContent(new Element("ANSWER").setText(parts[4]));
        return staff;
    }

    public ArrayList<String> getFilelist() {
        return filelist;
    }

    public void setFilelist(ArrayList<String> filelist) {
        this.filelist = filelist;
    }

    public String getUrlDir() {
        return UrlDir;
    }

    public void setUrlDir(String UrlDir) {
        this.UrlDir = UrlDir;
    }

    public String getPlaceOfXml() {
        return placeOfXml;
    }

    public void setPlaceOfXml(String placeOfXml) {
        this.placeOfXml = placeOfXml;
    }

}
