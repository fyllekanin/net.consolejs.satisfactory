package net.consolejs.satisfactory.restservice.initializer.provider;

import net.consolejs.satisfactory.entityview.document.gameimport.GameImportDocument;
import net.consolejs.satisfactory.entityview.document.gameimport.GameImportType;
import net.consolejs.satisfactory.repository.RepositoryFactory;
import net.consolejs.satisfactory.repository.gameimport.GameImportRepository;
import net.consolejs.satisfactory.restservice.initializer.model.InitialData;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InitialDataProvider {
    private final RepositoryFactory myRepositoryFactory;

    public InitialDataProvider(RepositoryFactory repositoryFactory) {
        myRepositoryFactory = repositoryFactory;
    }

    public InitialData getInitialData() {

        return InitialData.newBuilder()
                          .withGameVersions(getGameVersions())
                          .build();
    }

    private List<GameImportDocument> getGameVersions() {
        GameImportRepository repository = myRepositoryFactory.of(GameImportRepository.class);

        return Stream.of(repository.getLatestOfType(GameImportType.EARLY_ACCESS),
                         repository.getLatestOfType(GameImportType.EXPERIMENTAL))
                     .filter(Objects::nonNull)
                     .collect(Collectors.toList());
    }
}
