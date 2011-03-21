package br.jus.trems.estagioaprendizado.numberquiz.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Classe entidade (POJO - Plain Old Java Object) que representa o jogo em si.
 * � composto por um conjunto de problemas (entidade Problem) e por um
 * usu�rio (entidade User).
 *
 * @author heverson.vasconcelos
 */
@Entity
@Table(name = "TB_QUIZ")
@NamedQuery(name = "Quiz.getTopScores",
query = "SELECT q FROM Quiz AS q ORDER BY SCORE desc")
public class Quiz implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToMany
    private List<Problem> problems = new ArrayList<Problem>();
    @ManyToOne
    private User user = new User();
    @Column(nullable = false)
    private int score;


    /*
     *
     * GETTERS e SETTERS
     *
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Problem> getProblems() {
        return problems;
    }

    public void setProblems(ArrayList<Problem> problems) {
        this.problems = problems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        String string = new String();

        string += ("QuizID: " + getId() + "\n");
        string += ("User: " + getUser().getName() + "\n");
        string += ("Problems: " + "\n");
        for (Problem p : getProblems()) {
            string += ("\t" + p.toString() + "\n");
        }
        string += ("Score: " + getScore());

        return string;
    }
}
