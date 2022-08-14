package it.discovery.persistence.repository;

import it.discovery.persistence.PersistenceApplication;
import it.discovery.persistence.model.*;
import it.discovery.persistence.model.tuple.BookInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(PersistenceApplication.class)
//@Transactional
@Slf4j
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
        publisher.setAddress(Address.builder().apartment(100).build());
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
    @Transactional
    void findById_bookWithHits_success(int index) {
        bookRepository = bookRepositories.get(index);

        Book book = save();

        em.flush();
        em.clear();

        Book book1 = bookRepository.findById(book.getId());
        assertNotNull(book1);
        assertEquals(1, book1.getHitCount());
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

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    void findWithName_bookExists_success(int index) {
        bookRepository = bookRepositories.get(index);

        Book book = save();

        List<Book> books = bookRepository.findWithName(book.getName());
        assertTrue(books.size() > 0);
        bookRepository.findWithName(book.getName());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1})
    void findBookInfo_bookExists_success(int index) {
        bookRepository = bookRepositories.get(index);

        int count = bookRepository.findBookInfo().size();

        Book book = save();

        List<BookInfo> books = bookRepository.findBookInfo();
        assertEquals(count + 1, books.size());
        BookInfo info = books.get(0);
        assertEquals("Hibernate with JPA 3", info.name());
    }

    @Nested
    public class CacheTests {
        @ParameterizedTest
        @ValueSource(ints = {0, 1})
        void findById_bookWithHits_success(int index) {
            bookRepository = bookRepositories.get(index);

            Book book = save();

            Book book1 = bookRepository.findById(book.getId());
            assertNotNull(book1);
            Book book2 = bookRepository.findById(book.getId());
        }

    }
}