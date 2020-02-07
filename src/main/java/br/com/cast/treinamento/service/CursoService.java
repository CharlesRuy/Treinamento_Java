package br.com.cast.treinamento.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cast.treinamento.converter.CursoConverter;
import br.com.cast.treinamento.exceptions.BadRequestException;
import br.com.cast.treinamento.exceptions.NotFoundException;
import br.com.cast.treinamento.model.Curso;
import br.com.cast.treinamento.model.CursoLog;
import br.com.cast.treinamento.model.dto.CursoDTO;
import br.com.cast.treinamento.model.dto.PaginacaoDTO;
import br.com.cast.treinamento.repository.CursoLogRepository;
import br.com.cast.treinamento.repository.CursoRepository;

@Service
public class CursoService {
	@Autowired
	private CursoConverter converter;
	
	@Autowired
	private CursoRepository repository;
	
	@Autowired
	private CursoLogRepository logRepository;
	
	public List<CursoDTO> consultarTodos() {
		List<Curso> lista = repository.findAll();
		
		if(lista.isEmpty())
			throw new NotFoundException("Nenhum registro retornado!");
		
		return converter.toEntityToDto(lista);
	}
	
	public Page<CursoDTO> consultarPaginado(PaginacaoDTO<CursoDTO> paginacao) {
		Pageable paging = PageRequest.of(paginacao.getNumeroPagina(), 
				paginacao.getQuantidadePorPagina());
		
		Page<Curso> lista = repository.consultarPaginado(paginacao.getFilter(), paging);
		
		if(!lista.hasContent())
			throw new NotFoundException("Nenhum registro retornado!");
		
		return new PageImpl<CursoDTO>(converter.toEntityToDto(lista.getContent()));
	}
	
	public CursoDTO consultarPorId(Long id) {
		Optional<Curso> entity = repository.findById(id);
		
		if (!entity.isPresent())
			throw new BadRequestException("Não foi possível efetuar a busca. Esse registro não existe na base.");

		return converter.toEntityToDto(entity.get());
	}
	
	@Transactional
	public CursoDTO cadastrar(CursoDTO input) {

		Curso cursoNomeIgualECategoriaIgual = repository.consultarCursoComMesmoNomeECategoriaCurso(input.getNome(), input.getCategoriaCurso().getId());
		
		if (cursoNomeIgualECategoriaIgual != null) {
			throw new BadRequestException("Não foi possível cadastrar. Já existe um registro com este nome e com esta categoria simultaneamente.");
		}
		
		if (input.getDuracao() <= 0 || input.getDuracao() > 5) {
			throw new BadRequestException("Não foi possível cadastrar. O curso tem que estar entre 1 e 5 anos de duração.");
		}
		
		Curso entity = converter.toDtoToEntity(input);
		repository.save(entity);
		
		criarLogCurso(entity, "CADASTRO");
		
		return converter.toEntityToDto(entity);
	}
	
	@Transactional
	public CursoDTO alterar(CursoDTO input) {
		Optional<Curso> cursoEntity = repository.findById(input.getId());
		if (!cursoEntity.isPresent())
			throw new BadRequestException("Não foi possível alterar. Esse registro não existe na base.");
		
		Curso cursoDiferenteENomeIgualECategoriaIgual = repository.consultarCursoDiferenteComMesmoNomeECategoriaCurso(input.getId(), input.getNome(), input.getCategoriaCurso().getId());
		
		if (cursoDiferenteENomeIgualECategoriaIgual != null) {
			throw new BadRequestException("Não foi possível alterar. Já existe um registro diferente com este nome e com esta categoria simultaneamente.");
		}
		
		if (input.getDuracao() <= 0 || input.getDuracao() > 5) {
			throw new BadRequestException("Não foi possível alterar. O curso tem que estar entre 1 e 5 anos de duração.");
		}
		
		Curso entity = converter.toDtoToEntity(input);
		repository.save(entity);
		
		criarLogCurso(entity, "ALTERACAO");
		
		return converter.toEntityToDto(entity);
	}

	@Transactional
	public void deletar(Long id) {
		Optional<Curso> entity = repository.findById(id);
		if (!entity.isPresent())
			throw new BadRequestException("Não foi possível excluir. Esse registro não existe na base.");
		
		Curso curso = entity.get();
		curso.setExcluido(true);
		repository.save(curso);
		
		criarLogCurso(curso, "EXCLUSÃO LÓGICA");
	}
	
	private void criarLogCurso(Curso entity, String acao) {
		CursoLog logEntity = new CursoLog();
		logEntity.setNomeUsuario("Charles Ruy");
		logEntity.setDataHoraLog(new Date());
		logEntity.setCurso(entity);
		logEntity.setAcao(acao);
		logRepository.save(logEntity);
	}
}
