package it.discovery.persistence.repository.spring;

import it.discovery.persistence.model.Book;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class SpringJpaBookRepository extends BaseSpringBookRepository {

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> typedQuery = em.createQuery("FROM Book", Book.class);

        return typedQuery.getResultList();
    }

    @Override
    public List<Book> findWithName(String name) {
        TypedQuery<Book> typedQuery = em.createQuery("FROM Book WHERE name=:name", Book.class);
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

}
