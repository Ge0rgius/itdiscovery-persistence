package it.discovery.persistence.bootstrap;

import it.discovery.persistence.config.PersistenceConfig;
import it.discovery.persistence.model.Book;
import it.discovery.persistence.model.BookState;
import it.discovery.persistence.model.Publisher;
import it.discovery.persistence.repository.BookRepository;
import it.discovery.persistence.repository.PublisherRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringStarter {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(
                PersistenceConfig.class)) {
            BookRepository bookRepository = context.getBean(BookRepository.class);

            PublisherRepository publisherRepository = context.getBean(PublisherRepository.class);

            Book book = new Book();
            book.setPages(100);
            book.setName("Hibernate");
            book.setState(BookState.ABSENT);

            Publisher publisher = new Publisher();
            publisher.setName("Packt");

            publisherRepository.save(publisher);

            Publisher publisher2 = publisherRepository.findById(publisher.getId());
            publisher2.setName("O'Really");
            publisherRepository.save(publisher2);
            // book operations
            bookRepository.findAll();
        }
    }

}
