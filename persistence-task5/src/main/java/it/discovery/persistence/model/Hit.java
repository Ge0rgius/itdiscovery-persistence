package it.discovery.persistence.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Table
@Entity
public class Hit {
    @Id
    @GeneratedValue
    private int id;

    private LocalDateTime created;

    private LocalDateTime modified;

    private String ip;

    private String browser;

    private LocalDateTime viewed;

    @ManyToOne
    @JoinColumn
    private Book book;

}
