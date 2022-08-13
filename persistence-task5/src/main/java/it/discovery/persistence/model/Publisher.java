package it.discovery.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

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
public class Publisher extends BaseEntity {

    @Column(nullable = false, length = 64)
    private String name;

    @OneToMany(mappedBy = "publisher")
    private List<Book> books;

}
