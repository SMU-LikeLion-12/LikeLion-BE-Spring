package com.likelion.lionshop_sample.dto.response;

import com.likelion.lionshop_sample.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Builder //Builder 패턴을 사용합니다.
@Getter
public class OrderResponseDto {

    public Long id;

    public String name;

    public int quantity;

    public int price;

    public Long userId;

    //Order Entity -> Response Dto로 변환하는 메서드
    public static OrderResponseDto from(Order order) {
        return OrderResponseDto.builder()
                .id(order.getId())
                .name(order.getName())
                .quantity(order.getQuantity())
                .price(order.getPrice())
                .userId(order.getUser().getId())
                .build();
    }
    public static List<OrderResponseDto> from(List<Order> orders) {
        return orders.stream().map(OrderResponseDto::from).collect(Collectors.toList());
    }
}
