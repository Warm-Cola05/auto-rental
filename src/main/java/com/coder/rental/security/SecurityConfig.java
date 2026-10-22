package com.coder.rental.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import jakarta.annotation.Resource;
import org.springframework.context.ApplicationContext;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity     // 开启 Spring Security 的 Web 安全防护
@EnableMethodSecurity  // 开启 Controller 方法上的权限注解支持
public class SecurityConfig {

    @Resource
    private LoginSucessHandler loginSucessHandler; // 认证成功处理器

    @Resource
    private LoginFailHandler loginFailHandler;     // 认证失败处理器

    @Resource
    private CustomerAccessDeniedHandler customerAccessDeniedHandler; // 访问拒绝处理器（403）

    @Resource
    private CustomerAnonymousEntryPoint customerAnonymousEntryPoint; // 匿名用户无权访问处理器（401）

    @Resource
    private CustomerUserDetailsService customerUserDetailsService;   // 自定义用户档案加载服务

    // 【修改点：引入 Spring 容器上下文，确保在真正需要时再动态去拿密码编码器】
    @Resource
    private ApplicationContext applicationContext;

    @Resource
    private VerifyTokenFilter verifyTokenFilter;

    /**
     * 核心安全过滤链配置
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {



        // 步骤一：关闭网页级防护，开启跨域配置
        http.csrf(csrf -> csrf.disable());
        http.cors(Customizer.withDefaults());

        // 步骤二：配置表单登录功能盒
        http.formLogin(form -> form
                .loginProcessingUrl("/rental/user/login")
                .successHandler(loginSucessHandler)
                .failureHandler(loginFailHandler)
        );

        // 步骤三：配置会话管理盒子（无状态）
        http.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        // 步骤四：配置门禁路由拦截规则
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/rental/user/login", "/rental/user/login/**").permitAll()
                .anyRequest().authenticated()
        );

        // 步骤五：配置安全异常捕获盒子
        http.exceptionHandling(exception -> exception
                .authenticationEntryPoint(customerAnonymousEntryPoint)
                .accessDeniedHandler(customerAccessDeniedHandler)
        );

        // 步骤六：手动创建认证提供者
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customerUserDetailsService);

        // 不在类级别硬注入，而是在系统运行时，动态去容器里捞取已经被老师 PasswordConfig 初始化好的 Bean！
        BCryptPasswordEncoder encoder = applicationContext.getBean(BCryptPasswordEncoder.class);
        authenticationProvider.setPasswordEncoder(encoder);

        http.authenticationProvider(authenticationProvider);


        http.addFilterBefore(verifyTokenFilter, UsernamePasswordAuthenticationFilter.class);

        // 最终构建并返回安全过滤链
        return http.build();
    }
}