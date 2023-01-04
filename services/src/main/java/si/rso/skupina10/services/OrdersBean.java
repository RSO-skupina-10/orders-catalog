package si.rso.skupina10.services;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import org.eclipse.persistence.annotations.TimeOfDay;
import org.glassfish.jersey.model.internal.RankedComparator;
import si.rso.skupina10.dtos.OrderDto;
import si.rso.skupina10.entities.OrderEntity;
import si.rso.skupina10.converters.OrderConverter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@ApplicationScoped
public class OrdersBean {

    @PersistenceContext(unitName = "order-catalog-jpa")
    private EntityManager em;

    private static final Logger log = Logger.getLogger(OrdersBean.class.getName());

    @PostConstruct
    private void init(){ log.info("Initialization of the " + OrdersBean.class.getSimpleName()); }

    @PreDestroy
    private void destroy(){ log.info( "Destroy "+ OrdersBean.class.getSimpleName()); }

    public List<OrderDto> getOrders() {
        try{
            Query q = em.createNamedQuery("Order.getAll");
            List<OrderEntity> resultList = (List<OrderEntity>) q.getResultList();
            return resultList.stream().map(OrderConverter::toDto).collect(Collectors.toList());
        } catch (Exception e){
            log.severe("Error at getOrders: " + e);
            return null;
        }
    }

    public List<OrderDto> getOrders(UriInfo uriInfo){
        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery()).defaultOffset(0)
                .build();

        return JPAUtils.queryEntities(em, OrderEntity.class, queryParameters).stream()
                .map(OrderConverter::toDto).collect(Collectors.toList());
    }

    public OrderDto getOrder(Integer orderId) {
        try {
            Query q = em.createNamedQuery("Order.getOrderById");
            q.setParameter("orderId", orderId);
            return OrderConverter.toDto((OrderEntity) q.getSingleResult());
        } catch (Exception e) {
            log.severe("Error at getOrder by id: " + e);
            return null;
        }
    }

    @Transactional
    public OrderDto addOrder(OrderDto o) {
        try {
            Integer status = o.getOrderStatus();
            Integer restaurantId = o.getOrderRestaurantId();
            Integer personId = o.getOrderPersonId();

            if (status == null) {
                log.info("Cannot add order without status");
                return null;
            } else if (restaurantId == null) {
                log.info("Cannot add order without restaurantId");
                return null;
            } else if (personId == null) {
                log.info("Cannot add order without personId");
                return null;
            } else {
                OrderEntity entity = OrderConverter.toEntity(o);
                em.persist(entity);
                em.flush();
                log.info("Order " + o.getId() + " was added");
                return OrderConverter.toDto(entity);
            }
        } catch (Exception e) {
            log.severe("Error at addOrder "+ e);
            return null;
        }
    }

    @Transactional
    public boolean removeOrder(Integer orderId) {
        OrderEntity order = em.find(OrderEntity.class, orderId);

        if (order != null) {
            em.remove(order);
            em.flush();
            return true;
        }
        return false;
    }

}
