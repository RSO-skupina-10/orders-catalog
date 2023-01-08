package si.rso.skupina10.converters;

import si.rso.skupina10.dtos.OrderDto;
import si.rso.skupina10.entities.OrderEntity;

public class OrderConverter {

    public static OrderDto toDto(OrderEntity entity) {
        OrderDto dto = new OrderDto();
        dto.setId(entity.getOrderId());
        dto.setRestaurantId(entity.getRestaurantId());
        dto.setPersonId(entity.getPersonId());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public static OrderEntity toEntity(OrderDto dto) {
        OrderEntity entity = new OrderEntity();

        entity.setOrderId(dto.getId());
        entity.setRestaurantId(dto.getRestaurantId());
        entity.setPersonId(dto.getPersonId());
        entity.setStatus(dto.getStatus());
        return entity;
    }
}
