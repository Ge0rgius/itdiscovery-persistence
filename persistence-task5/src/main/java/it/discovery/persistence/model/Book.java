package it.discovery.persistence.model;

import it.discovery.persistence.converter.BookStateConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Book in a library
 *
 * @author morenets
 */
@Getter
@Setter
@Table
@Entity
public class Book {
    @Column(nullable = false, length = 64)
    private String name;

    @ManyToOne
    @JoinColumn
    private Person author;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Publisher publisher;

    @Convert(converter = BookStateConverter.class)
    private BookState state;

    /**
     * Publishing year
     */
    @Column(name = "`year`")
    private int year;

    /**
     * Total number of pages
     */
    @Column(columnDefinition = "tinyint")
    private int pages;

    @OneToMany(mappedBy = "book")
    private List<Hit> hits;

    @Id
    @GenericGenerator(name = "counter", strategy = "it.discovery.persistence.generator.AutoIncrementGenerator")
    @GeneratedValue(generator = "counter")
    private int id;

    @Column(updatable = false)
    private LocalDateTime created;

    private LocalDateTime modified;
}
