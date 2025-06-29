package com.generation.blogpessoal.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_postagens") // CREAT TABLE tb_postagens();
public class Postagem {

	@Id // Primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
	private Long id;

	@Column(length = 100)
	@NotBlank(message = "O atributo título é obrigatório")
	@Pattern(regexp = "^[^0-9].*", message = "A descrição não pode ser apenas numérico")
	@Size(min = 5, max = 100, message = "O atributo título deve ter no minimo 5 e no máximo 100 caracteres")
	private String titulo;

	@Column(length = 1000)
	@NotBlank(message = "O atributo texto é obrigatório") 
	@Size(min = 10, max = 1000, message = "O atributo título deve ter no minimo 10 e no máximo 1000 caracteres")
	private String texto;
	
	@UpdateTimestamp
	private LocalDateTime data;
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Categoria tema;
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Usuario usuario;

	public Categoria getTema() {
		return tema;
	}

	public void setTema(Categoria tema) {
		this.tema = tema;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}