/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "exercises")
@IdClass(ExercisePK.class)
public class Exercise implements Serializable {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column
//    private Long id;
    @Id
    @Column(name = "age")
    private String age;
    @Id
    @Column(name = "year")
    private int year;
    @Id
    @Column(name = "language")
    private String language;
    @Id
    @Column(name = "number")
    private int number;

    @Column(name = "answer")
    private String answer;
    @Column(name = "url")
    private String url;

    public Exercise() {

    }

    public Exercise(String Age, int Year, String language, int Number, String Answer, String Url) {
        this.age = Age;
        this.year = Year;
        this.language = language;
        this.number = Number;
        this.answer = Answer;
        this.url = Url;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String Age) {
        this.age = Age;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int Year) {
        this.year = Year;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String Language) {
        this.language = Language;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int Number) {
        this.number = Number;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String Answer) {
        this.answer = Answer;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String Url) {
        this.url = Url;
    }

    @Override
    public String toString() {
        return "Exercise{" + "age=" + age + ", year=" + year + ", language=" + language + ", number=" + number + ", answer=" + answer + ", url=" + url + '}';
    }

}
