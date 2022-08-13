package it.discovery.persistence.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Log {
    private int id;

    private LocalDateTime created;

    private int entityId;

    private String entity;

    private boolean success;

    private String error;

}
