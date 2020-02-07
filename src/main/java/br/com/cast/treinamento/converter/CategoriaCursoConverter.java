package br.com.cast.treinamento.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.cast.treinamento.model.CategoriaCurso;
import br.com.cast.treinamento.model.dto.CategoriaCursoDTO;

@Component
public class CategoriaCursoConverter {
	public List<CategoriaCursoDTO> toEntityToDto(List<CategoriaCurso> lista){
		List<CategoriaCursoDTO> listaRetorno = new ArrayList<CategoriaCursoDTO>();
		lista.forEach(item -> {
			CategoriaCursoDTO dto = new CategoriaCursoDTO();
			dto.setId(item.getId());
			dto.setDescricao(item.getDescricao());
			listaRetorno.add(dto);
		});
		
		return listaRetorno;
	}
	
	public CategoriaCursoDTO toEntityToDto(CategoriaCurso item){
		CategoriaCursoDTO retorno = new CategoriaCursoDTO();
		retorno.setId(item.getId());
		retorno.setDescricao(item.getDescricao());
		
		return retorno;
	}
	
	public List<CategoriaCurso> toDtoToEntity(List<CategoriaCursoDTO> lista){
		List<CategoriaCurso> listaRetorno = new ArrayList<CategoriaCurso>();
		lista.forEach(item -> {
			CategoriaCurso entity = new CategoriaCurso();
			entity.setId(item.getId());
			entity.setDescricao(item.getDescricao());
			listaRetorno.add(entity);
		});
		
		return listaRetorno;
	}
	
	public CategoriaCurso toDtoToEntity(CategoriaCursoDTO item){
		CategoriaCurso retorno = new CategoriaCurso();
		retorno.setId(item.getId());
		retorno.setDescricao(item.getDescricao());
		
		return retorno;
	}
	
}
