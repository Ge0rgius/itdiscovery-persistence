package it.discovery.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.time.LocalDateTime;

@Getter
@Setter
@Table
@Entity
@Immutable
public class Hit extends BaseEntity {
    private String ip;

    private String browser;

    private LocalDateTime viewed;

    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book book;

}
