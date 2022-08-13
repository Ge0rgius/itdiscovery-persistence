package it.discovery.persistence.repository;

import it.discovery.persistence.model.Book;

import java.util.List;

public interface BookRepository {

	/**
	 * Returns all the existing books
	 *
	 * @return
	 */
	List<Book> findAll();

	/**
	 * Returns all the books with exact name
	 *
	 * @param name
	 * @return
	 */
	List<Book> findWithName(String name);

	/**
	 * Returns all the books where name contains specified text
	 *
	 * @param text
	 * @return
	 */
	List<Book> findLikeName(String text);

	/**
	 * Returns all the books where number of pages is greater than pages parameter
	 *
	 * @param pages
	 * @return
	 */
	List<Book> findWithMorePages(int pages);

	/**
	 * Returns overall number of pages for all the books
	 *
	 * @return
	 */
	int findTotalPages();

	/**
	 * Returns all the books sorted by name
	 *
	 * @return
	 */
	List<Book> findSortedBooks();

	/**
	 * @param book
	 */
	void save(Book book);

	Book findById(int id);
}
