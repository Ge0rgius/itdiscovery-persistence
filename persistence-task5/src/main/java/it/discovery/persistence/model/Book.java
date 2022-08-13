package it.discovery.persistence.model;

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
public class Book {
	private int id;

	private LocalDateTime created;

	private LocalDateTime modified;

	private String name;

	private Person author;

	private Publisher publisher;

	/**
	 * Publishing year
	 */
	private int year;

	/**
	 * Total number of pages
	 */
	private int pages;

	private List<Hit> hits;

}
