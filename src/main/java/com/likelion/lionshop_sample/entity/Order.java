package com.likelion.lionshop_sample.entity;

import com.likelion.lionshop_sample.dto.request.UpdateOrderRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private int quantity;

    @Column
    private int price;

    // Dto를 사용해서 update하는 메서드.
    // Entity에는 Setter를 사용하지 않는 것이 좋음.
    public void update(UpdateOrderRequestDto updateOrderRequestDto) {
        name = updateOrderRequestDto.getName();
        quantity = updateOrderRequestDto.getQuantity();
        price = updateOrderRequestDto.getPrice();
    }
}
