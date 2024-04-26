package com.likelion.lionshop_sample.service;

import com.likelion.lionshop_sample.dto.request.CreateOrderRequestDto;
import com.likelion.lionshop_sample.dto.response.OrderResponseDto;
import com.likelion.lionshop_sample.dto.request.UpdateOrderRequestDto;
import com.likelion.lionshop_sample.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrderService {

    public void createOrder(List<CreateOrderRequestDto> createOrderRequestDto) {
        createOrderRequestDto.forEach(orderRequestDto -> {
            log.info("[ Order Service ] 주문 생성 이름 ---> {}", orderRequestDto.getName());

            //DTO를 Entity로 변환
            Order order = orderRequestDto.toEntity();

            //DB에 저장 로직이 들어갈 부분 (다음 주차)

        });
    }

    public OrderResponseDto getOrder(Long orderId) {
        log.info("[ Order Service ] 주문 가져오기 ID ---> {}", orderId);

        //DB에서 가져오는 로직이 들어갈 부분 (다음 주차)

        //(임시) Order Entity
        Order order = new Order();

        return OrderResponseDto.from(order);
    }

    public void deleteOrder(Long orderId) {
        log.info("[ Order Service ] 주문 삭제하기 ID ---> {}", orderId);

        //DB에서 삭제하는 로직이 들어갈 부분 (다음 주차)
    }

    public void updateOrder(UpdateOrderRequestDto updateOrderRequestDto) {
        log.info("[ Order Service ] 주문 수정하기 ID ---> {}", updateOrderRequestDto.getId());
        //DB에서 가져오는 로직이 들어갈 부분 (다음 주차)
        //임시 Order Entity
        Order order = new Order();

        //DB에서 수정하는 로직이 들어갈 부분
        order.update(updateOrderRequestDto);

        //DB에 저장 로직이 들어갈 부분 (다음 주차)
    }

}
