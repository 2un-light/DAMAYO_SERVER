package com.damayoproject.damayo.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SignInDto {

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 양식을 확인해주세요")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}
