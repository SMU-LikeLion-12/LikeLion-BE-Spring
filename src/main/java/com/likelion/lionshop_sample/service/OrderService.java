package com.likelion.lionshop_sample.service;

import com.likelion.lionshop_sample.dto.request.CreateOrderRequestDto;
import com.likelion.lionshop_sample.dto.response.OrderResponseDto;
import com.likelion.lionshop_sample.dto.request.UpdateOrderRequestDto;
import com.likelion.lionshop_sample.entity.Order;
import com.likelion.lionshop_sample.entity.User;
import com.likelion.lionshop_sample.repository.OrderRepository;
import com.likelion.lionshop_sample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    @Transactional
    public List<OrderResponseDto> createOrder(List<CreateOrderRequestDto> createOrderRequestDto) {
        List<Order> createdOrders = new ArrayList<>();
        for (CreateOrderRequestDto requestDto : createOrderRequestDto) {
            String name = requestDto.getName();
            double price = requestDto.getPrice();
            int quantity = requestDto.getQuantity();
            User user = userRepository.findById(requestDto.getUser().getId()).orElseThrow(()-> new IllegalArgumentException("사용자가 존재하지 않습니다."));
            Order savedOrder = orderRepository.save(requestDto.toEntity());
            createdOrders.add(savedOrder);
        }
        return OrderResponseDto.from(createdOrders);
    }

    public OrderResponseDto getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()-> new IllegalArgumentException("상품이 존재하지 않습니다."));
        return OrderResponseDto.from(order);

    }
    @Transactional
    public void deleteOrder(Long orderId) {
        try {
            orderRepository.deleteById(orderId);

        } catch (IllegalArgumentException exception) {
            log.error("Invalid Parameter", exception);
        }

    }
    @Transactional
    public OrderResponseDto updateOrder(UpdateOrderRequestDto updateOrderRequestDto) {
        Long orderId = updateOrderRequestDto.getId();
        Order order = orderRepository.findById(orderId).orElseThrow(()-> new IllegalArgumentException("상품이 존재하지 않습니다."));
        order.update(updateOrderRequestDto);
        orderRepository.save(order);
        return OrderResponseDto.from(order);

    }
}