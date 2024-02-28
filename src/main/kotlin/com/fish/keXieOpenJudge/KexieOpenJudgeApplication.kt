package com.fish.keXieOpenJudge

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@MapperScan("com.fish.keXieOpenJudge.mapper.*")
@EnableFeignClients
class KexieOpenJudgeApplication
fun main(args : Array<String>) {
    runApplication<KexieOpenJudgeApplication>(*args)
}
