package br.com.cast.treinamento.repository;

import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import br.com.cast.treinamento.model.Curso;
import br.com.cast.treinamento.model.dto.CursoDTO;

public class CursoRepositoryCustomImpl implements CursoRepositoryCustom{
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<Curso> consultarPaginado(CursoDTO filter, Pageable pageable) {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT c FROM Curso c ");
		sb.append(" WHERE 1 = 1 ");
		
		if(!StringUtils.isEmpty(filter.getNome())) {
			sb.append(" AND c.nome like :nome ");
			parametros.put("nome", "%" + filter.getNome() + "%");
		}
		
		Query query = entityManager.createQuery(sb.toString());
		parametros.forEach(query::setParameter);
		query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
		query.setMaxResults(pageable.getPageSize());
		
		return new PageImpl<Curso>(query.getResultList());		
	}
}
