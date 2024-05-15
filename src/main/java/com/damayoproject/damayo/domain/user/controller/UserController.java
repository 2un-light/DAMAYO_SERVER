package com.damayoproject.damayo.domain.user.controller;

import com.damayoproject.damayo.domain.user.dto.JoinDto;
import com.damayoproject.damayo.domain.user.dto.SignInDto;
import com.damayoproject.damayo.domain.user.service.UserService;
import com.damayoproject.damayo.global.config.security.JwtToken;
import com.damayoproject.damayo.global.response.ResponseDto;
import com.damayoproject.damayo.global.response.ResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseDto join(@RequestBody JoinDto joinDto){
        userService.join(joinDto);
        return ResponseUtil.SUCCESS("회원가입에 성공하였습니다.", null);
    }

    @PostMapping("/sign-in")
    public ResponseDto signIn(@RequestBody SignInDto signInDto){
        String email = signInDto.getEmail();
        String password = signInDto.getPassword();
        JwtToken jwtToken = userService.signIn(email, password);
        return ResponseUtil.SUCCESS("로그인 성공", jwtToken);
}

    @PostMapping("/test")
    public String test() {
        return "success";
    }
}
