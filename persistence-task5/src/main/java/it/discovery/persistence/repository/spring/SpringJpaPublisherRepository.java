package it.discovery.persistence.repository.spring;

import it.discovery.persistence.model.Publisher;
import it.discovery.persistence.repository.PublisherRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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

    }

    @Override
    @Transactional(readOnly = true)
    public Publisher findById(int publisherId) {
        return em.find(Publisher.class, publisherId);
    }
}
