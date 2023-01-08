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

    public Integer getRestaurantId(){ return restaurantId; }

    public void setRestaurantId(Integer restaurantId) {this.restaurantId = restaurantId; }

    public Integer getPersonId() {return personId; }

    public void setPersonId(Integer personId) {this.personId = personId;}

    public Integer getStatus() {return status; }

    public void setStatus(Integer status) {this.status = status; }

    public String toString(){ return "Order id: "+ id; }
}
