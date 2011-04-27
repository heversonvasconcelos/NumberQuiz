package br.jus.trems.estagioaprendizado.numberquiz.controller;

import br.jus.trems.estagioaprendizado.numberquiz.daoimpl.ProblemDaoImpl;
import br.jus.trems.estagioaprendizado.numberquiz.daoimpl.QuizDaoImpl;
import br.jus.trems.estagioaprendizado.numberquiz.entities.Problem;
import br.jus.trems.estagioaprendizado.numberquiz.entities.Quiz;
import br.jus.trems.estagioaprendizado.numberquiz.entities.User;
import br.jus.trems.estagioaprendizado.numberquiz.utils.Constants;
import br.jus.trems.estagioaprendizado.numberquiz.utils.SessionUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Bean gerenci�vel utilizado no controle dos quizzes (jogos realizados). <br>
 * Este controle envolve principalmente:
 *                          verificar se a resposta do usu�rio est� correta;
 *                          salvar o jogo atual com a sua devida pontua��o;
 *                          iniciar um novo jogo;
 *                          apresentar as melhores pontua��es;
 *                          
 *
 *
 * @author heverson.vasconcelos
 */
@ManagedBean(name = "quizBean")
@SessionScoped
public class QuizBean implements Serializable {

    /**
     * Armazena a lista com os problemas que ser�o apresentados ao usu�rio.
     */
    private List<Problem> problems;
    /**
     * Utilizado como �ndice de navega��o na lista de problemas.
     */
    private int currentIndex;
    private int answer;
    /**
     * Armazena o score(pontua��o) atual.
     */
    private int score;
    /**
     * Armazena o quiz corrente, ou seja, o jogo propriamente dito.
     */
    private Quiz quiz;
    /**
     * Vari�vel utilizada nos m�todos que ir�o inserir ou consultar alguma
     * informa��o relativa aos problemas.
     */
    private ProblemDaoImpl problemDaoImpl;
    /**
     * Vari�vel utilizada nos m�todos que ir�o inserir ou consultar alguma
     * informa��o relativa aos quizzes (jogos realizados).
     */
    private QuizDaoImpl quizDaoImpl;

    /**
     * Construtor que inicializa as singletons que gerenciar�o os problemas e
     * os quizzes requisitados.
     */
    public QuizBean() {
        problemDaoImpl = new ProblemDaoImpl();
        quizDaoImpl = new QuizDaoImpl();
    }

    /**
     * M�todo que inicializa todas as vari�veis necess�rias para um novo jogo.
     */
    @PostConstruct
    public void init() {
        quiz = new Quiz();

        problems = problemDaoImpl.list();
        score = 0;
        currentIndex = 0;
        Collections.shuffle(problems);
        quiz.setProblems((ArrayList<Problem>) problems);
        quiz.setScore(score);
        quiz.setUser((User) SessionUtil.getAttribute("authenticatedUser"));

    }

    @PreDestroy
    private void finalizeAccess() {
        problemDaoImpl.finalizeAccess();
        quizDaoImpl.finalizeAccess();
    }

    /**
     * M�todo que retorna o score atual.
     * 
     * @return O score atual.
     */
    public int getScore() {
        return score;
    }

    /**
     * M�todo que retorna o problema corrente.
     *
     * @return O problema corrente.
     */
    public Problem getCurrent() {
        return problems.get(currentIndex);
    }

    /**
     * Este m�todo s� foi implementado pela necessidade do JSF obter o valor
     * atual de uma propriedade acessada pela "expression language", antes do
     * novo valor ser submetido pelo formul�rio. <br>
     * No caso desta aplica��o, a expression language #{quizBean.answer} definida
     * na p�gina numberquiz.xhtml, acessa primeiramente o m�todo getAnswer
     * antes de setar a resposta do usu�rio no m�todo setAnswer.
     *
     * @return String vazia ("").
     */
    public String getAnswer() {
        return "";
    }

    /**
     * M�todo que verifica se a resposta do usu�rio est� correta.
     * Incrementa o score caso a resposta esteja correta.
     *
     * @param answeredByUser Resposta do usu�rio.
     */
    public void setAnswer(String answeredByUser) {
        try {
            answer = Integer.parseInt(answeredByUser.trim());
        } catch (NumberFormatException ex) {
        }
    }

    /**
     * M�todo para armazenar no banco, o jogo que foi realizado com o seu score.
     */
    public void saveScore() {
        if (score > 0) {
            quiz.setScore(score);
            quizDaoImpl.create(quiz);
        }
    }

    public String checkSolution() {
        if (getCurrent().getSolution() == answer) {
            score++;
        }
        currentIndex = (currentIndex + 1) % problems.size();

        return null;
    }

    /**
     * M�todo para iniciar um novo jogo.
     * @return String contendo o endere�o de redirecionamento para in�cio do
     *          jogo (numberquiz.xhtml).
     */
    public String newGame() {
        init();

        return Constants.PAGE_NUMBERQUIZ;
    }

    /**
     * M�todo para apresentar ao usu�rio a sua pontua��o final.
     * 
     * @return String contendo o endere�o de redirecionamento para a p�gina
     *          de apresenta��o das pontua��es (stats.xhtml).
     */
    public String showScore() {
        saveScore();

        return Constants.PAGE_STATS;
    }

    /**
     * M�todo para listar as melhores pontua��es j� realizadas.
     * 
     * @return Lista contendo os quizzes(jogos realizados) com as melhores pontua��es.
     */
    public List<Quiz> getTopScores() {
        return quizDaoImpl.getTopScores(Constants.CONFIG_NUMBER_OF_SCORES);
    }
}
