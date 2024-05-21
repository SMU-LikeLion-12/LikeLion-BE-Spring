package com.likelion.lionshop_sample.entity;

import com.likelion.lionshop_sample.dto.request.CreateOrderRequestDto;
import com.likelion.lionshop_sample.dto.request.UpdateUserRequestDto;
import com.likelion.lionshop_sample.entity.common.BaseEntity;
import com.likelion.lionshop_sample.entity.enums.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String roles;

    @Column
    private UserStatus status;

    @Column
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> order;


    // Dto를 사용해서 update하는 메서드.
    // Entity에는 Setter를 사용하지 않는 것이 좋음.
    public void update(UpdateUserRequestDto userRequestDto) {
        name = userRequestDto.getName();
        address = userRequestDto.getAddress();
    }
}