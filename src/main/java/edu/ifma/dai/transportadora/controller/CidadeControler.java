package edu.ifma.dai.transportadora.controller;

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

import edu.ifma.dai.transportadora.model.Cidade;
import edu.ifma.dai.transportadora.repository.filter.CidadeFilter;
import edu.ifma.dai.transportadora.service.CadastroCidadeService;


@Controller
@RequestMapping("/cidades")
public class CidadeControler {
	
	private static final String CADASTRO_VIEW = "CadastroCidade";
	
	@Autowired
	private CadastroCidadeService cadastroCidadeService;

	@RequestMapping("/novocidade")
	public String novo(Cidade cidade) {
		return CADASTRO_VIEW;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Cidade cidade, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_VIEW;
		}
		
		cadastroCidadeService.salvar(cidade);
		attributes.addFlashAttribute("mensagem", "Cidade salva com sucesso!");
		return "redirect:/cidades/novocidade";
	}
	
	@RequestMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro") CidadeFilter filtro) {
		List<Cidade> todosCidades = cadastroCidadeService.filtrar(filtro);
		
		ModelAndView mv = new ModelAndView("PesquisaCidades");
		mv.addObject("cidades", todosCidades);
		return mv;
	}
	
	@ModelAttribute("todosCidade")
	public List<Cidade> todosEstados() {
		return cadastroCidadeService.todosCidades();
	}
	
	
	@RequestMapping("{id_cidade}")
	public ModelAndView edicao(@PathVariable("id_cidades") Cidade cidade) {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW); 
		mv.addObject(cidade);
		return mv;
	}
	
	@RequestMapping(value="{id_cidade}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		cadastroCidadeService.excluir(codigo);
		
		attributes.addFlashAttribute("mensagem", "Cidade excluída com sucesso!");
		return "redirect:/cidades";
	}
	
}
