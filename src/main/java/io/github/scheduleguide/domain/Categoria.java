package io.github.scheduleguide.domain;

import java.util.List;

public class Categoria {
	private String nome;
	private List<Topico> topicos;

	public Categoria(String _nome){
		nome = _nome;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Topico> getTopicos() {
		return topicos;
	}
	public void setTopicos(List<Topico> topicos) {
		this.topicos = topicos;
	}
}
