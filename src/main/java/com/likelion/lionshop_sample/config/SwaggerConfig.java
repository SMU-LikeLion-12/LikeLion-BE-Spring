package com.likelion.lionshop_sample.config;

import com.likelion.lionshop_sample.global.filter.CustomLoginFilter;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    // http://localhost:8080/swagger-ui/index.html#/

    @Bean
    public OpenAPI lionshopAPI() {
        Info info = new Info()
                .title("Lion Shop API Docs")
                .description("멋쟁이 사자처럼 12기 테스트용 API 입니다. 문의는 백엔드 스프링 팀장에게 해주세요.\n" +
                        "\n" +
                        "[ 필독 ]\n" +
                        "- 인가 정보가 정확하지 않으면 403 Fobidden을 Return합니다.\n" +
                        "- 잘못된 값을 전달하더라도 403 Forbidden을 Return합니다.\n" +
                        "- 사용자 생성, 로그인, 테스트 API를 제외한 모든 API는 로그인이 필요합니다.\n" +
                        "- 오른쪽 Authorize를 눌러 Access Token을 넣으면 자동으로 인가 할 수 있습니다. \n" +
                        "- 프론트 파이팅")
                .version("1.0.0");

        String jwtSchemeName = "accessToken";
        // API 요청헤더에 인증정보 포함
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);

        // SecuritySchemes 등록
        Components components = new Components()
                .addSecuritySchemes(jwtSchemeName, new SecurityScheme()
                        .name(jwtSchemeName)
                        .type(SecurityScheme.Type.HTTP) // HTTP 방식
                        .scheme("bearer")
                        .bearerFormat("JWT"));

        return new OpenAPI()
                .addServersItem(new Server().url("/"))
                .info(info)
                .addSecurityItem(securityRequirement)
                .components(components);
    }
}
