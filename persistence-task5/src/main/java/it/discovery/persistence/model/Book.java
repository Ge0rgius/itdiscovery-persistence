package it.discovery.persistence.model;

import it.discovery.persistence.converter.BookStateConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Formula;

import java.util.ArrayList;
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
@NamedQuery(query = "SELECT new it.discovery.persistence.model.tuple.BookInfo(id,name) FROM Book", name = Book.QUERY_FIND_INFO)
@NamedQuery(query = """
        SELECT b FROM Book b LEFT JOIN FETCH
        b.hits h WHERE b.id=:id
        """, name = Book.QUERY_FIND_WITH_HITS)
@DynamicInsert
@DynamicUpdate
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Book extends BaseEntity {

    public static final String QUERY_FIND_ALL = "Book.findAll";

    public static final String QUERY_FIND_BY_NAME = "Book.findByName";
    public static final String QUERY_FIND_WITH_HITS = "Book.findWithHits";
    public static final String QUERY_FIND_INFO = "Book.findInfo";

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
    private Integer year;

    /**
     * Total number of pages
     */
    @Column(columnDefinition = "tinyint")
    private Integer pages;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Hit> hits;

    @Formula("(SELECT COUNT(h.*) FROM HIT h WHERE h.BOOK_ID = ID)")
    private int hitCount;

    public void addHit(Hit hit) {
        if (hits == null) {
            hits = new ArrayList<>();
        }
        hit.setBook(this);
        hits.add(hit);

    }


}
