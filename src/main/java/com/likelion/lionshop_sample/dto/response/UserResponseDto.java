package com.likelion.lionshop_sample.dto.response;

import com.likelion.lionshop_sample.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder //Builder 패턴을 사용합니다.
@Getter
public class UserResponseDto {

    public Long id;

    public String name;

    public String address;

    //User Entity -> ResponseDto 변환 메서드
    public static UserResponseDto from(User user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .address(user.getAddress())
                .build();
    }
}
