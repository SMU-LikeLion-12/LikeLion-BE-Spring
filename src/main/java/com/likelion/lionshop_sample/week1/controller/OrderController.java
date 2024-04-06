package com.likelion.lionshop_sample.week1.controller;

import com.likelion.lionshop_sample.week1.dto.CreateOrderRequestDto;
import com.likelion.lionshop_sample.week1.dto.UpdateOrderRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/order") // uri가 /order로 시작하는 요청을 받습니다.
public class OrderController {

    @PostMapping("")
    public String createOrder(@RequestBody CreateOrderRequestDto orders) {
        log.info(" [ Order Controller ] Order Name ---> {}" ,orders.getName());
        log.info(" [ Order Controller ] Order Quantity ---> {}" ,orders.getQuantity());
        log.info(" [ Order Controller ] Order Price ---> {}" ,orders.getPrice());
        return "주문 생성";
    }


    @GetMapping("/{orderId}")
    public String getOrder(@PathVariable String orderId) {
        log.info(" [ Order Controller ] Order Id ---> {}" , orderId);
        return "주문 가져오기";
    }

    @PutMapping("")
    public String updateOrder(@RequestBody UpdateOrderRequestDto updateOrderRequestDto) {
        log.info(" [ Order Controller ] Order Id ---> {}" , updateOrderRequestDto.getId());
        log.info(" [ Order Controller ] Order Name ---> {}" , updateOrderRequestDto.getName());
        log.info(" [ Order Controller ] Order Quantity ---> {}" , updateOrderRequestDto.getQuantity());
        log.info(" [ Order Controller ] Order Price  ---> {}" , updateOrderRequestDto.getPrice());
        return "주문 수정";
    }

    @DeleteMapping("")
    public String deleteOrder(@RequestParam("id") Long orderId) {
        log.info(" [ Order Controller ] Order Id ---> {}" , orderId);
        return "주문 삭제";
    }
}
