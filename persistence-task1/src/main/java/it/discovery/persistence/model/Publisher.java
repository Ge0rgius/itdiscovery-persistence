package it.discovery.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Book publisher
 *
 * @author morenets
 */
@Getter
@Setter
@Entity
@Table
public class Publisher {
    @Id
    private int id;

    private String name;

    @Transient
    private List<Book> books;

    private LocalDateTime created;

    private LocalDateTime modified;
}
