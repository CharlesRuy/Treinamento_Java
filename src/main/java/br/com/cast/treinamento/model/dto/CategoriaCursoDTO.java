package br.com.cast.treinamento.model.dto;

import java.io.Serializable;

public class CategoriaCursoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2573992238514718686L;

	private Long id;

	private String descricao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
