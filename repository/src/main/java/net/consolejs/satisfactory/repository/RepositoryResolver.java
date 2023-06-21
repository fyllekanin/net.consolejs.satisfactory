package net.consolejs.satisfactory.repository;

import jakarta.inject.Singleton;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class RepositoryResolver extends AbstractBinder {

    @Override
    protected void configure() {
        bindAsContract(RepositoryFactory.class).in(Singleton.class);
    }
}