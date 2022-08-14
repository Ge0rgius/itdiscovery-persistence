package it.discovery.persistence.repository.spring;

import it.discovery.persistence.model.Book;
import jakarta.persistence.TypedQuery;
import org.hibernate.jpa.AvailableHints;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@Transactional(readOnly = true)
public class SpringJpaBookRepository extends BaseSpringBookRepository {

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> typedQuery = em.createQuery("FROM Book", Book.class);

        return typedQuery.getResultList();
    }

    @Override
    public List<Book> findWithName(String name) {
        TypedQuery<Book> typedQuery = em.createQuery("FROM Book WHERE name=:name", Book.class);
        typedQuery.setHint(AvailableHints.HINT_CACHEABLE, true);
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
        TypedQuery<Long> typedQuery = em.createQuery("SELECT SUM(pages) FROM Book", Long.class);
        return typedQuery.getSingleResult().intValue();
    }

    @Override
    public List<Book> findSortedBooks() {
        return null;
    }

    @Override
    public Book findWithHits(int id) {
        TypedQuery<Book> typedQuery = em.createQuery("""
                SELECT b FROM Book b LEFT JOIN FETCH
                b.hits h WHERE b.id=:id
                """, Book.class);
        List<Book> books = typedQuery.setParameter("id", id).getResultList();
        if (books.isEmpty()) {
            return null;
        } else {
            return books.get(0);
        }
    }

}
