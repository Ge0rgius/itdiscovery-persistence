package it.discovery.persistence.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
	private String name;

	@Transient
	private Person author;

	@Transient
	private Publisher publisher;

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

	@Transient
	private List<Hit> hits;

	@Id
	private int id;

	@Column(updatable = false)
	private LocalDateTime created;

	private LocalDateTime modified;
}
