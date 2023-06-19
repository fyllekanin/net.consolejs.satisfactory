package net.consolejs.service;

import net.consolejs.service.http.HttpService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class ServiceResolver extends AbstractBinder {

    @Override
    protected void configure() {
        bind(HttpService.class).to(HttpService.class);
    }
}
