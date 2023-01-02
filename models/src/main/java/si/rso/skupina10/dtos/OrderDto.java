package si.rso.skupina10.dtos;

public class OrderDto {
    private Integer id;

    private Integer status;

    private Integer restaurantId;

    private Integer personId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderRestaurantId(){ return restaurantId; }

    public void setOrderRestaurantId(Integer restaurantId) {this.restaurantId = restaurantId; }

    public Integer getOrderPersonId() {return personId; }

    public void setOrderPersonId(Integer personId) {this.personId = personId;}

    public Integer getOrderStatus() {return status; }

    public void setOrderStatus(Integer status) {this.status = status; }

    public String toString(){ return "Order id: "+ id; }
}
