package it.discovery.persistence.service;

import it.discovery.persistence.model.Book;
import it.discovery.persistence.repository.BookRepository;

import java.util.List;

public class BookService {

	private BookRepository bookRepository;

	/**
	 * Returns all the books with specified name or pages. Both parameters are
	 * optional. If name is null or pages = 0 we should ignore them
	 *
	 * @param name
	 * @param pages
	 * @return
	 */
	List<Book> searchBooks(String name, int pages) {
		// TODO implement
		return null;
	}

}
