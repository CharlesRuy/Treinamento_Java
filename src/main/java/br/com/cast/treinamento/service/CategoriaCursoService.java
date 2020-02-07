package br.com.cast.treinamento.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.cast.treinamento.converter.CategoriaCursoConverter;
import br.com.cast.treinamento.exceptions.BadRequestException;
import br.com.cast.treinamento.exceptions.NotFoundException;
import br.com.cast.treinamento.model.CategoriaCurso;
import br.com.cast.treinamento.model.dto.CategoriaCursoDTO;
import br.com.cast.treinamento.model.dto.PaginacaoDTO;
import br.com.cast.treinamento.repository.CategoriaCursoRepository;
import br.com.cast.treinamento.repository.CursoRepository;

@Service
public class CategoriaCursoService {
	
	@Autowired
	private CategoriaCursoConverter converter;
	
	@Autowired
	private CategoriaCursoRepository repository;
	
	@Autowired
	private CursoRepository cursoRepository;

	public List<CategoriaCursoDTO> consultarTodos() {
		List<CategoriaCurso> lista = repository.findAll();
		
		if(lista.isEmpty()) {
			throw new NotFoundException("Nenhum registro retornado!");
		}
		
		return converter.toEntityToDto(lista);
	}
	
	public Page<CategoriaCursoDTO> consultarPaginado(PaginacaoDTO<CategoriaCursoDTO> paginacao) {
		Pageable paging = PageRequest.of(paginacao.getNumeroPagina(), 
				paginacao.getQuantidadePorPagina());
		
		Page<CategoriaCurso> lista = repository.consultarPaginado(paginacao.getFilter(), paging);
		
		if(!lista.hasContent()) {
			throw new NotFoundException("Nenhum registro retornado!");
		}
		
		return new PageImpl<CategoriaCursoDTO>(converter.toEntityToDto(lista.getContent()));
	}
	
	public CategoriaCursoDTO consultarPorId(Long id) {
		Optional<CategoriaCurso> entity = repository.findById(id);
		
		if (!entity.isPresent())
			throw new BadRequestException("Não foi possível efetuar a busca. Esse registro não existe na base.");

		return converter.toEntityToDto(entity.get());
	}
	
	public CategoriaCursoDTO cadastrar(CategoriaCursoDTO input) {
		CategoriaCurso categoriaCursoNomeIgual = repository.findByDescricao(input.getDescricao());
		
		if (categoriaCursoNomeIgual != null) {
			throw new BadRequestException("Não foi possível cadastrar. Já existe um registro com este nome.");
		}
		
		CategoriaCurso entity = converter.toDtoToEntity(input);
		repository.save(entity);
		return converter.toEntityToDto(entity);
	}
	
	public CategoriaCursoDTO alterar(CategoriaCursoDTO input) {
		Optional<CategoriaCurso> categoriaEntity = repository.findById(input.getId());
		if (!categoriaEntity.isPresent())
			throw new BadRequestException("Não foi possível alterar. Esse registro não existe na base.");
		
		CategoriaCurso outraCategoriaCursoNomeIgual = repository.consultarCategoriaDiferenteComMesmaDescricao(input.getId(), input.getDescricao());
		if (outraCategoriaCursoNomeIgual != null)
			throw new BadRequestException("Não foi possível alterar. Já existe outro registro com este nome.");
		
		CategoriaCurso entity = converter.toDtoToEntity(input);
		repository.save(entity);
		return converter.toEntityToDto(entity);
	}

	public void deletar(Long id) {
		Optional<CategoriaCurso> categoriaEntity = repository.findById(id);
		if (!categoriaEntity.isPresent())
			throw new BadRequestException("Não foi possível excluir. Esse registro não existe na base.");
		
		boolean existeCursosComEstaCategoria = cursoRepository.existsByCategoriaCursoId(id);

		if (existeCursosComEstaCategoria)
			throw new BadRequestException("Não foi possível excluir. Existem cursos anexados a essa Categoria de Curso.");
		
		repository.deleteById(id);
	}
}
