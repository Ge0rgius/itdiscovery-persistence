package it.discovery.persistence.repository.spring;

import it.discovery.persistence.model.Book;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class SpringNamedJpqlBookRepository extends BaseSpringBookRepository {

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> typedQuery = em.createNamedQuery(Book.QUERY_FIND_ALL, Book.class);
        return typedQuery.getResultList();
    }

    @Override
    public List<Book> findWithName(String name) {
        TypedQuery<Book> typedQuery = em.createNamedQuery(Book.QUERY_FIND_BY_NAME, Book.class);
        return typedQuery.setParameter("name", name).getResultList();
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
