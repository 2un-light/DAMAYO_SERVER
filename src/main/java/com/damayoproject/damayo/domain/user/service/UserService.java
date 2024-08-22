package com.damayoproject.damayo.domain.user.service;

import com.damayoproject.damayo.domain.user.dto.JoinDto;
import com.damayoproject.damayo.domain.user.entity.User;
import com.damayoproject.damayo.domain.user.repository.UserRepository;
import com.damayoproject.damayo.global.config.security.JwtToken;
import com.damayoproject.damayo.global.config.security.JwtTokenProvider;
import com.damayoproject.damayo.domain.user.exception.EmailAlreadyExistsException;
import com.damayoproject.damayo.domain.user.exception.EmailNotFoundException;
import com.damayoproject.damayo.domain.user.exception.PasswordMismatchException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long join(JoinDto joinDto){
        User user = User.builder()
                .name(joinDto.getName())
                .email(joinDto.getEmail())
                .password(passwordEncoder.encode(joinDto.getPassword()))
                .build();

        if(userRepository.existsByEmail(user.getEmail())){
            throw new EmailAlreadyExistsException("중복되는 이메일이 존재합니다.");
        }

        return userRepository.save(user).getId();
    }

    @Transactional
    public JwtToken signIn(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException("이메일을 다시 확인해주세요."));

        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        JwtToken jwtToken = jwtTokenProvider.accessToken(authentication);
        return jwtToken;
    }
}
