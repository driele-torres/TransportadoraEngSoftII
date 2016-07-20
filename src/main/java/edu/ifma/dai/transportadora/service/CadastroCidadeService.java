package edu.ifma.dai.transportadora.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifma.dai.transportadora.model.Cidade;
import edu.ifma.dai.transportadora.repository.Cidades;
import edu.ifma.dai.transportadora.repository.filter.CidadeFilter;

@Service
public class CadastroCidadeService {
	
	@Autowired
	private Cidades cidades;
	
	public void salvar(Cidade cidade) {
		cidades.save(cidade);
	}

	public void excluir(Long codigo) {
		cidades.delete(codigo);
	}
	
	public List<Cidade> filtrar(CidadeFilter filtro) {
		String nome = filtro.getNome() == null ? "%" : filtro.getNome();
		return cidades.findByNomeContaining(nome);
	}
	
	public List<Cidade> todosCidades() {
		return cidades.findAll();
	}
	
}
