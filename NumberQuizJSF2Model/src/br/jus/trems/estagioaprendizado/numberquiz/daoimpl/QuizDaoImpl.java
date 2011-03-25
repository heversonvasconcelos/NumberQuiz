package br.jus.trems.estagioaprendizado.numberquiz.daoimpl;

import br.jus.trems.estagioaprendizado.numberquiz.entities.Quiz;
import br.jus.trems.estagioaprendizado.numberquiz.utils.EntityManagerUtil;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * Classe concreta que ser� utilizada na execu��o de todas as opera��es de
 * persist�ncia relativas a entidade Quiz.
 * 
 * @author heverson.vasconcelos
 */
public class QuizDaoImpl extends DaoImpl<Quiz> {

    @Override
    public Class<Quiz> getDomainClass() {
        return Quiz.class;
    }

    /**
     * M�todo para listar os quizzes com as melhores pontua��es.
     * Ser�o organizados a partir da pontua��o de forma decrescrente.
     * Obs.: Utiliza a NamedQuery Quiz.getTopScores descrita na classe
     * Quiz.
     *
     * @param N�mero m�ximo de quizzes que dever�o ser consultados.
     * @return Lista contendo os quizzes resultado da consulta.
     */
    public List<Quiz> getTopScores(int numberOfScores) {
        Query query = EntityManagerUtil.getEntityManager().createNamedQuery("Quiz.getTopScores");

        try {
            query.setMaxResults(numberOfScores);
            return query.getResultList();
        } catch (NoResultException nre) {
            return null;
        }

    }
}
