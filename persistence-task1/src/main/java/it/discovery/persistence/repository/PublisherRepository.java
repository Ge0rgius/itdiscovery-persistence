package it.discovery.persistence.repository;

import java.util.List;

import it.discovery.persistence.model.Publisher;

public interface PublisherRepository {

    /**
     * Saves specified publisher instance
     *
     * @param publisher
     */
    void save(Publisher publisher);

    /**
     * Saves all publisher instances
     *
     * @param publishers
     */
    void saveAll(List<Publisher> publishers);

    /**
     * Deletes publisher with specified identifier
     *
     * @param publisherId
     */
    void delete(int publisherId);

    /**
     * Returns publisher with specified identifier.
     * If no publisher exists then NULL should be returned
     *
     * @param publisherId
     * @return
     */
    Publisher findById(int publisherId);
}
