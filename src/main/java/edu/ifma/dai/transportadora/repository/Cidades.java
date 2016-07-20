package edu.ifma.dai.transportadora.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.ifma.dai.transportadora.model.Cidade;

@Repository
public interface Cidades extends JpaRepository<Cidade, Long> {

	public List<Cidade> findByNomeContaining(String nome);
		
}
