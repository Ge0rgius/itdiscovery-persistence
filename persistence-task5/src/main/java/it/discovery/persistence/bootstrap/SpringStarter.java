package it.discovery.persistence.bootstrap;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import it.discovery.persistence.config.PersistenceConfig;
import it.discovery.persistence.repository.BookRepository;

public class SpringStarter {

    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext context =
                     new AnnotationConfigApplicationContext(
                             PersistenceConfig.class)) {
            BookRepository bookRepository = context.getBean(BookRepository.class);
            // book operations
            bookRepository.findAll();
        }
    }

}
