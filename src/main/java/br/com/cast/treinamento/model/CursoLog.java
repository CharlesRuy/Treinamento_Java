package br.com.cast.treinamento.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tb_curso_log", schema = "treinamento")
public class CursoLog implements Serializable {
	private static final long serialVersionUID = -4826901557138660510L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "DS_NOME")
	private String nomeUsuario;
	
	@Column(name = "DT_LOG", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHoraLog;
	
	@Column(name = "DS_ACAO")
	private String acao;
	
	@ManyToOne
	@JoinColumn(name = "ID_CURSO")
	private Curso curso;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public Date getDataHoraLog() {
		return dataHoraLog;
	}

	public void setDataHoraLog(Date dataHoraLog) {
		this.dataHoraLog = dataHoraLog;
	}
	
	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
}
