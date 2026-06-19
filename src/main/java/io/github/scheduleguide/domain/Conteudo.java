package io.github.scheduleguide.domain;

import java.util.List;

public class Conteudo {
	private String nome;
	private int nivelDeDominio;
	private String anotacoes;
	private boolean ativo;
	private List<Material> materiais;
	private Topico topico;

	public Conteudo(String _nome, int _nivel, String _anotacoes, boolean _ativo, List<Material> _materiais, Topico _topico){
		nome = _nome;
		nivelDeDominio = _nivel;
		anotacoes = _anotacoes;
		ativo = _ativo;
		materiais = _materiais;
		topico = _topico;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNivelDeDominio() {
		return nivelDeDominio;
	}
	public void setNivelDeDominio(int nivelDeDominio) {
		this.nivelDeDominio = nivelDeDominio;
	}

	public String getAnotacoes() {
		return anotacoes;
	}
	public void setAnotacoes(String anotacoes) {
		this.anotacoes = anotacoes;
	}

	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public List<Material> getMateriais() {
		return materiais;
	}
	public void setMateriais(List<Material> materiais) {
		this.materiais = materiais;
	}

	public Topico getTopico() {
		return topico;
	}
	public void setTopico(Topico topico) {
		this.topico = topico;
	}
}
