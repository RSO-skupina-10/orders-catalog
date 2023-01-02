package si.rso.skupina10.converters;

import si.rso.skupina10.dtos.OrderDto;
import si.rso.skupina10.entities.OrderEntity;

public class OrderConverter {

    public static OrderDto toDto(OrderEntity entity) {
        OrderDto dto = new OrderDto();
        dto.setId(entity.getOrderId());
        dto.setOrderRestaurantId(entity.getOrderRestaurantId());
        dto.setOrderPersonId(entity.getOrderPersonId());
        dto.setOrderStatus(entity.getOrderStatus());
        return dto;
    }

    public static OrderEntity toEntity(OrderDto dto) {
        OrderEntity entity = new OrderEntity();

        entity.setOrderId(dto.getId());
        entity.setOrderRestaurantId(dto.getOrderRestaurantId());
        entity.setOrderPersonId(dto.getOrderPersonId());
        entity.setOrderStatus(dto.getOrderStatus());
        return entity;
    }
}
