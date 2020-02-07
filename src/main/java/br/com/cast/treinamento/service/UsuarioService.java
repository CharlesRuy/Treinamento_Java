package br.com.cast.treinamento.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cast.treinamento.converter.UsuarioConverter;
import br.com.cast.treinamento.model.Usuario;
import br.com.cast.treinamento.model.dto.UsuarioDTO;
import br.com.cast.treinamento.repository.UsuarioRepository;

@Service
public class UsuarioService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2533545504899648030L;

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private UsuarioConverter converter;

	public List<UsuarioDTO> consultarTodos() {
		List<Usuario> lista = repository.findAll();
		return converter.convertToDTO(lista);
	}

	public UsuarioDTO incluir(UsuarioDTO usuarioDTO) {
		Usuario usuario = converter.convertToEntity(usuarioDTO);
		repository.save(usuario);
		return converter.convertToDTO(usuario);

	}

}
