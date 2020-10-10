package com.freddy.kulachat.core.auth.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.freddy.kulachat.core.config.NetworkConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * @author FreddyChen
 * @name JWT工具类
 * @date 2020/09/25 12:02
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 */
public class JWTUtil {

    // todo 不使用JWT的过期机制，直接利用Redis的过期机制即可
    public static final long EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000L;// token过期时间，单位：毫秒
//    public static final long EXPIRE_TIME = 60 * 1000L;// token过期时间，单位：毫秒
    private static final String TOKEN_SECRET = "kulachattokensecret";// JWT私钥

    /**
     * 生成token，并把userId和phone存入token中
     * @param userId
     * @param phone
     * @return
     */
    public static String sign(Long userId, String phone) {
        try {
//            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            Map<String, Object> header = new HashMap<>(2);
            header.put("alg", "HS256");
            header.put("typ", "JWT");

            return JWT.create()
                    .withHeader(header)
                    .withClaim(NetworkConfig.PARAM_USER_ID, userId)
                    .withClaim(NetworkConfig.PARAM_USER_PHONE, phone)
//                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 验证token
     * @param token
     * @return
     */
    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 从token里取出userId
     * @param token
     * @return
     */
    public static Long getUserId(String token) {
        try {
            return JWT.decode(token).getClaim(NetworkConfig.PARAM_USER_ID).asLong();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 从token里取出phone
     * @param token
     * @return
     */
    public static String getPhone(String token) {
        try {
            return JWT.decode(token).getClaim(NetworkConfig.PARAM_USER_PHONE).asString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
