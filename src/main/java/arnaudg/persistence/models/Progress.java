package arnaudg.persistence.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 */
@Entity
@Table(name = "progress")
public class Progress {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "game_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Game game;

    @OneToMany(targetEntity = Task.class, cascade = CascadeType.ALL)
    private List taskList;

    private double completion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List getTaskList() {
        return taskList;
    }

    public void setTaskList(List taskList) {
        this.taskList = taskList;
    }

    public double getCompletion() {
        return completion;
    }

    public void setCompletion(double completion) {
        this.completion = completion;
    }

    @Override
    public String toString() {
        return "Progress{" +
                "id=" + id +
                ", taskList=" + taskList +
                ", completion=" + completion +
                '}';
    }
}