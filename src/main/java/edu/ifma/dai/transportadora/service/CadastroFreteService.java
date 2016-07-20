package edu.ifma.dai.transportadora.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifma.dai.transportadora.model.Frete;
import edu.ifma.dai.transportadora.repository.Fretes;
import edu.ifma.dai.transportadora.repository.filter.FreteFilter;

@Service
public class CadastroFreteService {
	
	@Autowired
	private Fretes fretes;
	
	public BigDecimal salvar(Frete frete) {
		fretes.save(frete);	
		return frete.calculaFrete();
	}

	public void excluir(Long codigo) {
		fretes.delete(codigo);
	}
	
	public List<Frete> filtrar(FreteFilter filtro) {
		String descricao = filtro.getDescricao() == null ? "%" : filtro.getDescricao();
		return fretes.findByDescricaoContaining(descricao);
	}
}
