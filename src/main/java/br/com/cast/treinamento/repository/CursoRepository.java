package br.com.cast.treinamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.cast.treinamento.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long>, CursoRepositoryCustom {
	@Query("SELECT c "
			+ " FROM Curso c "
			+ " WHERE c.nome = :nome AND c.categoriaCurso.id = :categoriaCursoId ")
	Curso consultarCursoComMesmoNomeECategoriaCurso(String nome, Long categoriaCursoId);
	
	@Query("SELECT c "
			+ " FROM Curso c "
			+ " WHERE c.id <> :id AND c.nome = :nome AND c.categoriaCurso.id = :categoriaCursoId ")
	Curso consultarCursoDiferenteComMesmoNomeECategoriaCurso(Long id, String nome, Long categoriaCursoId);

	boolean existsByCategoriaCursoId(Long id);
}
