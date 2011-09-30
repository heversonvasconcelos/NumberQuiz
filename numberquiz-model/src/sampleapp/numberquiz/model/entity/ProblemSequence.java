package sampleapp.numberquiz.model.entity;

import java.io.Serializable;
import java.util.Arrays;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Representa a sequ�ncia de n�meros contida em cada problema.
 * � composta pela sequ�ncia de n�meros e uma solu��o (pr�ximo termo da sequ�ncia).
 * <br><br>
 * Ex.: Sequ�ncia: [1, 1, 2, 3, 5]; Solu��o: 8.
 *      (Sequ�ncia de Fibonacci)
 *
 * @author heverson.vasconcelos
 */
@Entity
@Table(name = "TB_PROBLEMSEQUENCE")
public class ProblemSequence implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * Sequ�ncia de n�meros
     */
    @Column(nullable = false, unique = true)
    private Integer[] problemSequence;
    /**
     * Representa a solu��o do problema, ou seja, o pr�ximo termo da sequ�ncia.
     */
    @Column(nullable = false)
    private int solution;

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

    public Integer[] getProblemSequence() {
        return problemSequence;
    }

    public void setProblemSequence(Integer[] problemSequence) {
        this.problemSequence = problemSequence;
    }

    public int getSolution() {
        return solution;
    }

    public void setSolution(int solution) {
        this.solution = solution;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProblemSequence other = (ProblemSequence) obj;
        if (!Arrays.deepEquals(this.problemSequence, other.problemSequence)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Arrays.deepHashCode(this.problemSequence);
        return hash;
    }

    /**
     * M�todo para retornar uma representa��o dos dados da sequ�ncia em modo
     * texto
     *
     * @return String contendo os dados da sequ�ncia
     */
    @Override
    public String toString() {
        return Arrays.toString(problemSequence);
    }
}
