package com.likelion.lionshop_sample.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
@Tag(name = "Test")
public class TestController {

    @Operation(method = "GET", summary = "테스트용 API", description = "Health Check 용")
    @GetMapping("/health")
    public String healthCheck() {
        return "서버가 건강합니다 ^_^";
    }


}
