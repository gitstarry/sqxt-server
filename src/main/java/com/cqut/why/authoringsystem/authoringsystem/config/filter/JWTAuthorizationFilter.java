package com.cqut.why.authoringsystem.authoringsystem.config.filter;

import com.cqut.why.authoringsystem.authoringsystem.config.util.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public static ThreadLocal<String> currentUsername = new ThreadLocal<>();

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String tokenHeader = request.getHeader("token");
        if (tokenHeader == null) {
            request.removeAttribute(getAlreadyFilteredAttributeName());
            chain.doFilter(request, response);
            return;
        }

        currentUsername.set(JwtUtil.getUsername(tokenHeader));

        // 如果请求头中有token，则进行解析，并且设置认证信息
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));

        super.doFilterInternal(request, response, chain);
    }

    // 这里从token中获取用户信息并新建一个token
    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) {

        String username = JwtUtil.getUsername(tokenHeader);
//        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        if (username != null) {
            List<SimpleGrantedAuthority> userRoles = JwtUtil.getUserRole(tokenHeader);
            return new UsernamePasswordAuthenticationToken(username, null, userRoles);
        }
        return null;
    }

    private String retrieveToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null)
            token = token.replace("Bearer ", "");
        else
            token = request.getParameter("token");
        return token;
    }

}
