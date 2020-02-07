package br.com.cast.treinamento.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.cast.treinamento.model.Curso;
import br.com.cast.treinamento.model.dto.CursoDTO;

public interface CursoRepositoryCustom {
	Page<Curso> consultarPaginado(CursoDTO filter, Pageable pageable);
}
