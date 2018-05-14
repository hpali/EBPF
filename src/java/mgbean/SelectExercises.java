/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgbean;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.util.Pair;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.hibernate.Query;
import org.hibernate.Session;
import pojos.Exercise;
import util.Utility;

/**
 *
 * @author Admin
 */
@ManagedBean
@SessionScoped
public class SelectExercises {

    private List<Integer> years;
    private List<String> levels;
    private List<String> languages;
    private List<String> ages;
    private String language;
    private int point = 0;
    private String level;
    private Integer year;
    private String age;
    private List<Exercise> exercises;
    private ResourceBundle languageProp;
    private Properties systemConfig;
    private Locale mainLocale;

    public SelectExercises() {

        years = new ArrayList<>();
        levels = new ArrayList<>();
        languages = new ArrayList<>();
        ages = new ArrayList<>();
        exercises = new ArrayList<>();
        mainLocale = new Locale("EN", "EN");
        loadLanguage(mainLocale);

        for (int i = 1998; i < 2018; i++) {
            years.add(i);
        }

        levels.add(getLanguageProp().getString("3p"));
        levels.add(getLanguageProp().getString("4p"));
        levels.add(getLanguageProp().getString("5p"));

        languages.add(getLanguageProp().getString("en"));
        languages.add(getLanguageProp().getString("de"));
        languages.add(getLanguageProp().getString("hu"));

        ages.add(getLanguageProp().getString("felix"));
        ages.add(getLanguageProp().getString("ecolier"));
        ages.add(getLanguageProp().getString("benjamin"));
        ages.add(getLanguageProp().getString("kadett"));
        ages.add(getLanguageProp().getString("junior"));
        ages.add(getLanguageProp().getString("student"));

    }

    public void doQuery() {
      //  Pair<Integer, Integer> numberlimit = getPontIntervall(level);
        Integer min = getX(level);
        Integer max = getY(level);
        Session session = hibernate.HibernateUtil.getSessionFactory().openSession();
        if (session != null) {
            Query query = session.createQuery("FROM Exercise WHERE"
                    + " YEAR = :Year"
                    + " AND AGE = :Age"
                    + " AND LANGUAGE = :Language"
                    + " AND NUMBER > :Min"
                    + " AND NUMBER < :Max");
            query.setParameter("Year", year);
            query.setParameter("Age", age);
            query.setParameter("Language", language);
            query.setParameter("Min", min);
            query.setParameter("Max", max);
            exercises = query.list();
        }
    }

 /*   public Pair<Integer, Integer> getPontIntervall(String level) {
        Integer min = 0;
        Integer max = 33;

        Integer x = 0;
        Integer y = 33;
        if (getLanguageProp().getString("3p").equals(level)) {
            y = 10;
        } else if (getLanguageProp().getString("4p").equals(level)) {
            x = 11;
            y = 20;
        } else if (getLanguageProp().getString("5p").equals(level)) {
            x = 21;
        }

        Pair<Integer, Integer> pair = new Pair<>(x, y);
        return pair;
    }
*/
    public int getX(String level) {
        int x = 0;
        if (getLanguageProp().getString("4p").equals(level)) {
            x = 11;
        } else if (getLanguageProp().getString("5p").equals(level)) {
            x = 21;

        }
        return x;
    }

    public int getY(String level) {
        int y = 33;
        if (getLanguageProp().getString("3p").equals(level)) {
            y = 10;
        }else if (getLanguageProp().getString("4p").equals(level)) {
            y = 20;
        }
        return 0;
    }

    public List<Integer> getYears() {
        return years;
    }

    public void setYears(List<Integer> years) {
        this.years = years;
    }

    public List<String> getLevels() {
        return levels;
    }

    public void setLevels(List<String> levels) {
        this.levels = levels;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<String> getAges() {
        return ages;
    }

    public void setAges(List<String> ages) {
        this.ages = ages;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public ResourceBundle getLanguageProp() {
        return languageProp;
    }

    public void setLanguageProp(ResourceBundle languageProp) {
        this.languageProp = languageProp;
    }

    public Properties getSystemConfig() {
        return systemConfig;
    }

    public void setSystemConfig(Properties systemConfig) {
        this.systemConfig = systemConfig;
    }

    public Locale getMainLocale() {
        return mainLocale;
    }

    public void setMainLocale(Locale mainLocale) {
        this.mainLocale = mainLocale;
    }

    public void loadLanguage(Locale loc) {
        setLanguageProp(Utility.getBoundle("messages", loc));
    }

}
