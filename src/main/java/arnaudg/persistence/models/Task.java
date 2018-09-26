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
    @JoinColumn(name = "progress_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Progress progress;

    private String name;
    private String description;
    private int actual_progress;
    private int max_progress;

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

    public int getActual_progress() {
        return actual_progress;
    }

    public void setActual_progress(int actual_progress) {
        this.actual_progress = actual_progress;
    }

    public int getMax_progress() {
        return max_progress;
    }

    public void setMax_progress(int max_progress) {
        this.max_progress = max_progress;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", actual_progress=" + actual_progress +
                ", max_progress=" + max_progress +
                '}';
    }
}
