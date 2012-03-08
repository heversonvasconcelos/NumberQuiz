package sampleapp.numberquiz.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 * Classe entidade que representa um jogo realizado. � composto por um conjunto
 * de problemas (Problem) e por um usu�rio (User).
 *
 * Possui uma NamedQuery (Quiz.getTopScores) que ser� utilizada para consultar
 * todas as pontua��es dos jogos ordenadas de forma decrescente. O resultado
 * desta query ser� utilizada para mostar as melhores pontua��es.
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

    /**
     * M�todo para retornar uma representa��o dos dados do Quiz em modo texto
     *
     * @return String contendo os dados do quiz
     */
    @Override
    public String toString() {
        String string = "";

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
