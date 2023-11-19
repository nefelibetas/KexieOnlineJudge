package com.fish.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;


@Component
@Data
public class JwtUtil {
    /**
     * <div>私钥: 生成签名的时候使用的秘钥secret，一般可以从本地配置文件中读取。</div>
     * 应该大于等于 256位(长度32及以上的字符串)，并且是随机的字符串
     */
    @Value("${jwt.secret}")
    private String SECRET;
    /**
     * 过期时间(单位:秒)
     */
    @Value("${jwt.expireTime}")
    private Long expireTime;
    /**
     * jwt签发者
     */
    private static final String ISS = "Fish";
    /**
     * jwt主题
     */
    private static final String SUBJECT = "KexieOpenJudgeAccount";
    /**
     * 加密算法
     */
    private static final SecureDigestAlgorithm<SecretKey, SecretKey> ALGORITHM = Jwts.SIG.HS256;
    public String createToken(String str) {
        String uuid = UUID.randomUUID().toString();
        Date exprireDate = Date.from(Instant.now().plusSeconds(expireTime));
        return Jwts.builder()
                .header()
                .add("typ", "JWT")
                .add("alg", "HS256")
                .and()
                .claim("userId", str)
                .id(uuid)
                .subject(SUBJECT)
                .issuedAt(exprireDate)
                .issuer(ISS)
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()), ALGORITHM)
                .compact();
    }
    private Jws<Claims> parseClaim(String token) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(SECRET.getBytes()))
                .build()
                .parseSignedClaims(token);
    }
    public JwsHeader parseHeader(String token) {
        return parseClaim(token).getHeader();
    }
    public Claims parsePayload(String token) {
        return parseClaim(token).getPayload();
    }
    public String getRedisKey(String token) {
        return parsePayload(token).get("userId", String.class);
    }
}

