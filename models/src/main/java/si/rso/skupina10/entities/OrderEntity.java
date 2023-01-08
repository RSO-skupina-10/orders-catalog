package si.rso.skupina10.entities;

import javax.persistence.*;

@Entity(name = "orderentity")
@NamedQueries(value = {
        @NamedQuery(name = "Order.getAll", query = "SELECT o FROM orderentity o"),
        @NamedQuery(name = "Order.getOrderById", query = "SELECT o FROM orderentity o WHERE o.orderId = :orderId")
})
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    private Integer status; // 0 if ready for delivery, 1 if being delivered, 2 if already delivered

    private Integer restaurantId;

    private Integer personId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getRestaurantId(){ return restaurantId; }

    public void setRestaurantId(Integer restaurantId) {this.restaurantId = restaurantId; }

    public Integer getPersonId() {return personId; }

    public void setPersonId(Integer personId) {this.personId = personId;}

    public Integer getStatus() {return status; }

    public void setStatus(Integer status) {this.status = status; }
}
