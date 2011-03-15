/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.trems.estagioaprendizado.numberquiz.controller;

import br.jus.trems.estagioaprendizado.numberquiz.daoimpl.ProblemDaoImpl;
import br.jus.trems.estagioaprendizado.numberquiz.entities.Problem;
import br.jus.trems.estagioaprendizado.numberquiz.entities.User;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 *
 *
 * @author heverson.vasconcelos
 */
@ManagedBean(name = "quizBean")
@SessionScoped
public class QuizBean implements Serializable {

    private User user;
    private List<Problem> problems;
    private int currentIndex;
    private int score;
    private ProblemDaoImpl problemDaoImpl = new ProblemDaoImpl();

    public QuizBean() {
        problems = problemDaoImpl.list();
        Collections.shuffle(problems);
    }

    public void setProblems(List<Problem> problems) {
        this.problems = problems;
        currentIndex = 0;
        score = 0;
    }

    public int getScore() {
        return score;
    }

    public Problem getCurrent() {
        return problems.get(currentIndex);
    }

    public String getAnswer() {
        return "";
    }

    public void setAnswer(String newValue) {
        try {
            int answer = Integer.parseInt(newValue.trim());
            if (getCurrent().getSolution() == answer) {
                score++;
            }
            currentIndex = (currentIndex + 1) % problems.size();
        } catch (NumberFormatException ex) {
        }
    }

    /**
     * M�todo para limpar o score, a sequencia de quest�es e embaralhar as quest�es
     */
    public void resetQuiz() {
        score = 0;
        currentIndex = 0;
        Collections.shuffle(problems);
    }

    /**
     * M�todo para armazenar o score atual no banco. Este score ser� atrelado
     * a um jogo
     */
    public void saveScore() {
        /*
         * TODO: devera ser implementado o
         * m�todo para salvar o score atual
         */
    }

    /**
     * M�todo para iniciar um novo jogo. Inicialmente ser� armazenado o score atual
     * e ent�o ser�o 
     */
    public String newGame() {
        resetQuiz();

        return "numberquiz";
    }

    public String showScore() {
        saveScore();

        return "stats";
    }

    public String quit() {
        resetQuiz();
        /*
         * TODO: devera ser implementado a finalizacao da sessao
         */

        return "index";
    }
}
