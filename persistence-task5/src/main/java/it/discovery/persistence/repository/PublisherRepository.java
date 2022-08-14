package it.discovery.persistence.repository;

import it.discovery.persistence.model.Publisher;

import java.util.List;

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

    boolean rename(String name, int id);
}
