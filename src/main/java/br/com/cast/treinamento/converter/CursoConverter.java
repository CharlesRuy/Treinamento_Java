package br.com.cast.treinamento.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cast.treinamento.model.Curso;
import br.com.cast.treinamento.model.dto.CursoDTO;

@Component
public class CursoConverter {
	
	@Autowired
	private CategoriaCursoConverter converterCategoriaCurso;
	
	public List<CursoDTO> toEntityToDto(List<Curso> lista){
		List<CursoDTO> listaRetorno = new ArrayList<CursoDTO>();
		lista.forEach(item -> {
			CursoDTO dto = new CursoDTO();
			dto.setId(item.getId());
			dto.setNome(item.getNome());
			dto.setDuracao(item.getDuracao());
			dto.setPcd(item.isPcd());
			dto.setExcluido(item.isExcluido());
			
			dto.setCategoriaCurso(converterCategoriaCurso.toEntityToDto(item.getCategoriaCurso()));
			
			listaRetorno.add(dto);
		});
		
		return listaRetorno;
	}
	
	public CursoDTO toEntityToDto(Curso item){
		CursoDTO retorno = new CursoDTO();
		retorno.setId(item.getId());
		retorno.setNome(item.getNome());
		retorno.setDuracao(item.getDuracao());
		retorno.setPcd(item.isPcd());
		retorno.setExcluido(item.isExcluido());
		
		retorno.setCategoriaCurso(converterCategoriaCurso.toEntityToDto(item.getCategoriaCurso()));
		
		return retorno;
	}
	
	public List<Curso> toDtoToEntity(List<CursoDTO> lista){
		List<Curso> listaRetorno = new ArrayList<Curso>();
		lista.forEach(item -> {
			Curso entity = new Curso();
			entity.setId(item.getId());
			entity.setNome(item.getNome());
			entity.setDuracao(item.getDuracao());
			entity.setPcd(item.isPcd());
			entity.setExcluido(item.isExcluido());
			
			entity.setCategoriaCurso(converterCategoriaCurso.toDtoToEntity(item.getCategoriaCurso()));
			
			listaRetorno.add(entity);
		});
		
		return listaRetorno;
	}
	
	public Curso toDtoToEntity(CursoDTO item){
		Curso retorno = new Curso();
		retorno.setId(item.getId());
		retorno.setNome(item.getNome());
		retorno.setDuracao(item.getDuracao());
		retorno.setPcd(item.isPcd());
		retorno.setExcluido(item.isExcluido());
		
		retorno.setCategoriaCurso(converterCategoriaCurso.toDtoToEntity(item.getCategoriaCurso()));
		
		return retorno;
	}
}
