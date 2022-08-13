package it.discovery.persistence.repository;

import it.discovery.persistence.config.PersistenceConfig;
import it.discovery.persistence.model.Publisher;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(PersistenceConfig.class)
@Transactional
class PublisherRepositoryTest {

    @Autowired
    PublisherRepository publisherRepository;

    @Test
    void save_validPublisher_success() {
        Publisher publisher = new Publisher();
        publisher.setName("Packt");

        publisherRepository.save(publisher);
        assertTrue(publisher.getId() > 0);
        Publisher publisher1 = publisherRepository.findById(publisher.getId());
        assertNotNull(publisher1);
        publisher1.setName("Apres");
        publisherRepository.save(publisher1);
        Publisher publisher2 = publisherRepository.findById(publisher.getId());
        assertEquals("Apres", publisher2.getName());
    }

    @Test
    void save_publisherWithoutName_error() {
        Publisher publisher = new Publisher();

        assertThrows(PersistenceException.class, () -> publisherRepository.save(publisher));
    }
}