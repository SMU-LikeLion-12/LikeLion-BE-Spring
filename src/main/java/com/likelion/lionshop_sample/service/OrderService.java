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
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    public List<OrderResponseDto> createOrder(List<CreateOrderRequestDto> createOrderRequestDto) {
        List<Order> createdOrders = new ArrayList<>();
        for (CreateOrderRequestDto requestDto : createOrderRequestDto) {
            String name = requestDto.getName();
            double price = requestDto.getPrice();
            int quantity = requestDto.getQuantity();

            Order savedOrder = orderRepository.save(requestDto.toEntity());
            Optional<User> userId = userRepository.findById(requestDto.getUserId());
            if(userId.isEmpty()) {
                throw new IllegalArgumentException(("회원이 존재하지 않습니다."));
            }
            User user = userId.get();
            savedOrder.specifyUser(user);
            createdOrders.add(savedOrder);
        }
        return OrderResponseDto.from(createdOrders);
    }

    @Transactional(readOnly = true)
    public OrderResponseDto getOrder(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isEmpty()) {
            throw new IllegalArgumentException("조회한 상품이 존재하지 않습니다.");
        }
        else {
            Order orders = order.get();
            return OrderResponseDto.from(orders);
        }
    }
    public void deleteOrder(Long orderId) {
        if(orderId == null) {
            throw new IllegalArgumentException("상품이 존재하지 않습니다.");
        }
        orderRepository.deleteById(orderId);
    }

    public OrderResponseDto updateOrder(UpdateOrderRequestDto updateOrderRequestDto) {
        Long orderId = updateOrderRequestDto.getId();
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isEmpty()) {
            throw new IllegalArgumentException("상품이 존재하지 않습니다.");
        }
        else {
            Order orders = order.get();
            orders.update(updateOrderRequestDto);
            orderRepository.save(orders);
            return OrderResponseDto.from(orders);
        }
    }
}