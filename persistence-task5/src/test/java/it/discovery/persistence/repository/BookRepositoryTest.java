package it.discovery.persistence.repository;

import it.discovery.persistence.config.PersistenceConfig;
import it.discovery.persistence.model.Book;
import it.discovery.persistence.model.Publisher;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(PersistenceConfig.class)
@Transactional
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
        //@Commit
    void save_bookWithPublisher_success() {
        Book book = new Book();
        book.setName("Hibernate with JPA 3");

        Publisher publisher = new Publisher();
        publisher.setName("Packt");
        book.setPublisher(publisher);

        bookRepository.save(book);
        assertTrue(book.getId() > 0);
        Book book1 = bookRepository.findById(book.getId());
        assertNotNull(book1);
    }

    @Test
    void save_bookWithoutName_error() {
        Book book = new Book();

        assertThrows(PersistenceException.class, () -> bookRepository.save(book));
    }
}