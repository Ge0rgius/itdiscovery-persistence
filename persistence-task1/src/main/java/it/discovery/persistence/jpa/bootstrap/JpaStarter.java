package it.discovery.persistence.jpa.bootstrap;

import it.discovery.persistence.model.Book;
import it.discovery.persistence.model.Publisher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class JpaStarter {

    public static void main(String[] args) {
        var emf = Persistence
                .createEntityManagerFactory("library");
        EntityManager em = null;
        try {
            em = emf.createEntityManager();

            em.getTransaction().begin();
            Publisher publisher = new Publisher();
            publisher.setName("Packt");

            em.persist(publisher);

            Book book = new Book();
            book.setPages(100);
            book.setName("Hibernate");

            em.persist(book);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
