package com.likelion.lionshop_sample.week1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor //매개변수 없는 생성자를 생성해 줍니다.
@AllArgsConstructor //모든 매개변수를 받는 생성자를 생성해 줍니다.
@Getter
public class UpdateUserRequestDto {

    //업데이트 하려는 User의 id
    public Long id;

    public String name;

    public String address;
}
