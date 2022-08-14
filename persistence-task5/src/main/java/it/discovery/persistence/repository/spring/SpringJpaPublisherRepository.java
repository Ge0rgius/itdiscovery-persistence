package it.discovery.persistence.repository.spring;

import it.discovery.persistence.model.Publisher;
import it.discovery.persistence.repository.PublisherRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class SpringJpaPublisherRepository implements PublisherRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Publisher publisher) {
        if (publisher.getId() == 0) {
            em.persist(publisher);
        } else {
            em.merge(publisher);
        }
    }

    @Override
    public void saveAll(List<Publisher> publishers) {

    }

    @Override
    public void delete(int publisherId) {
        Publisher publisher = em.find(Publisher.class, publisherId);
        if (publisher != null) {
            em.remove(publisher);
        }

    }

    @Override
    @Transactional(readOnly = true)
    public Publisher findById(int publisherId) {
        return em.find(Publisher.class, publisherId);
    }

    @Override
    public boolean rename(String name, int id) {
        //Query query = em.createNativeQuery("UPDATE PUBLISHER SET NAME=:name WHERE id=:id");
        Query query = em.createQuery("UPDATE Publisher SET name=:name WHERE id=:id");
        query.setParameter("name", name).setParameter("id", id);
        int rows = query.executeUpdate();
        return rows == 1;
    }
}
