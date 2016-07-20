package edu.ifma.dai.transportadora.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ifma.dai.transportadora.model.Cliente;
import edu.ifma.dai.transportadora.repository.Clientes;
import edu.ifma.dai.transportadora.repository.filter.ClienteFilter;

@Service
public class CadastroClienteService {
	
	@Autowired
	private Clientes clientes;
	
	public void salvar(Cliente cliente) {
		clientes.save(cliente);	
	}

	public void excluir(Long codigo) {
		clientes.delete(codigo);
	}
	
	public List<Cliente> filtrar(ClienteFilter filtro) {
		String nome = filtro.getNome() == null ? "%" : filtro.getNome();
		return clientes.findByNomeContaining(nome);
	}
	
	public List<Cliente> todosClientes() {
		return clientes.findAll();
	}

}
