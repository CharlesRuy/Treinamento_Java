package br.com.cast.treinamento.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.cast.treinamento.model.Usuario;
import br.com.cast.treinamento.repository.UsuarioRepository;


@Service
public class AuthUserService implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;

	/**
	 * Utilizado para autenticação de usuários por OAuht2
	 */
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Optional<Usuario> usuario = repository.findByEmail(login);
		if (usuario.isPresent()) {
			return new AuthUserDetail(usuario.get().getNome(), usuario.get().getSenha());
		}
		return new AuthUserDetail();
	}

}
