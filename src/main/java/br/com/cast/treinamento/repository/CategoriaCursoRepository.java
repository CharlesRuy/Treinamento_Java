package br.com.cast.treinamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cast.treinamento.model.CategoriaCurso;

@Repository
public interface CategoriaCursoRepository extends JpaRepository<CategoriaCurso, Long>, CategoriaCursoRepositoryCustom {

	@Query("SELECT c FROM CategoriaCurso c where descricao = :descricao AND id <> :id")
	CategoriaCurso consultarCategoriaDiferenteComMesmaDescricao(Long id, String descricao);

	CategoriaCurso findByDescricao(String descricao);
}
