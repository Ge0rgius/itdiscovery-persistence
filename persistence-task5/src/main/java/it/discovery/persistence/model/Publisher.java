package it.discovery.persistence.model;

import jakarta.persistence.*;
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
    @GeneratedValue
    private int id;

    @Column(nullable = false, length = 64)
    private String name;

    @Transient
    private List<Book> books;

    @Column(updatable = false)
    private LocalDateTime created;

    private LocalDateTime modified;

    @PrePersist
    void onPersist() {
        created = LocalDateTime.now();
    }

    @PreUpdate
    void onUpdate() {
        modified = LocalDateTime.now();
    }
}
