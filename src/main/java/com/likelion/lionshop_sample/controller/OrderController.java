package com.likelion.lionshop_sample.controller;

import com.likelion.lionshop_sample.dto.request.CreateOrderRequestDto;
import com.likelion.lionshop_sample.dto.response.OrderResponseDto;
import com.likelion.lionshop_sample.dto.request.UpdateOrderRequestDto;
import com.likelion.lionshop_sample.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/order") // uri가 /order로 시작하는 요청을 받습니다.
@Tag(name = "주문 API", description = "주문 관련 API입니다.")
@CrossOrigin(originPatterns = "*")
public class OrderController {

    private final OrderService orderService;

    @Operation(method = "POST", summary = "주문 생성", description = "주문을 생성합니다. 생성할 주문 List를 Body에 담아서 전송합니다.")
    @PostMapping("")
    public ResponseEntity<List<OrderResponseDto>> createOrder(@AuthenticationPrincipal UserDetails userDetails,  @RequestBody List<CreateOrderRequestDto> orders) {
        List<OrderResponseDto> responseDto = orderService.createOrder(userDetails.getUsername(), orders);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @Operation(method = "GET", summary = "주문 조회", description = "Path variable로 order id 를 전달합니다.")
    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrder(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long orderId) {
        OrderResponseDto responseDto = orderService.getOrder(userDetails.getUsername(), orderId);
        return ResponseEntity.ok(responseDto);
    }

    @Operation(method = "GET", summary = "주문 리스트 조회", description = "요청한 사용자가 주문한 주문 리스트들을 조회합니다.")
    @GetMapping("/orders")
    public ResponseEntity<?> getOrders(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(orderService.getOrders(userDetails.getUsername()));
    }

    @Operation(method = "PUT", summary = "주문 수정", description = "주문을 수정합니다. 수정할 주문 id와 수정할 내역인 name, quantity, price를 입력하여 전송합니다." +
            "만약 수정할 권한이 없다면 403 Forbidden을 Return합니다.")
    @PutMapping("")
    public ResponseEntity<?> updateOrder(@AuthenticationPrincipal UserDetails userDetails, @RequestBody UpdateOrderRequestDto updateOrderRequestDto) {
        OrderResponseDto responseDto = orderService.updateOrder(updateOrderRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @Operation(method = "DELETE", summary = "주문 삭제", description = "주문을 삭제합니다. RequestParam으로 삭제할 주문 id를 전송합니다.")
    @DeleteMapping("")
    public ResponseEntity<Void> deleteOrder(@AuthenticationPrincipal UserDetails userDetails, @RequestParam("id") Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }
}
