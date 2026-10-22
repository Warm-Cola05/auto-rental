package com.coder.rental.security;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Data
public class PasswordConfig {

    @Value("${encoder.ctype.strength}")
    private int strength;

    @Value("${encoder.ctype.secret}")
    private String secret;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // 1.创建基于你 application.yml 里实际强度的编码器
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(strength);

        // 2.让程序在启动时，用它自己亲手把明文 "admin" 加密一次，并打印出来！
        String realEncodedPassword = encoder.encode("admin");
        System.out.println("=================================================");
        System.out.println("正确的admin强散列密文如下");
        System.out.println(realEncodedPassword);
        System.out.println("=================================================");

        return encoder;
    }
}