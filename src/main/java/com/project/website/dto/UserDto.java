package com.project.website.dto;


import com.project.website.domain.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String userName;
    private String password;
    private String phoneNumber;
    private LocalDateTime insertTime;

    public UserEntity toEntity(){
        return UserEntity.builder()
                .id(id)
                .email(email)
                .userName(userName)
                .password(password)
                .phoneNumber(phoneNumber)
                .build();
    }

    @Builder
    public UserDto(Long id, String email, String userName, String password, String phoneNumber) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
}
