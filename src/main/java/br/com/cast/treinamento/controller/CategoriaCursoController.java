package br.com.cast.treinamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cast.treinamento.model.dto.CategoriaCursoDTO;
import br.com.cast.treinamento.model.dto.PaginacaoDTO;
import br.com.cast.treinamento.service.CategoriaCursoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@RestController
@RequestMapping(value = "/categoria-curso")
public class CategoriaCursoController {
	@Autowired
	private CategoriaCursoService service;
	
	@ApiOperation(value = "Endpoint para consultar todas as Categorias de Curso")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "Authorization", value = "Token de acesso", required = true, paramType = "header", dataTypeClass = String.class) })
	@GetMapping 	
	public ResponseEntity<List<CategoriaCursoDTO>> get() {
		return ResponseEntity.ok(service.consultarTodos());
	}
	
	@ApiOperation(value = "Endpoint para consultar todas as Categorias de Curso, de forma paginada")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "Authorization", value = "Token de acesso", required = true, paramType = "header", dataTypeClass = String.class) })
	@PostMapping(value = "/consultar-paginado")
	public ResponseEntity<Page<CategoriaCursoDTO>> consultarPaginado(@RequestBody PaginacaoDTO<CategoriaCursoDTO> paginacao) {
		return ResponseEntity.ok(service.consultarPaginado(paginacao));
	}

	@ApiOperation(value = "Endpoint para consultar por um Id específico de Categoria de Curso")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "Authorization", value = "Token de acesso", required = true, paramType = "header", dataTypeClass = String.class) })
	@GetMapping(value = "/{id}")
	public ResponseEntity<CategoriaCursoDTO> getById(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok((service.consultarPorId(id)));
	}
	
	@ApiOperation(value = "Endpoint para criar uma Categoria de Curso")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "Authorization", value = "Token de acesso", required = true, paramType = "header", dataTypeClass = String.class) })
	@PostMapping
	public ResponseEntity<CategoriaCursoDTO> post(@RequestBody CategoriaCursoDTO input){
		return ResponseEntity.ok(service.cadastrar(input));
	}
	
	@ApiOperation(value = "Endpoint para alterar uma Categoria de Curso")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "Authorization", value = "Token de acesso", required = true, paramType = "header", dataTypeClass = String.class) })
	@PutMapping
	public ResponseEntity<CategoriaCursoDTO> put(@RequestBody CategoriaCursoDTO input){
		return ResponseEntity.ok(service.alterar(input));
	}
	
	@ApiOperation(value = "Endpoint para deletar uma Categoria de Curso")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "Authorization", value = "Token de acesso", required = true, paramType = "header", dataTypeClass = String.class) })
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable(name = "id") Long id) {
		service.deletar(id);
	}
}
