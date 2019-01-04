package arnaudg.persistence.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 */
@Entity
@Table(name="task")
public class Task {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "game_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Game game;

    private String name;
    private String description;
    private int max_progess;

    public Task(){}

    public Task(Game game, String name, String description, int max_progess) {
        this.game = game;
        this.name = name;
        this.description = description;
        this.max_progess = max_progess;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMax_progess() {
        return max_progess;
    }

    public void setMax_progess(int max_progess) {
        this.max_progess = max_progess;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", game=" + game +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", max_progess=" + max_progess +
                '}';
    }
}
