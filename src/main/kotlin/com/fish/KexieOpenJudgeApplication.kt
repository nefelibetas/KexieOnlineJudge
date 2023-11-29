package com.fish

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@MapperScan("com.fish.mapper")
class KexieOpenJudgeApplication
fun main(args : Array<String>) {
    runApplication<KexieOpenJudgeApplication>(*args)
}
