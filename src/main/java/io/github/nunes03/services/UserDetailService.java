package io.github.nunes03.services;

import io.github.nunes03.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final var usuario = usuarioRepository
            .findByLogin(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));

        final var roles = usuario.getIsAdmin().equals(Boolean.TRUE)
            ? List.of("ADMIN", "USER")
            : List.of("USER");

        return User
            .builder()
            .username(usuario.getLogin())
            .password(usuario.getSenha())
            .roles(roles.toArray(new String[0]))
            .build();
    }
}
