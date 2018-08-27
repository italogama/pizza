package br.com.khayrus.pizzaria.controladores;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.khayrus.pizzaria.excecoes.IngredienteInvalidoException;
import br.com.khayrus.pizzaria.modelo.entidades.Ingrediente;
import br.com.khayrus.pizzaria.modelo.enumeracoes.CategoriaDeIngrediente;
import br.com.khayrus.pizzaria.modelo.repositorios.IngredienteRepositorio;

//		/app/ingredientes (metodo GET) -> listarIngredientes
//		/app/ingredientes (metodo POST) -> salvarIngredientes

@Controller
@RequestMapping("/ingredientes")
public class IngredienteController {

	@Autowired
	private IngredienteRepositorio ingredienteRepositorio;

	@RequestMapping(method=RequestMethod.GET)
	public String listarIngredientes(Model model) {
		Iterable<Ingrediente> ingredientes = ingredienteRepositorio.findAll();

		model.addAttribute("titulo", "Listagem de Ingredientes");
		model.addAttribute("ingredientes", ingredientes);
		model.addAttribute("categorias", CategoriaDeIngrediente.values());
		return "ingrediente/listagem";
	}
	// /WEB-INF/ingrediente/listagem.jsp

	@RequestMapping(method=RequestMethod.POST)
	// ModelAttribute mapeia os atributos de um formulario pra objeto
	public String salvarIngrediente(
			@Valid @ModelAttribute Ingrediente ingrediente,
			BindingResult bindingResult,
			Model model) {

		if (bindingResult.hasErrors()) {
			throw new IngredienteInvalidoException();

		} else {
			ingredienteRepositorio.save(ingrediente);
		}

		model.addAttribute("ingredientes", ingredienteRepositorio.findAll());
		model.addAttribute("categorias", CategoriaDeIngrediente.values());
		return "ingrediente/tabela-ingredientes";
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public ResponseEntity<String> deletarIngrediente(@PathVariable Long id) {
		try {
			ingredienteRepositorio.delete(id);
			return new ResponseEntity<String>(HttpStatus.OK);
			
		} catch (Exception ex) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}")
	@ResponseBody
	public Ingrediente buscarIngrediente(@PathVariable Long id) {
		Ingrediente ingrediente = ingredienteRepositorio.findOne(id);
		return ingrediente;
	}
}
