package it.discovery.persistence.repository;

import it.discovery.persistence.config.PersistenceConfig;
import it.discovery.persistence.model.Book;
import it.discovery.persistence.model.Person;
import it.discovery.persistence.model.Publisher;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(PersistenceConfig.class)
@Transactional
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
        //@Commit
    void save_bookWithPublisher_success() {
        Book book = save();

        assertTrue(book.getId() > 0);
        Book book1 = bookRepository.findById(book.getId());
        assertNotNull(book1);
    }

    @Test
        //@Commit
    void findAll_bookExists_success() {
        Book book = save();

        List<Book> books = bookRepository.findAll();
        assertEquals(1, books.size());
        Book book1 = books.get(0);
        assertEquals(book.getId(), book1.getId());
    }

    @Test
        //@Commit
    void findTotalPage_bookExists_success() {
        save();

        int totalPages = bookRepository.findTotalPages();
        assertEquals(100, totalPages);
    }

    private Book save() {
        Book book = new Book();
        book.setName("Hibernate with JPA 3");
        book.setPages(100);

        Publisher publisher = new Publisher();
        publisher.setName("Packt");
        book.setPublisher(publisher);

        Person author = new Person();
        author.setName("Gavin King");
        book.setAuthor(author);

        bookRepository.save(book);

        return book;
    }

    @Test
    void save_bookWithoutName_error() {
        Book book = new Book();

        assertThrows(PersistenceException.class, () -> bookRepository.save(book));
    }
}