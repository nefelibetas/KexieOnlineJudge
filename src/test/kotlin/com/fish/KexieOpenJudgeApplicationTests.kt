package com.fish

import jakarta.annotation.Resource
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootTest
internal class KexieOpenJudgeApplicationTests {
    @Resource
    private val passwordEncoder: PasswordEncoder? = null
    @Test
    fun contextLoads() {
        println(passwordEncoder!!.encode("qx310320"))
    }
}
