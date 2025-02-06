package br.com.luger.dev.precad.config;


import br.com.luger.dev.precad.repository.UserModelRepository;
import br.com.luger.dev.precad.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;


    private final UserModelRepository userModelRepository;


    @Autowired
    public SecurityFilter(TokenService tokenService, UserModelRepository userModelRepository) {
        this.tokenService = tokenService;
        this.userModelRepository = userModelRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        System.out.println("doFilterInternal passou 00");
        var authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println("doFilterInternal passou 01");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            if (authorizationHeader == null) {
                System.out.println("doFilterInternal invalid authorization header is" + null);
            }
            System.out.println("doFilterInternal passou error 01");
            filterChain.doFilter(request, response);
            return;
        }
        System.out.println("doFilterInternal line 46");
        final String token = authorizationHeader.split(" ")[1].trim();

        String subject = this.tokenService.getSubject(token);
        var user = this.userModelRepository.findByEmail(subject);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,
                null, user.getAuthorities());

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
        System.out.println("doFilterInternal line 62");
    }
}

