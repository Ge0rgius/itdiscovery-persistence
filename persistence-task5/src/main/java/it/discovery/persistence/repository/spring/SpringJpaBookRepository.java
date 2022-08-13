package it.discovery.persistence.repository.spring;

import it.discovery.persistence.model.Book;
import it.discovery.persistence.repository.BookRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class SpringJpaBookRepository implements BookRepository {
    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public List<Book> findWithName(String name) {
        return null;
    }

    @Override
    public List<Book> findLikeName(String text) {
        return null;
    }

    @Override
    public List<Book> findWithMorePages(int pages) {
        return null;
    }

    @Override
    public int findTotalPages() {
        return 0;
    }

    @Override
    public List<Book> findSortedBooks() {
        return null;
    }
}
