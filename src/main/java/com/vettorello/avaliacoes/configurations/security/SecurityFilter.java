package com.vettorello.avaliacoes.configurations.security;


import com.vettorello.avaliacoes.entities.Usuario;
import com.vettorello.avaliacoes.repositories.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recoverToken(request);
        var login = tokenService.validarToken(token);

        if(login != null){
            Usuario usuario = usuarioRepository.findByEmail(login).orElseThrow(() ->  new RuntimeException("Usuário não encontrado"));
            var autorizacoes = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
            var autenticacoes = new UsernamePasswordAuthenticationToken(usuario, null, autorizacoes);
            SecurityContextHolder.getContext().setAuthentication(autenticacoes);
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        var authHeader = request.getHeader("Authorization");
        if(authHeader == null){
            return null;
        }

        return authHeader.replace("Bearer", "");
    }
}
