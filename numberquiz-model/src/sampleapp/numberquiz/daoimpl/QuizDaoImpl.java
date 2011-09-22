package sampleapp.numberquiz.daoimpl;

import sampleapp.numberquiz.dao.QuizDao;
import sampleapp.numberquiz.entities.Quiz;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 * Classe concreta que ser� utilizada na execu��o de todas as opera��es de
 * persist�ncia relativas a entidade Quiz.
 * 
 * @author heverson.vasconcelos
 */
@Repository
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
