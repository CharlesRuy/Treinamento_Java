package br.com.cast.treinamento.repository;

import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import br.com.cast.treinamento.model.CategoriaCurso;
import br.com.cast.treinamento.model.dto.CategoriaCursoDTO;

public class CategoriaCursoRepositoryCustomImpl implements CategoriaCursoRepositoryCustom{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<CategoriaCurso> consultarPaginado(CategoriaCursoDTO filter, Pageable pageable) {
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT cc FROM CategoriaCurso cc ");
		sb.append(" WHERE 1 = 1 ");
		
		if(!StringUtils.isEmpty(filter.getDescricao())) {
			sb.append(" AND cc.descricao like :descricao ");
			parametros.put("descricao", "%" + filter.getDescricao() + "%");
		}
		
		Query query = entityManager.createQuery(sb.toString());
		parametros.forEach(query::setParameter);
		query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
		query.setMaxResults(pageable.getPageSize());
		
		return new PageImpl<CategoriaCurso>(query.getResultList());		
	}
}
