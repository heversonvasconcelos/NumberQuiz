package sampleapp.numberquiz.daoimpl;

import sampleapp.numberquiz.dao.ProblemDao;
import sampleapp.numberquiz.entities.Problem;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 * Classe concreta que ser� utilizada na execu��o de todas as opera��es de
 * persist�ncia relativas a entidade Problem.
 *
 * @author heverson.vasconcelos
 */
@Repository
public class ProblemDaoImpl extends GenericDaoImpl<Problem, Integer> implements ProblemDao {

    @Override
    public Class<Problem> getDomainClass() {
        return Problem.class;
    }

    /**
     * M�todo para consultar um problema a partir de uma determinada sequ�ncia.
     * Obs.: Utiliza a NamedQuery Problem.findByProblemSequence descrita na classe
     * Problem.
     *
     * @param problemSequence Sequ�ncia a ser buscada.
     * @return Problema contendo a sequ�ncia problemSequence. <br>
     *         Null caso n�o exista um problema contendo a sequ�ncia problemSequence.
     */
    @Override
    public Problem getProblemBySequence(Integer[] problemSequence) {
        Query query = getEntityManager().createNamedQuery("Problem.findByProblemSequence");
        query.setParameter("problemSequence", problemSequence);

        try {
            return (Problem) query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }

    }

    /**
     * M�todo para verificar se existe um problema com uma determinada sequ�ncia.
     *
     * @param problemSequence Sequ�ncia a ser buscada.
     * @return True caso exista um problema contendo a determinada sequ�ncia.
     */
    @Override
    public boolean verifyIfProblemSequenceExists(Integer[] problemSequence) {
        return (getProblemBySequence(problemSequence) != null) ? true : false;
    }
}
