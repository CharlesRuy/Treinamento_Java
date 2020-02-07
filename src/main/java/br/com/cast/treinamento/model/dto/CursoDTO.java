package br.com.cast.treinamento.model.dto;

import java.io.Serializable;

public class CursoDTO implements Serializable {
	private static final long serialVersionUID = 6135999033634436057L;

	private Long id;
	
	private String nome;
	
	private int duracao;
	
	private boolean pcd;
	
	private boolean excluido;
	
	private CategoriaCursoDTO categoriaCurso;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public boolean isPcd() {
		return pcd;
	}

	public void setPcd(boolean pcd) {
		this.pcd = pcd;
	}

	public boolean isExcluido() {
		return excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}

	public CategoriaCursoDTO getCategoriaCurso() {
		return categoriaCurso;
	}

	public void setCategoriaCurso(CategoriaCursoDTO categoriaCurso) {
		this.categoriaCurso = categoriaCurso;
	}
}
