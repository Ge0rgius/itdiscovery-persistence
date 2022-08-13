package it.discovery.persistence.jpa.bootstrap;

import java.time.LocalDateTime;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import it.discovery.persistence.model.Publisher;

public class JpaStarter {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence
                .createEntityManagerFactory("library");
        EntityManager em = null;
        try {
            em = emf.createEntityManager();

            em.getTransaction().begin();
            Publisher publisher = new Publisher();
            publisher.setName("Packt");
            publisher.setCreated(LocalDateTime.now());
            em.persist(publisher);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
