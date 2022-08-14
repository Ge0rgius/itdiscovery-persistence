package it.discovery.persistence.service;

import it.discovery.persistence.model.Book;
import it.discovery.persistence.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

	private final BookRepository bookRepository;

	/**
	 * Returns all the books with specified name or pages. Both parameters are
	 * optional. If name is null or pages = 0 we should ignore them
	 *
	 * @param name
	 * @param pages
	 * @return
	 */
	List<Book> searchBooks(String name, int pages) {
		Book probe = new Book();
		if (name != null) {
			probe.setName(name);
		}
		if (pages > 0) {
			probe.setPages(pages);
		}
		return bookRepository.findAll(Example.of(probe));
	}

	void save(Book book) {
		bookRepository.save(book);
	}

}
