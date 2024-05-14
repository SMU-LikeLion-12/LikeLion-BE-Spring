package com.likelion.lionshop_sample.controller;

import com.likelion.lionshop_sample.dto.request.CreateUserRequestDto;
import com.likelion.lionshop_sample.dto.request.UpdateUserRequestDto;
import com.likelion.lionshop_sample.dto.response.UserResponseDto;
import com.likelion.lionshop_sample.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j //로그 출력을 도와주는 어노테이션
@RestController
@RequiredArgsConstructor
@RequestMapping("/user") // uri가 /user로 시작하는 요청을 받습니다.
@Tag(name = "사용자 API", description = "사용자 관련 API입니다.")
@CrossOrigin(originPatterns = "*")
public class UserController {

    private final UserService userService;

    /**
     * @param createUserRequestDto <br>
     * 새로운 사용자를 생성합니다. <br>
     * 어노테이션 @RequestBody를 통해 Body의 값을 매개변수로 받을 수 있습니다. <br>
     * 이 경우, Spring에서 Json 형식으로 된 내용을 Dto에 맞게 직렬화 하여 담아줍니다. <br>
     * Post Method일 경우 웬만하면 uri에 파라미터를 담지 않고 Body로 보내는게 좋습니다.
     *
     * @return ResponseEntity
     * SpringFramework에서 제공하는 HTTP 응답 클래스입니다. <br>
     * 여러 방식으로 Response Body, HTTP Status, Header등을 지정해서 Response를 보낼 수 있습니다.
     */
    @Operation(method = "POST", summary = "사용자 생성", description = "사용자를 생성합니다. 인가가 필요하지 않습니다.")
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequestDto createUserRequestDto) {
        UserResponseDto responseDto = userService.createUser(createUserRequestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    /**
     * 사용자를 조회합니다. <br>
     */
    @Operation(method = "GET", summary = "사용자 조회", description = "인가된 토큰을 이용해 사용자를 조회합니다.")
    @GetMapping("")
    public ResponseEntity<?> getUser(@AuthenticationPrincipal UserDetails userDetails) {

//        log.info("User Email ---> {}", userDetails.getUsername());
        return ResponseEntity.ok(userService.getUser(userDetails.getUsername()));
    }

    /**
     * @param userUpdateRequestDto <br>
     * 사용자의 내용을 수정합니다. <br>
     * 수정된 내용을 Body에 담아서 전송합니다. <br>
     */
    @Operation(method = "PUT", summary = "사용자 수정", description = "인가된 사용자의 정보를 수정합니다.")
    @PutMapping("")
    public ResponseEntity<?> updateUser(@AuthenticationPrincipal UserDetails userDetails, @RequestBody UpdateUserRequestDto userUpdateRequestDto) {
        UserResponseDto responseDto = userService.updateUser(userDetails.getUsername(), userUpdateRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * @param userId <br>
     * 사용자를 삭제합니다. <br>
     * 어노테이션 @RequestParam을 통해 uri 뒤 '?' 부터의 파라미터를 받을 수 있습니다. <br>
     * ex) localhost:8080/user?id = 3 에 Delete Method일 경우, userId 값으로 3이 전달됩니다. <br>
     * 파라미터가 여러개일 경우는 '&'로 구분합니다. <br>
     * ex) localhost:8080/user?id=3&query=delete
     */
    @Operation(method = "DELETE", summary = "사용자 삭제", description = "인가된 사용자를 삭제합니다.")
    @DeleteMapping("")
    public ResponseEntity<?> deleteUser(@AuthenticationPrincipal UserDetails userDetails) {
        userService.deleteUser(userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }


}
