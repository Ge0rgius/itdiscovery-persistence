package it.discovery.persistence.jpa.bootstrap;

import it.discovery.persistence.model.Book;
import it.discovery.persistence.model.BookState;
import it.discovery.persistence.model.Publisher;
import it.discovery.persistence.repository.jpa.JpaPublisherRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class JpaStarter {

    public static void main(String[] args) {
        var emf = Persistence
                .createEntityManagerFactory("library");

        var publisherRepository = new JpaPublisherRepository(emf);
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();

            Book book = new Book();
            book.setPages(100);
            book.setName("Hibernate");
            book.setState(BookState.ABSENT);

            em.persist(book);
            em.getTransaction().commit();

            Publisher publisher = new Publisher();
            publisher.setName("Packt");

            publisherRepository.save(publisher);

            Publisher publisher2 = publisherRepository.findById(publisher.getId());
            publisher2.setName("O'Really");
            publisherRepository.save(publisher2);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
