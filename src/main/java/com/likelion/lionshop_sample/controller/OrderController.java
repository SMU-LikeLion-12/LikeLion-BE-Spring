package com.likelion.lionshop_sample.controller;

import com.likelion.lionshop_sample.dto.request.CreateOrderRequestDto;
import com.likelion.lionshop_sample.dto.response.OrderResponseDto;
import com.likelion.lionshop_sample.dto.request.UpdateOrderRequestDto;
import com.likelion.lionshop_sample.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/order") // uri가 /order로 시작하는 요청을 받습니다.
public class OrderController {

    private final OrderService orderService;

    @PostMapping("")
    public ResponseEntity<List<OrderResponseDto>> createOrder(@RequestBody List<CreateOrderRequestDto> orders) {
        List<OrderResponseDto> responseDto = orderService.createOrder(orders);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrder(@PathVariable Long orderId) {
        OrderResponseDto responseDto = orderService.getOrder(orderId);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("")
    public ResponseEntity<?> updateOrder(@RequestBody UpdateOrderRequestDto updateOrderRequestDto) {
        OrderResponseDto responseDto = orderService.updateOrder(updateOrderRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteOrder(@RequestParam("id") Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}
