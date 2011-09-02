package sampleapp.numberquiz.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Classe entidade que representa um usu�rio do 
 * jogo. � composto por um nome de login e uma senha.
 * O atributo nome define a unicidade de um usu�rio, ou seja, para cada usu�rio
 * existir� somente um nome de login.
 *
 * Possui uma NamedQuery (User.findByUserName) que ser� utilizada para consultar
 * um usu�rio a partir de um nome de login.
 *
 * @author heverson.vasconcelos
 */
@Entity
@Table(name = "TB_USER")
@NamedQuery(name = "User.findByUserName",
query = "SELECT u FROM User AS u WHERE u.name = :userName")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * Nome de login do usu�rio
     */
    @Column(length = 20, nullable = false, unique = true)
    private String name;
    /**
     * Senha do usu�rio
     */
    @Column(length = 10, nullable = false)
    private String password;

    /*
     *
     * GETTERS e SETTERS
     *
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*
     * Hashcode e equals foram sobrescritos para que um usu�rio possa ser
     * comparado com outro a partir do nome
     *
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

    /**
     * M�todo para retornar uma representa��o dos dados do usu�rio em modo texto
     * 
     * @return String contendo os dados do usu�rio
     */
    @Override
    public String toString() {
        return (getId() + " - " + getName() + " - " + getPassword());
    }
}
