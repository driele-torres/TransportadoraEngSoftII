package edu.ifma.dai.transportadora.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.ifma.dai.transportadora.service.CadastroClienteService;
import edu.ifma.dai.transportadora.service.CadastroCidadeService;
import edu.ifma.dai.transportadora.model.Cidade;
import edu.ifma.dai.transportadora.model.Cliente;
import edu.ifma.dai.transportadora.model.Frete;
import edu.ifma.dai.transportadora.repository.filter.FreteFilter;
import edu.ifma.dai.transportadora.service.CadastroFreteService;

@Controller
@RequestMapping("/fretes")
public class FreteControler {
	
	private static final String CADASTRO_VIEW = "CadastroFrete";
	
	@Autowired
	private CadastroFreteService cadastroFreteService;
	
	@Autowired
	private CadastroClienteService cadastroClienteService;
	
	@Autowired
	private CadastroCidadeService cadastroCidadeService;

	@RequestMapping("/novofrete")
	public String novo(Frete frete) {
		return CADASTRO_VIEW;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Frete frete, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_VIEW;
		}
		
		try {
			BigDecimal valFrete = cadastroFreteService.salvar(frete);
			attributes.addFlashAttribute("mensagem", "Frete salvo com sucesso! Valor: "+valFrete);
			return "redirect:/fretes/novofrete";
		
		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataVencimento", null, e.getMessage());
			return CADASTRO_VIEW;
		}
	}
	
	@RequestMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro") FreteFilter filtro) {
		List<Frete> todosFretes = cadastroFreteService.filtrar(filtro);
		
		ModelAndView mv = new ModelAndView("PesquisaFretes");
		mv.addObject("fretes", todosFretes);
		return mv;
	}
	
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Frete frete) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW); 
		mv.addObject(frete);
		return mv;
	}
	
	@RequestMapping(value="{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		cadastroFreteService.excluir(codigo);
		
		attributes.addFlashAttribute("mensagem", "Frete excluído com sucesso!");
		return "redirect:/fretes";
	}
	
	@ModelAttribute("todosClientes")
	public List<Cliente> todosClientes() {
		return cadastroClienteService.todosClientes();
	}

	@ModelAttribute("todasCidades")
	public List<Cidade> todasCidades() {
		return cadastroCidadeService.todosCidades();
	}


}
