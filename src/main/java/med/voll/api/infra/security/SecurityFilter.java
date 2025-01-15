package med.voll.api.infra.security;

import java.io.IOException;

import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import med.voll.api.domain.usuarios.UsuarioRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            var token = authHeader.replace("Bearer ", "");
            var nombreUsuario = tokenService.getSubject(token); // Obtener el username del token

            if (nombreUsuario != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                var usuario = usuarioRepository.findByLogin(nombreUsuario);

                if (usuario != null && tokenService.isTokenValid(token, (UserDetails) usuario)) { // Validar token y
                                                                                                  // usuario
                    var authentication = new UsernamePasswordAuthenticationToken(
                            usuario,
                            null,
                            ((AbstractAuthenticationToken) usuario).getAuthorities() // Obtener roles y permisos
                    );
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

}
