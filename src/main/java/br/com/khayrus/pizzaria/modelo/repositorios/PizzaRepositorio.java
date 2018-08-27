package br.com.khayrus.pizzaria.modelo.repositorios;

import org.springframework.data.repository.CrudRepository;

import br.com.khayrus.pizzaria.modelo.entidades.Pizza;

public interface PizzaRepositorio extends CrudRepository<Pizza, Long> {

}
