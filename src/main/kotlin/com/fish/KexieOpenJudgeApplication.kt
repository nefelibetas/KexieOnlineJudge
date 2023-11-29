package com.fish

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy

@SpringBootApplication
@MapperScan("com.fish.mapper")
class KexieOpenJudgeApplication
fun main(args : Array<String>) {
    runApplication<KexieOpenJudgeApplication>(*args)
}
