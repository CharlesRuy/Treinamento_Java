package br.com.cast.treinamento.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_curso", schema = "treinamento")
public class Curso implements Serializable {
	private static final long serialVersionUID = -3481731965679349901L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "DS_CURSO")
	private String nome;
	
	@Column(name = "NR_DURACAO")
	private int duracao;
	
	@Column(name = "IN_PCD")
	private boolean pcd;
	
	@Column(name = "IN_EXCLUIDO")
	private boolean excluido;
	
	@ManyToOne()
	@JoinColumn(name = "ID_CATEGORIA_CURSO")
	private CategoriaCurso categoriaCurso;

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

	public CategoriaCurso getCategoriaCurso() {
		return categoriaCurso;
	}

	public void setCategoriaCurso(CategoriaCurso categoriaCurso) {
		this.categoriaCurso = categoriaCurso;
	}
	
	public boolean isExcluido() {
		return excluido;
	}

	public void setExcluido(boolean excluido) {
		this.excluido = excluido;
	}
}
