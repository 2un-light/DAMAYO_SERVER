package com.damayoproject.damayo.domain.user.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SignInDto {
    private String email;
    private String password;
}
