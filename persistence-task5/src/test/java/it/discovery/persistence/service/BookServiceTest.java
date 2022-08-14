package it.discovery.persistence.service;

import it.discovery.persistence.model.Address;
import it.discovery.persistence.model.Book;
import it.discovery.persistence.model.Person;
import it.discovery.persistence.model.Publisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class BookServiceTest {

    @Autowired
    BookService bookService;

    @Test
    void searchBooks_bookExist_success() {
        Book book = new Book();
        book.setName("Spring Data");
        book.setPages(500);

        Publisher publisher = new Publisher();
        publisher.setName("Packt");
        publisher.setAddress(Address.builder().apartment(100).build());
        book.setPublisher(publisher);

        Person author = new Person();
        author.setName("Gavin King");
        book.setAuthor(author);

        bookService.save(book);

        List<Book> books = bookService.searchBooks("Spring Data", 500);
        assertEquals(1, books.size());
        assertEquals(book.getId(), books.get(0).getId());

    }
}