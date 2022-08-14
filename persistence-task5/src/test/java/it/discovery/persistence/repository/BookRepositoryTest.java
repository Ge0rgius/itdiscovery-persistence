package it.discovery.persistence.repository;

import it.discovery.persistence.config.PersistenceConfig;
import it.discovery.persistence.model.Book;
import it.discovery.persistence.model.Hit;
import it.discovery.persistence.model.Person;
import it.discovery.persistence.model.Publisher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(PersistenceConfig.class)
@Transactional
class BookRepositoryTest {

    @Autowired
    List<BookRepository> bookRepositories;

    BookRepository bookRepository;

    @PersistenceContext
    EntityManager em;

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
        //@Commit
    void save_bookWithPublisher_success(int index) {
        bookRepository = bookRepositories.get(index);
        Book book = save();

        assertTrue(book.getId() > 0);
        Book book1 = bookRepository.findById(book.getId());
        assertNotNull(book1);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
        //@Commit
    void findAll_bookExists_success(int index) {
        bookRepository = bookRepositories.get(index);
        Book book = save();

        List<Book> books = bookRepository.findAll();
        assertEquals(1, books.size());
        Book book1 = books.get(0);
        assertEquals(book.getId(), book1.getId());
        assertFalse(book.getHits().isEmpty());
        Hit hit = book.getHits().get(0);
        assertEquals("Chrome", hit.getBrowser());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
        //@Commit
    void findTotalPage_bookExists_success(int index) {
        bookRepository = bookRepositories.get(index);

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

        Hit hit = new Hit();
        hit.setBrowser("Chrome");
        hit.setIp("127.0.0.1");
        book.addHit(hit);

        bookRepository.save(book);

        return book;
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    void save_bookWithoutName_error(int index) {
        bookRepository = bookRepositories.get(index);
        Book book = new Book();

        assertThrows(PersistenceException.class, () -> bookRepository.save(book));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    void findById_bookWithHits_success(int index) {
        bookRepository = bookRepositories.get(index);

        Book book = save();

        Book book1 = bookRepository.findById(book.getId());
        assertNotNull(book1);
        List<Hit> hits = book1.getHits();
        Hit hit = hits.get(0);
        assertEquals("Chrome", hit.getBrowser());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    @Commit
    void findWithHits_bookWithHits_success(int index) {
        bookRepository = bookRepositories.get(index);

        Book book = save();

        Book book1 = bookRepository.findWithHits(book.getId());
        assertNotNull(book1);
        List<Hit> hits = book1.getHits();
        Hit hit = hits.get(0);
        assertEquals("Chrome", hit.getBrowser());
    }
}