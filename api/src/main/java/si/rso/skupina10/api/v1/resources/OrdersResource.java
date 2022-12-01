package si.rso.skupina10.api.v1.resources;

import si.rso.skupina10.dtos.OrderDto;
import si.rso.skupina10.services.OrdersBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/orders")
public class OrdersResource {

    @Inject
    private OrdersBean ordersBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    public Response getOrders() {
        List<OrderDto> orderDtoList = ordersBean.getAllOrders(uriInfo);

        return Response.status(Response.Status.OK).entity(orderDtoList).build();
    }
}
