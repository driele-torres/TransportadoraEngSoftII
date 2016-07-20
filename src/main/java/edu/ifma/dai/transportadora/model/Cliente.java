package edu.ifma.dai.transportadora.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_cliente;

	@NotEmpty(message = "Nome é obrigatório")
	@Size(max = 50, message = "Nome deve ser menor que 100 caracteres")
	private String nome;

	@NotEmpty(message = "Endereço é obrigatório")
	@Size(max = 100, message = "O endereço deve ser menor que 100 caracteres")
	private String endereco;

	@Size(max = 15, message = "O telefone deve ser menor que 15 caracteres")
	private String telefone;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId_cliente() == null) ? 0 : getId_cliente().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (getId_cliente() == null) {
			if (other.getId_cliente() != null)
				return false;
		} else if (!getId_cliente().equals(other.getId_cliente()))
			return false;
		return true;
	}

	public Long getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
	}

}
