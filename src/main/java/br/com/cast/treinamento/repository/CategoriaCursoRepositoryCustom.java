package br.com.cast.treinamento.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.cast.treinamento.model.CategoriaCurso;
import br.com.cast.treinamento.model.dto.CategoriaCursoDTO;
	
public interface CategoriaCursoRepositoryCustom {

	Page<CategoriaCurso> consultarPaginado(CategoriaCursoDTO filter, Pageable pageable);
}
