package si.rso.skupina10.api.v1.resources;

import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import si.rso.skupina10.dtos.OrderDto;
import si.rso.skupina10.services.OrdersBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/orders")
public class OrdersResource {

    private Logger log = Logger.getLogger(OrdersResource.class.getName());

    @Context
    protected UriInfo uriInfo;
    @Inject
    private OrdersBean ordersBean;

    @Operation(description = "Get all orders.", summary = "Get all orders")
    @APIResponses({
            @APIResponse(responseCode = "200",
                    description = "List of orders data",
                    content = @Content(schema = @Schema(implementation = OrderDto.class, type = SchemaType.ARRAY))
                    //,headers = {@Header(name = "X-Total-Count", description = "Number of objects in list")}
            )})
    @GET
    @Metered(name = "order_get_all_meter")
    public Response getOrders() {
        log.info("getOrders called.");
        List<OrderDto> orders = ordersBean.getOrders(uriInfo);

        log.info("getOrders output: " + orders.toString());
        return Response.status(Response.Status.OK).entity(orders).build();
    }

    @GET
    @Path("{id}")
    @Operation(description = "Get info about order with id.", summary = "Order info")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Order info",
                    content = @Content(schema = @Schema(implementation = OrderDto.class))
            ), @APIResponse(responseCode = "404", description = "Order not found")
    })
    @Metered(name = "order_get_by_id_meter")
    public Response getOrderById(@PathParam("id") Integer id) {
        log.info("getOrderById called with id " + id);
        OrderDto orderDto = ordersBean.getOrder(id);
        if (orderDto != null) {
            log.info("getOrderById was successful " + orderDto);
            return Response.ok(orderDto).build();
        } else {
            log.severe("Did not find order with this id (" + id + ").");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Operation(description = "Add new order", summary = "Add new order")
    @APIResponses({
            @APIResponse(responseCode = "201", description = "New order added", content = @Content(schema = @Schema(implementation = OrderDto.class))),
            @APIResponse(responseCode = "400", description = "Error adding new order")
    })
    @Metered(name = "order_add_new_meter")
    public Response addNewRestaurant(@RequestBody(
            description = "Order DTO object",
            required = true,
            content = @Content(
                    schema = @Schema(implementation = OrderDto.class)
            )
    ) OrderDto orderDto) {
        log.info("addNewOrder " + orderDto);
        OrderDto newOrder = ordersBean.addOrder(orderDto);
        if (newOrder == null) {
            log.severe("Could not add new order.");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        log.info("Order created.");
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("{id}")
    @Operation(description = "Delete order", summary = "Delete order by id")
    @APIResponses({
            @APIResponse(responseCode = "204", description = "Order deleted"),
            @APIResponse(responseCode = "400", description = "Error deleting order")
    })
    @Metered(name = "order_delete_meter")
    public Response deleteOrder(@PathParam("id") Integer id) {
        log.info("deleteOrder called with id (" + id + ")");
        boolean deleted = ordersBean.removeOrder(id);
        if (deleted) {
            log.info("Order deleted id (" + id + ")");
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        log.severe("Could not delete order with id (" + id + ")");
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
