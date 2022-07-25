package com.gl.test.config;

import com.gl.test.exception.UserManagementException;
import com.gl.test.service.JwtService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.gl.test.utils.ErrorMessageUtil.EMPTY_JWT_ERR_DESC;

@Component
@NoArgsConstructor
public class AuthTokenFilter extends OncePerRequestFilter {

    private static final String BEARER = "Bearer ";
    private static final String AUTHENTICATED_PATH = "/v1/account/login";
    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            String jwt = extractJwt(request);
            String email = jwtService.getEmailFromJwt(jwt);
            UserDetails user = userDetailsService.loadUserByUsername(email);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    user, null,user.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request,response);
        }catch (Exception ex) {
            resolver.resolveException(request, response, null, ex);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request){
        return !request.getRequestURI().matches(AUTHENTICATED_PATH);
    }

    private String extractJwt(HttpServletRequest request){
        String authorizationHeader = request.getHeader(AUTHORIZATION_HEADER);
        if(!authorizationHeader.startsWith(BEARER))
            throw new UserManagementException(HttpStatus.BAD_REQUEST, EMPTY_JWT_ERR_DESC);
        return authorizationHeader.substring(BEARER.length());
    }
}
