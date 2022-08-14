package it.discovery.persistence.repository.spring;

import it.discovery.persistence.model.Book;
import it.discovery.persistence.model.tuple.BookInfo;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@Transactional(readOnly = true)
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

    @Override
    public Book findWithHits(int id) {
        TypedQuery<Book> typedQuery = em.createNamedQuery(Book.QUERY_FIND_WITH_HITS, Book.class);
        List<Book> books = typedQuery.setParameter("id", id).getResultList();
        if (books.isEmpty()) {
            return null;
        } else {
            return books.get(0);
        }
    }

    @Override
    public List<BookInfo> findBookInfo() {
        TypedQuery<BookInfo> typedQuery = em.createNamedQuery(
                Book.QUERY_FIND_INFO, BookInfo.class);
        return typedQuery.getResultList();
    }

}
