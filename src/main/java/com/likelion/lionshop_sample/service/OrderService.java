package com.likelion.lionshop_sample.service;

import com.likelion.lionshop_sample.dto.request.CreateOrderRequestDto;
import com.likelion.lionshop_sample.dto.response.OrderResponseDto;
import com.likelion.lionshop_sample.dto.request.UpdateOrderRequestDto;
import com.likelion.lionshop_sample.dto.response.UserResponseDto;
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
    public List<OrderResponseDto> createOrder(String email, List<CreateOrderRequestDto> createOrderRequestDto) {
        List<Order> createdOrders = new ArrayList<>();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));
        for (CreateOrderRequestDto requestDto : createOrderRequestDto) {


            String name = requestDto.getName();
            double price = requestDto.getPrice();
            int quantity = requestDto.getQuantity();
            Order order = requestDto.toEntity();
            order.setUser(user);
            Order savedOrder = orderRepository.save(order);
            createdOrders.add(savedOrder);
        }
        return OrderResponseDto.from(createdOrders);
    }

    public OrderResponseDto getOrder(String email, Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()-> new IllegalArgumentException("상품이 존재하지 않습니다."));

        if (!order.getUser().getEmail().equals(email)) {
            throw new SecurityException("권한이 없습니다.");
        }

        return OrderResponseDto.from(order);

    }

    public List<OrderResponseDto> getOrders(String email) {
        return orderRepository.findAllByUser_Email(email).stream()
                .map(OrderResponseDto::from)
                .toList();
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