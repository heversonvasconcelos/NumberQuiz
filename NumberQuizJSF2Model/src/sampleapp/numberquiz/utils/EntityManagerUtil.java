package sampleapp.numberquiz.utils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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

    private static EntityManagerFactory emf;
    private static EntityManager em;

    @PostConstruct
    public static void init() {
        if (!isOpen()) {
            emf = Persistence.createEntityManagerFactory("NumberQuizJSF2ModelPU");
            em = emf.createEntityManager();
        }
    }

    /**
     * M�todo para retornar a EntityManager criada
     *
     * @return EntityManager criada a partir da unidade de persist�ncia NumberQuizJSF2ModelPU
     */
    public static EntityManager getEntityManager() {
        init();
        return em;
    }

    /**
     * M�todo para iniciar uma transa��o no banco. Necess�ria para opera��es
     * transacionais.
     */
    public static void beginTransaction() {
        init();
        if (!isTransactionActive()) {
            em.getTransaction().begin();
        }
    }

    /**
     * M�todo para verificar se a transa��o est� ativa.
     * @return True caso esteja ativa a transa��o. False caso contr�rio.
     */
    public static boolean isTransactionActive() {
        init();
        return em.getTransaction().isActive();
    }

    /**
     * M�todo para dar commit na transa��o.
     */
    public static void commit() {
        init();
        em.getTransaction().commit();
    }

    /**
     * M�todo para inserir um novo objeto no banco.
     * 
     * @param obj Objeto a ser inserido
     */
    public static void insert(Object obj) {
        init();
        em.persist(obj);
    }

    /**
     * M�todo para criar uma query a ser executada no banco.
     * 
     * @param query String que representa a query
     * @return Query criada
     */
    public static Query createQuery(String query) {
        init();
        return em.createQuery(query);
    }

    /**
     * M�todo para atualizar o estado de um determinado objeto no banco.
     * 
     * @param obj Objeto a ser atualizado
     * @return Objeto atualizado
     */
    public static Object update(Object obj) {
        init();
        return em.merge(obj);
    }

    /**
     * M�todo para remover um determinado objeto.
     * 
     * @param obj Objeto a ser removido.
     */
    public static void remove(Object obj) {
        init();
        em.remove(obj);
    }

    @PreDestroy
    public static void close() {
        if (em.isOpen()) {
            em.close();
        }

        if (emf.isOpen()) {
            emf.close();
        }
    }

    public static boolean isOpen() {
        if (emf != null) {
            return emf.isOpen();
        }

        return false;
    }
}
