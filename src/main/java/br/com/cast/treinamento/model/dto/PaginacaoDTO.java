package br.com.cast.treinamento.model.dto;

import java.io.Serializable;

public class PaginacaoDTO<F> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8979855338321424774L;

	private Integer quantidadePorPagina;
	private Integer numeroPagina;
	private String sortBy;
	private F filter;

	public Integer getQuantidadePorPagina() {
		return quantidadePorPagina;
	}

	public void setQuantidadePorPagina(Integer quantidadePorPagina) {
		this.quantidadePorPagina = quantidadePorPagina;
	}

	public Integer getNumeroPagina() {
		return numeroPagina;
	}

	public void setNumeroPagina(Integer numeroPagina) {
		this.numeroPagina = numeroPagina;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public F getFilter() {
		return filter;
	}

	public void setFilter(F filter) {
		this.filter = filter;
	}

}
