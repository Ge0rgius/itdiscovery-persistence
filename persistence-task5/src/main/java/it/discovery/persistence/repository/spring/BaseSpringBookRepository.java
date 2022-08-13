package it.discovery.persistence.repository.spring;

import it.discovery.persistence.model.Book;
import it.discovery.persistence.repository.BookRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;

public abstract class BaseSpringBookRepository implements BookRepository {

    @PersistenceContext
    protected EntityManager em;

    @Override
    @Transactional
    public void save(Book book) {
        if (book.getId() == 0) {
            em.persist(book);
        } else {
            em.merge(book);
        }
    }

    @Override
    public Book findById(int id) {
        return em.find(Book.class, id);
    }
}
