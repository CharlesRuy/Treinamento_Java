package br.com.cast.treinamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cast.treinamento.model.CursoLog;

@Repository
public interface CursoLogRepository extends JpaRepository<CursoLog, Long> {

}
