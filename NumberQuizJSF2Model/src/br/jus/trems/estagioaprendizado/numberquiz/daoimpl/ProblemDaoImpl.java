package br.jus.trems.estagioaprendizado.numberquiz.daoimpl;

import br.jus.trems.estagioaprendizado.numberquiz.entities.Problem;
import br.jus.trems.estagioaprendizado.numberquiz.utils.EntityManagerUtil;
import java.io.Serializable;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * Classe concreta que ser� utilizada na execu��o de todas as opera��es de
 * persist�ncia relativas a entidade Problem.
 *
 * @author heverson.vasconcelos
 */
public class ProblemDaoImpl extends DaoImpl<Problem> implements Serializable {

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
    public Problem getProblemBySequence(Integer[] problemSequence) {
        Query query = EntityManagerUtil.getEntityManager().createNamedQuery("Problem.findByProblemSequence");
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
    public boolean verifyIfProblemSequenceExists(Integer[] problemSequence) {
        return (getProblemBySequence(problemSequence) != null) ? true : false;
    }
}
