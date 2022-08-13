package it.discovery.persistence.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Person who can write books, for example
 *
 * @author admin
 */
@Getter
@Setter
@Table
@Entity
public class Person {
    @Id
    @GeneratedValue
    private int id;

    private LocalDateTime created;

    private LocalDateTime modified;

    private String name;

    /**
     * Books that person has written
     */
    @OneToMany(mappedBy = "author")
    private List<Book> books;

}
