package com.demo.entregas.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String PREFIX_BEARER = "Bearer ";

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            String jwt = extrairJwt(request);
            if (StringUtils.hasText(jwt) && jwtService.tokenValido(jwt)) {
                String email = jwtService.extrairEmail(jwt);
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } catch (Exception e) {
            // mantém contexto vazio em caso de token inválido
        }
        filterChain.doFilter(request, response);
    }

    private String extrairJwt(HttpServletRequest request) {
        String bearer = request.getHeader(HEADER_AUTHORIZATION);
        if (StringUtils.hasText(bearer) && bearer.startsWith(PREFIX_BEARER)) {
            return bearer.substring(PREFIX_BEARER.length());
        }
        return null;
    }
}
