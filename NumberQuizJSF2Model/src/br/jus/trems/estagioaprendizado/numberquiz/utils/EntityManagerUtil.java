package br.jus.trems.estagioaprendizado.numberquiz.utils;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Singleton que realiza as opera��es b�sicas para persist�ncia
 * dos dados no banco. Inicializa uma EntityManager a partir da unidade de persist�ncia
 * NumberQuizJSF2ModelPU.
 *
 * @author heverson.vasconcelos
 */
public class EntityManagerUtil {

    private static EntityManager em = Persistence.createEntityManagerFactory("NumberQuizJSF2ModelPU").createEntityManager();

    /**
     * M�todo para retornar a EntityManager criada
     *
     * @return EntityManager criada a partir da unidade de persist�ncia NumberQuizJSF2ModelPU
     */
    public static EntityManager getEntityManager() {
        return em;
    }

    /**
     * M�todo para iniciar uma transa��o no banco. Necess�ria para opera��es
     * transacionais.
     */
    public static void beginTransaction() {
        em.getTransaction().begin();
    }

    /**
     * M�todo para verificar se a transa��o est� ativa.
     * @return True caso esteja ativa a transa��o. False caso contr�rio.
     */
    public static boolean isTransactionActive() {
        return em.getTransaction().isActive();
    }

    /**
     * M�todo para dar commit na transa��o.
     */
    public static void commit() {
        em.getTransaction().commit();
    }

    /**
     * M�todo para inserir um novo objeto no banco.
     * 
     * @param obj Objeto a ser inserido
     */
    public static void insert(Object obj) {
        em.persist(obj);
    }

    /**
     * M�todo para criar uma query a ser executada no banco.
     * 
     * @param query String que representa a query
     * @return Query criada
     */
    public static Query createQuery(String query) {
        return em.createQuery(query);
    }

    /**
     * M�todo para atualizar o estado de um determinado objeto no banco.
     * 
     * @param obj Objeto a ser atualizado
     * @return Objeto atualizado
     */
    public static Object update(Object obj) {
        return em.merge(obj);
    }

    /**
     * M�todo para remover um determinado objeto.
     * 
     * @param obj Objeto a ser removido.
     */
    public static void remove(Object obj) {
        em.remove(obj);
    }
}
