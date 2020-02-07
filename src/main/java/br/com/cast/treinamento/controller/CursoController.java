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

import br.com.cast.treinamento.model.dto.CursoDTO;
import br.com.cast.treinamento.model.dto.PaginacaoDTO;
import br.com.cast.treinamento.service.CursoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/curso")
public class CursoController {
	@Autowired
	private CursoService service;
	
	@ApiOperation(value = "Endpoint para consultar todos os Cursos")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "Authorization", value = "Token de acesso", required = true, paramType = "header", dataTypeClass = String.class) })
	@GetMapping 	
	public ResponseEntity<List<CursoDTO>> get() {
		return ResponseEntity.ok(service.consultarTodos());
	}
	
	@ApiOperation(value = "Endpoint para consultar todos os Cursos, de forma paginada")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "Authorization", value = "Token de acesso", required = true, paramType = "header", dataTypeClass = String.class) })
	@PostMapping(value = "/consultar-paginado")
	public ResponseEntity<Page<CursoDTO>> consultarPaginado(@RequestBody PaginacaoDTO<CursoDTO> paginacao) {
		return ResponseEntity.ok(service.consultarPaginado(paginacao));
	}

	@ApiOperation(value = "Endpoint para consultar por um Id específico de Curso")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "Authorization", value = "Token de acesso", required = true, paramType = "header", dataTypeClass = String.class) })
	@GetMapping(value = "/{id}")
	public ResponseEntity<CursoDTO> getById(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok((service.consultarPorId(id)));
	}
	
	@ApiOperation(value = "Endpoint para criar um Curso")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "Authorization", value = "Token de acesso", required = true, paramType = "header", dataTypeClass = String.class) })
	@PostMapping
	public ResponseEntity<CursoDTO> post(@RequestBody CursoDTO input){
		return ResponseEntity.ok(service.cadastrar(input));
	}
	
	@ApiOperation(value = "Endpoint para alterar um Curso")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "Authorization", value = "Token de acesso", required = true, paramType = "header", dataTypeClass = String.class) })
	@PutMapping
	public ResponseEntity<CursoDTO> put(@RequestBody CursoDTO input){
		return ResponseEntity.ok(service.alterar(input));
	}
	
	@ApiOperation(value = "Endpoint para deletar um Curso")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "Authorization", value = "Token de acesso", required = true, paramType = "header", dataTypeClass = String.class) })
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable(name = "id") Long id) {
		service.deletar(id);
	}
}
