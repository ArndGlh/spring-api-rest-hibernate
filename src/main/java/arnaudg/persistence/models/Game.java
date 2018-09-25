package arnaudg.persistence.models;

import org.hibernate.annotations.Columns;

import javax.persistence.*;
import java.util.List;

/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 */
@Entity
@Table(name="game")
public class Game {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @OneToMany(targetEntity = User.class, cascade = CascadeType.ALL)
    private List userList;

    @Column(name = "title")
    private String title;

    @Column(name = "genre")
    private String genre;

    @Column(name = "year")
    private String year;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}