package si.rso.skupina10.converters;

import si.rso.skupina10.dtos.OrderDto;
import si.rso.skupina10.entities.OrderEntity;

public class OrderConverter {

    public static OrderDto toDto(OrderEntity entity) {
        OrderDto dto = new OrderDto();
        dto.setId(entity.getOrderId());
        dto.setName(entity.getName());
        return dto;
    }

    public static OrderEntity toEntity(OrderDto dto) {
        OrderEntity entity = new OrderEntity();

        entity.setOrderId(dto.getId());
        entity.setName(dto.getName());

        return entity;
    }
}
