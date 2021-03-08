package com.cqut.why.authoringsystem.authoringsystem.config.securityConfig;

import com.cqut.why.authoringsystem.authoringsystem.dao.PermissionMapper;
import com.cqut.why.authoringsystem.authoringsystem.entity.Permission;
import com.cqut.why.authoringsystem.authoringsystem.config.filter.JWTAuthorizationFilter;
import com.cqut.why.authoringsystem.authoringsystem.config.filter.JWTLoginFilter;
import com.cqut.why.authoringsystem.authoringsystem.config.util.MD5Util;
import com.cqut.why.authoringsystem.authoringsystem.config.service.MemberUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Component
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MemberUserDetailsService memberUserDetailsService;
    @Autowired
    PermissionMapper permissionMapper;

    @Value("${cros.allowedOrigin:*}")
    private String allowedOrigin;
    /**
     * 添加授权账户
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(memberUserDetailsService).passwordEncoder(new PasswordEncoder() {
            /**
             * 对密码MD5
             * @param rawPassword
             * @return
             */
            @Override
            public String encode(CharSequence rawPassword) {
                return MD5Util.encode((String) rawPassword);
            }

            /**
             * rawPassword 用户输入的密码
             * encodedPassword 数据库DB的密码
             * @param rawPassword
             * @param encodedPassword
             * @return
             */
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                String rawPass = MD5Util.encode((String) rawPassword);
                boolean result = rawPass.equals(encodedPassword);
                return result;
            }
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        List<Permission> allPermission = permissionMapper.findAllPermission();
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry
                expressionInterceptUrlRegistry = http.authorizeRequests();

        allPermission.forEach((permission) -> {
            expressionInterceptUrlRegistry.antMatchers(permission.getUrl()).
                    hasAnyAuthority(permission.getTag());
        });

        expressionInterceptUrlRegistry
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/swagger*/**", "/v2/api-docs", "/webjars/springfox*/**").permitAll()
                .antMatchers("/**").permitAll()
                .and()
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                .addFilter(new JWTLoginFilter(authenticationManager())).csrf().disable()

                // 不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }


    @Bean
    CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOriginPattern(allowedOrigin); // 1    设置访问源地址  //修复2.4.0之后Api问题
        corsConfiguration.addAllowedHeader("*"); // 2 设置访问源请求头
        corsConfiguration.addAllowedMethod("*"); // 3 设置访问源请求方法
        corsConfiguration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration); // 4 对接口配置跨域设置
        return new CorsFilter(source);
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> userInsertingMdcFilterRegistrationBean(CorsFilter corsFilter) {
        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(corsFilter);
        registrationBean.setOrder(-101);
        return registrationBean;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
