package it.discovery.persistence.repository;

import it.discovery.persistence.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Integer> {

    //boolean rename(String name, int id);
}
