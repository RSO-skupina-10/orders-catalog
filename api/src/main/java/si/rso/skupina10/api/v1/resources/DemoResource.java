package si.rso.skupina10.api.v1.resources;


import si.rso.skupina10.services.config.RestProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@ApplicationScoped
@Path("/demo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DemoResource {

    private Logger log = Logger.getLogger(DemoResource.class.getName());

    @Inject
    private RestProperties restProperties;

    @GET
    @Path("break")
    public Response makeUnhelthy(){
        restProperties.setBroken(true);

        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("maintenance-on")
    public Response maintenanceOn(){
        restProperties.setMaintenanceMode(true);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("maintenance-off")
    public Response maintenanceOff(){
        restProperties.setMaintenanceMode(false);
        return Response.status(Response.Status.OK).build();
    }
}
