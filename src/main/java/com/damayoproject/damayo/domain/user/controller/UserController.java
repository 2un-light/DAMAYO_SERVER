package com.damayoproject.damayo.domain.user.controller;

import com.damayoproject.damayo.domain.user.dto.JoinDto;
import com.damayoproject.damayo.domain.user.dto.SignInDto;
import com.damayoproject.damayo.domain.user.service.UserService;
import com.damayoproject.damayo.global.config.security.JwtToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public String join(@RequestBody JoinDto joinDto){
        userService.join(joinDto);
        return "회원가입 성공";
    }

    @PostMapping("/sign-in")
    public JwtToken signIn(@RequestBody SignInDto signInDto){
        String email = signInDto.getEmail();
        String password = signInDto.getPassword();
        JwtToken jwtToken = userService.signIn(email, password);
        return jwtToken;
}

    @PostMapping("/test")
    public String test() {
        return "success";
    }
}
