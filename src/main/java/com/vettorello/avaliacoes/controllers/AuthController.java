package com.vettorello.avaliacoes.controllers;


import com.vettorello.avaliacoes.configurations.security.TokenService;
import com.vettorello.avaliacoes.dtos.LoginRequestDTO;
import com.vettorello.avaliacoes.dtos.RegistroRequestDTO;
import com.vettorello.avaliacoes.dtos.ResponseLoginDTO;
import com.vettorello.avaliacoes.entities.Usuario;
import com.vettorello.avaliacoes.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final TokenService tokenService;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body){
        Usuario usuario = this.usuarioRepository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        if(passwordEncoder.matches(body.senha(), usuario.getSenha())){
            String token = this.tokenService.gerarToken(usuario);

            return ResponseEntity.ok(new ResponseLoginDTO(usuario.getNome(), token));
        }

        return ResponseEntity.badRequest().build();
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegistroRequestDTO body){
        Optional<Usuario> usuario = this.usuarioRepository.findByEmail(body.email());

        if(usuario.isEmpty()){
            Usuario novoUsuario = new Usuario();
            novoUsuario.setSenha(passwordEncoder.encode(body.senha()));
            novoUsuario.setEmail(body.email());
            novoUsuario.setNome(body.nome());
            novoUsuario.setCargo(body.cargo());
            usuarioRepository.save(novoUsuario);

           String token = this.tokenService.gerarToken(novoUsuario);
           return ResponseEntity.ok(new ResponseLoginDTO(novoUsuario.getEmail(), token));
        }

        return ResponseEntity.badRequest().build();
    }
}
