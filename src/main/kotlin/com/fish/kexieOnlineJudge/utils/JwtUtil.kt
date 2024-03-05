package com.fish.kexieOnlineJudge.utils

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.JwsHeader
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SecureDigestAlgorithm
import lombok.Data
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.*
import javax.crypto.SecretKey

@Component
@Data
class JwtUtil {
    /**
     * 私钥: 生成签名的时候使用的秘钥secret，一般可以从本地配置文件中读取。
     * 应该大于等于 256位(长度32及以上的字符串)，并且是随机的字符串
     */
    @Value("\${jwt.secret}")
    val secret: String? = null
    /**
     * 过期时间(单位:秒)
     */
    @Value("\${jwt.expireTime}")
    val expireTime: Long? = null
    fun createToken(str: String?): String {
        val uuid = UUID.randomUUID().toString()
        val expireDate = Date.from(Instant.now().plusSeconds(expireTime!!))
        return Jwts.builder()
            .header()
            .add("typ", "JWT")
            .add("alg", "HS256")
            .and()
            .claim("userId", str)
            .id(uuid)
            .subject(com.fish.kexieOnlineJudge.utils.JwtUtil.Companion.SUBJECT)
            .issuedAt(expireDate)
            .issuer(com.fish.kexieOnlineJudge.utils.JwtUtil.Companion.ISS)
            .signWith(Keys.hmacShaKeyFor(secret!!.toByteArray()),
                com.fish.kexieOnlineJudge.utils.JwtUtil.Companion.ALGORITHM
            )
            .compact();
    }

    private fun parseClaim(token: String): Jws<Claims> {
        return Jwts.parser()
            .verifyWith(Keys.hmacShaKeyFor(secret!!.toByteArray()))
            .build()
            .parseSignedClaims(token)
    }

    fun parseHeader(token: String): JwsHeader {
        return parseClaim(token).header
    }

    private fun parsePayload(token: String): Claims {
        return parseClaim(token).payload
    }

    fun getRedisKey(token: String): String {
        return parsePayload(token).get("userId", String::class.java)
    }
    companion object {
        /**
         * jwt签发者
         */
        const val ISS = "Fish"
        /**
         * jwt主题
         */
        const val SUBJECT = "KexieOnlineJudgeAccount"
        /**
         * 加密算法
         */
        val ALGORITHM: SecureDigestAlgorithm<SecretKey, SecretKey> = Jwts.SIG.HS256
    }
}
