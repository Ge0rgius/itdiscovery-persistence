package it.discovery.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Table
@Entity
public class Hit extends BaseEntity {
    private String ip;

    private String browser;

    private LocalDateTime viewed;

    @ManyToOne
    @JoinColumn
    private Book book;

}
