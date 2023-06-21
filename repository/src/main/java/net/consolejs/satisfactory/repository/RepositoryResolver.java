package net.consolejs.satisfactory.repository;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class RepositoryResolver extends AbstractBinder {

    @Override
    protected void configure() {
        bind(new RepositoryFactory()).to(RepositoryFactory.class);
    }
}