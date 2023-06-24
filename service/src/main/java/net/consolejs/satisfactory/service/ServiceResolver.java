package net.consolejs.satisfactory.service;

import net.consolejs.satisfactory.service.file.FileService;
import net.consolejs.satisfactory.service.http.HttpService;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class ServiceResolver extends AbstractBinder {
    @Override
    protected void configure() {
        bind(HttpService.class).to(HttpService.class);
        bind(FileService.class).to(FileService.class);
    }
}
