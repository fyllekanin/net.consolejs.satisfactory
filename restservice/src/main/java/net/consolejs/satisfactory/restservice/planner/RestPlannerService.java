package net.consolejs.satisfactory.restservice.planner;

import com.google.gson.Gson;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import net.consolejs.satisfactory.repository.RepositoryFactory;
import net.consolejs.satisfactory.restservice.planner.model.PlannerStep;
import net.consolejs.satisfactory.restservice.planner.provider.PlannerProvider;

import java.util.logging.Logger;

@Path("/v1/planner")
public class RestPlannerService {
    private static final Logger LOGGER = Logger.getLogger(RestPlannerService.class.getName());
    @Inject
    private RepositoryFactory myRepositoryFactory;

    private PlannerProvider myPlannerProvider;

    @PostConstruct
    public void init() {
        myPlannerProvider = new PlannerProvider(myRepositoryFactory);
    }

    @GET
    @Path("/{gameVersion}/{recipeClassName}/{amount}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlanning(@PathParam("gameVersion") String gameVersion,
                                @PathParam("recipeClassName") String recipeClassName,
                                @PathParam("amount") float amount) {
        PlannerStep solution = myPlannerProvider.getSolution(gameVersion, recipeClassName, amount);

        return Response
                .status(Response.Status.OK)
                .entity(new Gson().toJson(solution, PlannerStep.class))
                .build();
    }
}
