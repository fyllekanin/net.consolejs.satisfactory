package net.consolejs.satisfactory.restservice.planner.provider;

import net.consolejs.satisfactory.repository.RepositoryFactory;
import net.consolejs.satisfactory.restservice.planner.model.PlannerStep;

public class PlannerProvider {
    private final RepositoryFactory myRepositoryFactory;

    public PlannerProvider(RepositoryFactory repositoryFactory) {
        myRepositoryFactory = repositoryFactory;
    }

    public PlannerStep getSolution(String gameVersion, String recipeClass, float amount) {
        // TODO
        return null;
    }
}
