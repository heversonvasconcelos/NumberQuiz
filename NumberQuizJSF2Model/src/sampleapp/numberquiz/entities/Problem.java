package sampleapp.numberquiz.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Classe entidade que representa cada problema a ser solucionado pelo usu�rio.
 * � composto por uma sequ�ncia de n�meros (ProblemSequence). Este atributo
 * define a unicidade de um problema, ou seja, para cada problema existir�
 * somente uma sequ�ncia e vice-versa.
 * <br><br>
 * Ex.: Sequ�ncia: [1, 1, 2, 3, 5]; Solu��o: 8.
 *      (Sequ�ncia de Fibonacci)
 * <br><br>
 * Possui uma NamedQuery(Problem.findByProblemSequence) que ser� utilizada para
 * consultar um problema a partir de uma sequ�ncia (ProblemSequence).
 * 
 * @author heverson.vasconcelos
 */
@Entity
@Table(name = "TB_PROBLEM")
@NamedQuery(name = "Problem.findByProblemSequence",
query = "SELECT p FROM Problem p "
+ "JOIN p.problemSequence pseq "
+ "WHERE problemSequence = :problemSequence")
public class Problem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * Representa a sequ�ncia de n�meros que o usu�rio dever� adivinhar
     * o pr�ximo n�mero desta mesma.
     */
    @OneToOne(cascade = javax.persistence.CascadeType.ALL)
    private ProblemSequence problemSequence = new ProblemSequence();

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

    public ProblemSequence getProblemSequence() {
        return problemSequence;
    }

    public void setProblemSequence(ProblemSequence problemSequence) {
        this.problemSequence = problemSequence;
    }

    public Integer[] getIntegerArrayProblemSequence() {
        return problemSequence.getProblemSequence();
    }

    public void setProblemSequence(Integer[] problemSequence) {
        this.problemSequence.setProblemSequence(problemSequence);
    }

    public int getSolution() {
        return problemSequence.getSolution();
    }

    public void setSolution(int solution) {
        this.problemSequence.setSolution(solution);
    }

    public void setProblemSequenceAndSolution(Integer[] values, int solution) {
        this.problemSequence.setProblemSequence(values);
        this.problemSequence.setSolution(solution);
    }

    /*
     * Hashcode e equals foram sobrescritos para que um problema possa ser
     * comparado com outro a partir da sequ�ncia deste problema (ProblemSequence)
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
        final Problem other = (Problem) obj;
        if (this.problemSequence != other.problemSequence && (this.problemSequence == null || !this.problemSequence.equals(other.problemSequence))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (this.problemSequence != null ? this.problemSequence.hashCode() : 0);
        return hash;
    }

    /**
     * M�todo para retornar uma representa��o dos dados do problema em modo texto
     *
     * @return String contendo os dados do problema
     */
    @Override
    public String toString() {
        return (getId() + " - " + problemSequence + " - " + getSolution());
    }
}
