package sampleapp.numberquiz.model.daoimpl;

import sampleapp.numberquiz.model.dao.QuizDao;
import sampleapp.numberquiz.model.entity.Quiz;
import java.util.List;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * Classe concreta que ser� utilizada na execu��o de todas as opera��es de
 * persist�ncia relativas a entidade Quiz.
 * 
 * @author heverson.vasconcelos
 */
@Named
public class QuizDaoImpl extends GenericDaoImpl<Quiz, Integer> implements QuizDao {

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
     * @param numberOfScores N�mero m�ximo de quizzes que dever�o ser consultados.
     * @return Lista contendo os quizzes resultado da consulta.
     */
    public List<Quiz> getTopScores(int numberOfScores) {
        Query query = getEntityManager().createNamedQuery("Quiz.getTopScores");

        try {
            query.setMaxResults(numberOfScores);
            return query.getResultList();
        } catch (NoResultException nre) {
            return null;
        }

    }
}
