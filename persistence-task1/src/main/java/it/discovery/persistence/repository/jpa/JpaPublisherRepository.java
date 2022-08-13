package it.discovery.persistence.repository.jpa;

import it.discovery.persistence.model.Publisher;
import it.discovery.persistence.repository.PublisherRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class JpaPublisherRepository implements PublisherRepository {

    private final EntityManagerFactory emf;

    @Override
    public void save(Publisher publisher) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();

            em.getTransaction().begin();
            em.persist(publisher);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException(ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }

    }

    @Override
    public void saveAll(List<Publisher> publishers) {

    }

    @Override
    public void delete(int publisherId) {

    }

    @Override
    public Publisher findById(int publisherId) {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();

            return em.find(Publisher.class, publisherId);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
