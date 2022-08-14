package it.discovery.persistence.repository;

import it.discovery.persistence.PersistenceApplication;
import it.discovery.persistence.model.Address;
import it.discovery.persistence.model.Publisher;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(PersistenceApplication.class)
//@Transactional
class PublisherRepositoryTest {

    @Autowired
    PublisherRepository publisherRepository;

    @Test
    void save_validPublisher_success() {
        Publisher publisher = new Publisher();
        publisher.setName("Packt");
        publisher.setAddress(Address.builder().apartment(101).build());

        publisherRepository.save(publisher);
        assertTrue(publisher.getId() > 0);
        Publisher publisher1 = publisherRepository.findById(publisher.getId()).orElseThrow();
        assertNotNull(publisher1);
        assertEquals(101, publisher1.getAddress().getApartment());
        publisher1.setName("Apres");
        publisherRepository.save(publisher1);
        Publisher publisher2 = publisherRepository.findById(publisher.getId()).orElseThrow();
        assertEquals("Apres", publisher2.getName());
    }

    @Test
    void save_publisherWithoutName_error() {
        Publisher publisher = new Publisher();
        publisher.setAddress(Address.builder().apartment(100).build());

        assertThrows(DataIntegrityViolationException.class, () -> publisherRepository.save(publisher));
    }

    @Nested
    public class CacheTests {

        @Autowired
        EntityManagerFactory emf;

        @PersistenceContext
        EntityManager em;

        @Test
        void findById_validPublisher_cached() {
            Publisher publisher = new Publisher();
            publisher.setName("Packt");
            publisher.setAddress(Address.builder().apartment(102).build());

            publisherRepository.save(publisher);

            //TODO check that publisher is taken from cache
//            boolean success = publisherRepository.rename("Apress", publisher.getId());
//            assertTrue(success);

            assertTrue(emf.getCache().contains(Publisher.class, publisher.getId()));

            Publisher publisher1 = publisherRepository.findById(publisher.getId()).orElseThrow();
            assertNotNull(publisher1);
            assertEquals("Packt", publisher1.getName());
        }

    }
}