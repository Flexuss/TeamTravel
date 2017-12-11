package ru.kpfu.itis.dmitryivanov.security;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;


public class JWTFilter extends GenericFilterBean {

    private JWTAuthenticationProvider authenticationManager;

    @Autowired
    public JWTFilter(JWTAuthenticationProvider authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void afterPropertiesSet() throws ServletException {
        Assert.notNull(authenticationManager);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        try {
            String stringToken = req.getHeader("Authorization");
            if (stringToken == null) {
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_OK, "Authorization header not found");
                throw new InsufficientAuthenticationException("Authorization header not found");
            }
            try {
                JWT jwt = JWTParser.parse(stringToken);
                JWTToken token = new JWTToken(jwt);
                Authentication auth = authenticationManager.authenticate(token);
                if (!auth.isAuthenticated()) {
                    ((HttpServletResponse) response).sendError(HttpServletResponse.SC_OK, "Wrong token");
                } else {
                    SecurityContextHolder.getContext().setAuthentication(auth);
                    chain.doFilter(request, response);
                }
            } catch (ParseException e) {
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_OK, "Wrong token");
//                ((HttpServletResponse) response).sendError(914, "Wrong token");
            }
        } catch (AuthenticationException e) {
            SecurityContextHolder.clearContext();
        }
    }
}
