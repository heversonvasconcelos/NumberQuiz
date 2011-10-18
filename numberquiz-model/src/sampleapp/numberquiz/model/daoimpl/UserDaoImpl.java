package sampleapp.numberquiz.model.daoimpl;

import javax.inject.Named;
import sampleapp.numberquiz.model.dao.UserDao;
import sampleapp.numberquiz.model.entity.User;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * Classe concreta que ser� utilizada na execu��o de todas as opera��es de
 * persist�ncia relativas a entidade User.
 * 
 * @author heverson.vasconcelos
 */
@Named
public class UserDaoImpl extends GenericDaoImpl<User, Integer> implements UserDao {

    @Override
    public Class<User> getDomainClass() {
        return User.class;
    }

    /**
     * M�todo para consultar um usu�rio a partir de um nome de login.
     * Obs.: Utiliza a NamedQuery User.findByUserName descrita na classe User.
     *
     * @param userName Nome de login a ser buscado.
     * @return Usu�rio contendo o nome userName. <br>
     *         Null caso n�o exista um usu�rio contendo o nome userName.
     */
    @Override
    public User getUserByName(String userName) {
        Query query = getEntityManager().createNamedQuery("User.findByUserName");
        query.setParameter("userName", userName);

        try {
            return (User) query.getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }

    }

    /**
     * M�todo para verificar se existe um usu�rio com um determinado nome de login.
     *
     * @param userName Nome de login a ser buscado.
     * @return True caso exista um usu�rio contendo o nome de login.
     */
    @Override
    public boolean verifyIfUserNameExists(String userName) {
        return (getUserByName(userName) != null) ? true : false;
    }
}
