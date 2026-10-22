package com.coder.rental.security;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.convert.NumberWithFormat;
import cn.hutool.core.date.DateTime;
import cn.hutool.jwt.JWTPayload;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.coder.rental.entity.User;
import com.coder.rental.utils.JwtUtils;
import com.coder.rental.utils.RedisUtils;
import com.coder.rental.utils.ResultCode;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoginSucessHandler implements AuthenticationSuccessHandler {

    @Resource
    private RedisUtils redisUtils;

    /**
     * 认证成功后的处理逻辑。
     * 当前用户成功登录后，该方法会被调用，用于生成并返回JSON格式认证结果，包含token等信息。
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        // 设置响应内容类型为JSON
        response.setContentType("application/json;charset=utf-8");

        // 从认证结果中获取用户对象
        User user = (User) authentication.getPrincipal();

        // 准备token相关的载荷并生成token
        Map<String, Object> map = new HashMap<>();
        map.put("username", user.getUsername());
        map.put("userId", user.getId());
        String token = JwtUtils.createToken(map);

        // 解析token获取过期时间，并构建认证结果对象
        NumberWithFormat claim = (NumberWithFormat) JwtUtils.parseToken(token).getClaim(JWTPayload.EXPIRES_AT);
        Long expireTime = Convert.toDate(claim).getTime();

        AuthenticationResult authenticationResult = new AuthenticationResult(
                user.getId(),
                ResultCode.SUCCESS,
                token,
                expireTime
        );

        // 将认证结果转换为JSON字符串
        String result = JSON.toJSONString(authenticationResult, SerializerFeature.DisableCircularReferenceDetect);

        // 向客户端发送认证结果
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(result.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();

        // 将token存入redis，设置过期时间
        String tokenKey = "token:" + token;
        long nowTime = DateTime.now().getTime();
        redisUtils.set(tokenKey, token, (expireTime - nowTime) / 1000);
    }
}