package br.com.khayrus.pizzaria.modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.khayrus.pizzaria.modelo.enumeracoes.CategoriaDeIngrediente;

@Entity
public class Ingrediente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NotEmpty
	private String nome;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private CategoriaDeIngrediente categoria;

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

	public CategoriaDeIngrediente getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDeIngrediente categoria) {
		this.categoria = categoria;
	}
	
	
}
