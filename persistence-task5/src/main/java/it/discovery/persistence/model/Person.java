package it.discovery.persistence.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Person who can write books, for example
 *
 * @author admin
 */
@Getter
@Setter
public class Person {
    private int id;

    private LocalDateTime created;

    private LocalDateTime modified;

    private String name;

    /**
     * Books that person has written
     */
    private List<Book> books;

}
