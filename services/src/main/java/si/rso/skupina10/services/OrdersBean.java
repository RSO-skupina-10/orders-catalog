package si.rso.skupina10.services;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.rso.skupina10.dtos.OrderDto;
import si.rso.skupina10.entities.OrderEntity;
import si.rso.skupina10.converters.OrderConverter;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class OrdersBean {

    @PersistenceContext(unitName = "order-catalog-jpa")
    private EntityManager em;
    public List<OrderDto> getAllOrders(UriInfo uriInfo) {
        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).defaultOffset(0)
                .build();
        return JPAUtils.queryEntities(em, OrderEntity.class, queryParameters).stream()
                .map(OrderConverter::toDto).collect(Collectors.toList());
    }
}
