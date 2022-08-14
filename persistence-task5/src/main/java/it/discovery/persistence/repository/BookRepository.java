package it.discovery.persistence.repository;

import it.discovery.persistence.model.Book;
import it.discovery.persistence.model.tuple.BookInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

	/**
	 * Returns all the books with exact name
	 *
	 * @param name
	 * @return
	 */
	List<Book> findByName(String name);

	/**
	 * Returns all the books where name contains specified text
	 *
	 * @param text
	 * @return
	 */
	List<Book> findByNameLike(String text);

	/**
	 * Returns all the books where number of pages is greater than pages parameter
	 *
	 * @param pages
	 * @return
	 */
	List<Book> findByPagesGreaterThan(int pages);

	/**
	 * Returns overall number of pages for all the books
	 *
	 * @return
	 */
	@Query("SELECT SUM(pages) FROM Book")
	int findTotalPages();

	@Query("""
			SELECT b FROM Book b LEFT JOIN FETCH
			b.hits h WHERE b.id=:id
			""")
	Book findWithHits(@Param("id") int id);

	@Query("SELECT new it.discovery.persistence.model.tuple.BookInfo(id,name) FROM Book")
	List<BookInfo> findBookInfo();
}
