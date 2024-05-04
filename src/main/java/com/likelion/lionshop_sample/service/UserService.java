package com.likelion.lionshop_sample.service;

import com.likelion.lionshop_sample.dto.request.CreateUserRequestDto;
import com.likelion.lionshop_sample.dto.request.UpdateUserRequestDto;
import com.likelion.lionshop_sample.entity.User;
import com.likelion.lionshop_sample.dto.response.UserResponseDto;
import com.likelion.lionshop_sample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDto createUser(CreateUserRequestDto createUserRequestDto) {
        User user = createUserRequestDto.toEntity();
        userRepository.save(user);
        return UserResponseDto.from(user);
    }
    @Transactional(readOnly = true)
    public UserResponseDto getUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) {
            throw new IllegalArgumentException("회원이 존재하지 않습니다.");
        }
        else {
            User users = user.get();
            return UserResponseDto.from(users);
        }
    }

    public UserResponseDto updateUser(UpdateUserRequestDto userRequestDto) {
        Long userId = userRequestDto.getId();
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) {
            throw new IllegalArgumentException("회원이 존재하지 않습니다.");
        }
        else {
            User users = user.get();
            users.update(userRequestDto);
            userRepository.save(users);
            return UserResponseDto.from(users);
        }
    }
    public void deleteUser(Long userId) {
        if(userId==null) {
            throw new IllegalArgumentException("회원이 존재하지 않습니다.");
        }
        userRepository.deleteById(userId);
    }
}
