package it.discovery.persistence.generator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.util.concurrent.atomic.AtomicInteger;

public class AutoIncrementGenerator implements IdentifierGenerator {

    private final static int INITIAL_OFFSET = 100;

    private final AtomicInteger counter = new AtomicInteger();

    @Override
    //TODO generate identifier the same type of id field(long or int)
    public Object generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object object)
            throws HibernateException {
        return INITIAL_OFFSET + counter.incrementAndGet();
    }
}
