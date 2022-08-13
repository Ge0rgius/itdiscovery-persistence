package it.discovery.persistence.model;

import it.discovery.persistence.converter.BookStateConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Book in a library
 *
 * @author morenets
 */
@Getter
@Setter
@Table(indexes = {@Index(name = "IND_BOOK_NAME", columnList = "name"),
        @Index(name = "IND_BOOK_PAGES", columnList = "pages")})
@Entity
@NamedQuery(query = "FROM Book", name = Book.QUERY_FIND_ALL)
@NamedQuery(query = "FROM Book WHERE name=:name", name = Book.QUERY_FIND_BY_NAME)
public class Book extends BaseEntity {

    public static final String QUERY_FIND_ALL = "Book.findAll";

    public static final String QUERY_FIND_BY_NAME = "Book.findByName";

//    @Id
//    @GenericGenerator(name = "counter", strategy = "it.discovery.persistence.generator.AutoIncrementGenerator")
//    @GeneratedValue(generator = "counter")
//    private int id;

    @Column(nullable = false, length = 64)
    private String name;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn
    private Person author;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
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


}
