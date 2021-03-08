package com.cqut.why.authoringsystem.authoringsystem.config.util;

import com.alibaba.fastjson.JSONArray;
import com.cqut.why.authoringsystem.authoringsystem.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JwtUtil {


    public static final String TOKEN_HEADER = "token";
    public static final String TOKEN_PREFIX = "Bearer ";

    private static final String SUBJECT = "mayikt";

    private static final long EXPIRITION = 1000 * 24 * 60 * 60 * 7;

    private static final String APPSECRET_KEY = "mayikt_secret";

    private static final String ROLE_CLAIMS = "roles";

    public static String generateJsonWebToken(User user) {
        String token = Jwts
                .builder()
                .setSubject(SUBJECT)
                .claim(ROLE_CLAIMS, user.getAuthorities())
                .claim("username", user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRITION))
                .signWith(SignatureAlgorithm.HS256,APPSECRET_KEY).compact();

        return token;
    }



    public static Claims checkJWT(String token) {
        try {
            final Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
            return claims;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取用户名
     *
     * @param token
     * @return
     */
    public static String getUsername(String token) {
        Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
        return claims.get("username").toString();
    }





    /**
     * 获取用户角色
     *
     * @param token
     * @return
     */
    public static List<SimpleGrantedAuthority> getUserRole(String token) {
        Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
        List roles = (List) claims.get(ROLE_CLAIMS);
        String json = JSONArray.toJSONString(roles);
        List<SimpleGrantedAuthority>
                grantedAuthorityList =
                JSONArray.parseArray(json, SimpleGrantedAuthority.class);

        return grantedAuthorityList;
    }

    /**
     * 是否过期
     *
     * @param token
     * @return
     */
    public static boolean isExpiration(String token) {
        Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
        return claims.getExpiration().before(new Date());
    }

}
