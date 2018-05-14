/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgbean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import pojos.Exercise;

/**
 *
 * @author Admin
 */
@ManagedBean
@SessionScoped
public class ManageExercisesList {

    private List<Exercise> exercises;
    private int sumExercises = 0;
    private int counter = -1;
    private Exercise relevantExercise;
    private boolean cannext;

    public ManageExercisesList() {
        relevantExercise = new Exercise();
        exercises = new ArrayList<>();
    }

    public boolean isnext() {
        boolean next = false;
        System.out.println("ELOTTE ISNEXT: " + exercises.size() + "  COUNTER: " + counter);
        if (exercises.size() - 1 > counter) {
            next = true;
            counterPlus();
        }
        System.out.println("UTANA ISNEXT: " + exercises.size() + "  COUNTER: " + counter);
        return next;
    }

    public boolean isback() {
        System.out.println("ELOTTE ISNEXT: " + exercises.size() + "  COUNTER: " + counter);
        boolean next = false;

        if (0 <= counter) {
            next = true;
            counterMinus();
        }
        System.out.println("UTANA ISNEXT: " + exercises.size() + "  COUNTER: " + counter);
        return next;
    }

    public void counterPlus() {
        System.out.println("Plus:" + counter + "-" + exercises.size() + "");
        if (counter < exercises.size() - 1) {
            counter++;
        }
    }

    public void next() {
        System.out.println("ELSŐ");
        counterPlus();
        System.out.println("MÁSODIK");
        relevantExercise = exercises.get(counter);
        System.out.println("HARMADIK");
        isnext();
        System.out.println("Next:" + relevantExercise.getNumber()
                + "_"
                + relevantExercise.getAnswer()
                + " Counter: "
                + counter
                + " Sum: "
                + sumExercises
                + " Cannext: "
                + cannext
                + "");
    }

    public void actionOK() {
        relevantExercise = exercises.get(counter);
        System.out.println("OK :" + relevantExercise.getNumber() + "_" + relevantExercise.getAnswer() + " Counter: " + counter);
    }

    public void counterMinus() {
        if (counter > 0) {
            counter--;
        }
    }

    public int niki(boolean mimi) {
        if (mimi) {
            counterPlus();
            System.out.println("Süsü");

        } else {
            System.out.println("a sárkány");
            if (exercises.size() > 0) {
                counterMinus();

            }
        }
        relevantExercise = exercises.get(counter);
        return counter;
    }

    public void back() {
        counterMinus();
        if (!exercises.isEmpty()) {
            relevantExercise = exercises.get(counter);
        }
        isnext();
        System.out.println("Back:" + relevantExercise.getNumber()
                + "_"
                + relevantExercise.getAnswer()
                + " Counter: "
                + counter
                + " Sum: "
                + sumExercises
                + " Cannext: "
                + cannext
                + "");
        addMessage(counter + "BACK EZAZ");
    }

    public void add(Exercise exercise) {
        exercises.add(exercise);
        sumExercises++;
        System.out.println("Az add ok:" + sumExercises + "");
    }

    public void delete(Exercise feladat) {
        exercises.remove(feladat);
        sumExercises--;
    }

    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public Exercise getExercise(int i) {
        return exercises.get(i);
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public int getSumExercises() {
        return sumExercises;
    }

    public void setSumExercises(int sumExercises) {
        this.sumExercises = sumExercises;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public Exercise getRelevantExercise() {
        return relevantExercise;
    }

    public void setRelevantExercise(Exercise relevantExercise) {
        this.relevantExercise = relevantExercise;
    }

    public boolean isCannext() {
        return cannext;
    }

    public void setCannext(boolean cannext) {
        this.cannext = cannext;
    }

}
