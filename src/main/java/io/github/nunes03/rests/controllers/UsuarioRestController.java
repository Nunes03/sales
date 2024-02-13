package io.github.nunes03.rests.controllers;

import io.github.nunes03.entities.Usuario;
import io.github.nunes03.services.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UsuarioRestController {

    private final UserDetailService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    @PostMapping()
    @ResponseStatus(value = HttpStatus.CREATED)
    public Usuario create(@RequestBody @Valid Usuario usuario) {
        final var senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        return userDetailsService.create(usuario);
    }
}
