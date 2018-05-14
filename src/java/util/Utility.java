/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Component;
import java.awt.Container;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.swing.JMenu;

/**
 *
 * @author Admin
 */
public class Utility {

    public static ResourceBundle getBoundle(String fileName, Locale loc) {
        return ResourceBundle.getBundle(fileName, loc);
    }

    public static Properties getProperties(String fileName) {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = Utility.class.getClassLoader().getResourceAsStream(fileName);
            if (input == null) {
                System.out.println("Sorry, unable to find " + fileName);
            } else {
                System.out.println("Properties loaded filename " + fileName);
                prop.load(input);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop;
    }

}
