package com.likelion.lionshop_sample.entity;

import com.likelion.lionshop_sample.dto.request.UpdateUserRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String loginId;

    @Column
    private String password;

    // Dto를 사용해서 update하는 메서드.
    // Entity에는 Setter를 사용하지 않는 것이 좋음.
    public void update(UpdateUserRequestDto userRequestDto) {
        name = userRequestDto.getName();
        address = userRequestDto.getAddress();
    }
}
