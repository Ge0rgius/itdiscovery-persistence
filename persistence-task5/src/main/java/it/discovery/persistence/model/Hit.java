package it.discovery.persistence.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Hit {
    private int id;

    private LocalDateTime created;

    private LocalDateTime modified;

    private String ip;

    private String browser;

    private LocalDateTime viewed;

    private Book book;

}
