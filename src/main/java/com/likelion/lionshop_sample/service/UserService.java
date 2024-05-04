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
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    @Transactional
    public UserResponseDto createUser(CreateUserRequestDto createUserRequestDto) {
        User user = createUserRequestDto.toEntity();
        userRepository.save(user);
        return UserResponseDto.from(user);
    }
    public UserResponseDto getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("사용자가 존재히지 않습니다."));
        return UserResponseDto.from(user);
    }
    @Transactional
    public UserResponseDto updateUser(UpdateUserRequestDto userRequestDto) {
        Long userId = userRequestDto.getId();
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));
        user.update(userRequestDto);
        userRepository.save(user);
        return UserResponseDto.from(user);

    }
    @Transactional
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
