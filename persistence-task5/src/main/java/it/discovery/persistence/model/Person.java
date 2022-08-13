package it.discovery.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

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
public class Person extends BaseEntity {

    private String name;

    /**
     * Books that person has written
     */
    @OneToMany(mappedBy = "author")
    private List<Book> books;

}
