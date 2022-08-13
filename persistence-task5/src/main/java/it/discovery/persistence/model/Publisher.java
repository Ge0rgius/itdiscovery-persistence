package it.discovery.persistence.model;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Book publisher
 *
 * @author morenets
 */
@Getter
@Setter
public class Publisher {
    private LocalDateTime created;

    private LocalDateTime modified;

    private String name;

    private List<Book> books;

}
