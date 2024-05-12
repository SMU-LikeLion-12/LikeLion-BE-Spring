package com.likelion.lionshop_sample.dto.request;

import com.likelion.lionshop_sample.entity.Order;
import com.likelion.lionshop_sample.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor //매개변수 없는 생성자를 생성해 줍니다.
@AllArgsConstructor
@Getter
public class CreateOrderRequestDto {

    //상품 이름
    public String name;
    //수량
    public int quantity;
    //가격
    public int price;

    public Long userId;


    //OrderDto -> Order Entity로 변환하는 메서드
    public Order toEntity() {
        return Order.builder()
                .name(name)
                .quantity(quantity)
                .price(price)
                .build();
    }

}
