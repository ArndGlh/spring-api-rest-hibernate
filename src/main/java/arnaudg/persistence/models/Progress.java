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
@Table(name = "progress")
public class Progress {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "task_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

    private int actual_progress;
    private int max_progress;

    public Progress(){}

    public Progress(Task task, User user, int actual_progress, int max_progress) {
        this.task = task;
        this.user = user;
        this.actual_progress = actual_progress;
        this.max_progress = max_progress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
        return "Progress{" +
                "id=" + id +
                ", task=" + task +
                ", user=" + user +
                ", actual_progress=" + actual_progress +
                ", max_progress=" + max_progress +
                '}';
    }
}