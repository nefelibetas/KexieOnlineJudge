package com.fish.keXieOnlineJudge.common

import jakarta.servlet.ReadListener
import jakarta.servlet.ServletInputStream
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletRequestWrapper
import java.io.BufferedReader
import java.io.ByteArrayInputStream
import java.io.InputStreamReader
/**
 * 一个RequestWrapper, 用于服务日志过滤器中构造可复用的流
 * @author fish
 */
class RepeatableReadRequestWrapper(request: HttpServletRequest): HttpServletRequestWrapper(request) {
    val body: ByteArray = request.inputStream.readAllBytes()
    override fun getReader(): BufferedReader {
        return BufferedReader(InputStreamReader(inputStream))
    }
    override fun getInputStream(): ServletInputStream {
        return object : ServletInputStream() {
            val byteArrayInputStream = ByteArrayInputStream(body)
            override fun read(): Int {
                return byteArrayInputStream.read()
            }
            override fun isFinished(): Boolean {
                return false
            }
            override fun isReady(): Boolean {
                return true
            }
            override fun setReadListener(listener: ReadListener?) {}
        }
    }

}