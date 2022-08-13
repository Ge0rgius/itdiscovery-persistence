package it.discovery.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Table
@Entity
public class Log {
    @Id
    @GeneratedValue
    private int id;

    private LocalDateTime created;

    private int entityId;

    private String entity;

    private boolean success;

    private String error;

}
