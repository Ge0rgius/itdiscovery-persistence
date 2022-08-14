package it.discovery.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.List;

/**
 * Book publisher
 *
 * @author morenets
 */
@Getter
@Setter
@Entity
@Table
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Publisher extends BaseEntity {

    @Column(nullable = false, length = 64)
    private String name;

    @OneToMany(mappedBy = "publisher")
    private List<Book> books;

}
