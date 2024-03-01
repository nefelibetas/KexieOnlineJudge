package com.fish.keXieOnlineJudge

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@MapperScan("com.fish.keXieOnlineJudge.mapper.*")
@EnableFeignClients
class KexieOnlineJudgeApplication
fun main(args : Array<String>) {
    runApplication<KexieOnlineJudgeApplication>(*args)
}