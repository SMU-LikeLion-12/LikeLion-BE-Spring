package com.likelion.lionshop_sample.service;

import com.likelion.lionshop_sample.dto.request.CreateUserRequestDto;
import com.likelion.lionshop_sample.dto.request.UpdateUserRequestDto;
import com.likelion.lionshop_sample.entity.User;
import com.likelion.lionshop_sample.dto.response.UserResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    public void createUser(CreateUserRequestDto createUserRequestDto) {
        log.info("[ User Service ] 사용자 생성 이름 ---> {}", createUserRequestDto.getName());
        User user = createUserRequestDto.toEntity();

        //DB 저장 로직이 들어갈 부분 (다음주차)
    }

    public UserResponseDto getUser(Long userId) {
        log.info("[ User Service ] 사용자 가져오기 ID ---> {}", userId);

        //DB에서 가져오는 로직이 들어갈 부분 (다음 주차)
        //(임시) User Entity
        User user = new User();

        return UserResponseDto.from(user);
    }

    public void updateUser(UpdateUserRequestDto userRequestDto) {
        log.info("[ User Service ] 사용자 수정 ID ---> {}", userRequestDto.getId());
        //DB에서 가져오는 로직이 들어갈 부분
        //(임시) User Entity
        User user = new User();

        //수정하는 로직이 들어갈 부분 (다음주차)
        user.update(userRequestDto);

        //DB 저장 로직이 들어갈 부분 (다음주차)
    }

    public void deleteUser(Long userId) {
        log.info("[ User Service ] 사용자 삭제 ID ---> {}", userId);
        //DB 삭제가 들어갈 부분 (다음주차)
    }
}
