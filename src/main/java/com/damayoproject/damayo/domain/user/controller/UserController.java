package com.damayoproject.damayo.domain.user.controller;

import com.damayoproject.damayo.domain.user.dto.JoinDto;
import com.damayoproject.damayo.domain.user.dto.SignInDto;
import com.damayoproject.damayo.domain.user.service.UserService;
import com.damayoproject.damayo.global.config.security.JwtToken;
import com.damayoproject.damayo.global.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<ApiResponse<String>> join(@Valid @RequestBody JoinDto joinDto) {
            userService.join(joinDto);
            String responseMessage = "회원가입이 성공적으로 완료되었습니다.";
            ApiResponse<String> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), responseMessage, null);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<ApiResponse<JwtToken>> signIn(@Valid @RequestBody SignInDto signInDto){
        String email = signInDto.getEmail();
        String password = signInDto.getPassword();

        JwtToken jwtToken = userService.signIn(email, password);
        String responseMessage = "로그인이 성공적으로 완료되었습니다.";
        ApiResponse<JwtToken> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), responseMessage, jwtToken);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/test")
    public String test() {
        return "success";
    }
}