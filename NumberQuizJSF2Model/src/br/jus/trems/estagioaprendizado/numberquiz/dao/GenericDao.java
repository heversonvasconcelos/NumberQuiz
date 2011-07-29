package br.jus.trems.estagioaprendizado.numberquiz.dao;

import java.io.Serializable;
import java.util.List;

/**
 * DAO - Data Access Object <br>
 * Interface que descreve a assinatura da opera��es b�sicas que cada entidade
 * do banco ir� realizar. Opera��es do CRUD e mais uma de listagem.
 * 
 * @author heverson.vasconcelos
 */
public interface GenericDao<T, ID extends Serializable> {

    /**
     * M�todo para inserir um novo objeto do tipo T no banco.
     * 
     * @param obj Objeto a ser inserido no banco.
     */
    public void create(T obj);

    /**
     * M�todo para consultar um objeto especificado pela id.
     *
     * @param id Identifica��o do objeto.
     * @return Objeto consultado.
     */
    public T retrieve(ID id);

    /**
     * M�todo para atualizar um objeto no banco.
     *
     * @param obj
     * @return Objeto atualizado.
     */
    public T update(T obj);

    public T delete(T obj);

    /* public T delete(T obj); */
    /**
     * M�todo para listar todos os objetos do tipo T contidos no banco.
     *
     * @return Lista contendo objetos do tipo T.
     */
    public List<T> list();
}
