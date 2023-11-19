package com.fish;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class KexieOpenJudgeApplicationTests {
    @Resource
    private PasswordEncoder passwordEncoder;
    @Test
    void contextLoads() {
        System.out.println(passwordEncoder.encode("qx310320"));
    }
}
