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
    public ResponseEntity<?> createOrder(@RequestBody List<CreateOrderRequestDto> orders) {
        orders.forEach(order -> {
            log.info("[ Order Controller ] 이름 ---> {}", order.getName());
            log.info("[ Order Controller ] 수량 ---> {}", order.getQuantity());
            log.info("[ Order Controller ] 가격 ---> {}", order.getPrice());
        });
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrder(@PathVariable Long orderId) {
        log.info("[ Order Controller ] id ---> {}", orderId);

        OrderResponseDto orders = orderService.getOrder(orderId);
        return ResponseEntity.ok(orders);
    }

    @PutMapping("")
    public String updateOrder(@RequestBody UpdateOrderRequestDto updateOrderRequestDto) {
        log.info("[ Order Controller ] id ---> {}", updateOrderRequestDto.getId());
        log.info("[ Order Controller ] 이름 ---> {}", updateOrderRequestDto.getName());
        log.info("[ Order Controller ] 가격 ---> {}", updateOrderRequestDto.getPrice());
        log.info("[ Order Controller ] 수량 ---> {}", updateOrderRequestDto.getQuantity());

        orderService.updateOrder(updateOrderRequestDto);
        return "주문 수정";
    }

    @DeleteMapping("")
    public String deleteOrder(@RequestParam("id") Long orderId) {
        log.info("[ Order Controller ] id ---> {}", orderId);

        orderService.deleteOrder(orderId);
        return "주문 삭제";
    }
}
