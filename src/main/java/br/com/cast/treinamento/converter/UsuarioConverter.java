package br.com.cast.treinamento.converter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.cast.treinamento.model.Usuario;
import br.com.cast.treinamento.model.dto.UsuarioDTO;

@Component
public class UsuarioConverter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6233861791353678694L;

	public List<UsuarioDTO> convertToDTO(List<Usuario> listaUsuario) {
		List<UsuarioDTO> listaUsuarioDTO = new ArrayList<>();
		for (Usuario usuario : listaUsuario) {
			listaUsuarioDTO.add(convertToDTO(usuario));
		}
		return listaUsuarioDTO;
	}

	public Usuario convertToEntity(UsuarioDTO usuarioDTO) {
		Usuario usuario = new Usuario();
		usuario.setId(usuarioDTO.getId());
		usuario.setEmail(usuarioDTO.getEmail());
		usuario.setNome(usuarioDTO.getNome());
		usuario.setSenha(usuarioDTO.getSenha());
		return usuario;
	}

	public UsuarioDTO convertToDTO(Usuario usuario) {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId(usuario.getId());
		usuarioDTO.setEmail(usuario.getEmail());
		usuarioDTO.setNome(usuario.getNome());
		usuarioDTO.setSenha(usuario.getSenha());
		return usuarioDTO;
	}

}
