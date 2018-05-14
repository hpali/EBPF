/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Admin
 */
@ManagedBean
@SessionScoped
public class Answer implements Serializable {

    private String answer;
    private List<String> answers;

    public Answer() {
        answers = new ArrayList<>();
        answers.add("A");
        answers.add("B");
        answers.add("C");
        answers.add("D");
        answers.add("E");
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

}
