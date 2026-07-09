package io.github.scheduleguide.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
/** <i>Documentação da classe Conteúdo.</i>
 * 
 * <p>A classe de conteúdo engloba um elemento de uma lista pertencente a um tópico, sendo uma maior especificação
 * de um assunto a ser estudado, possibilitando o usuário centralizar informações úteis relacionadas a este assunto,
 * como anotações e materiais de apoio, além de aumentar a especificidade do algoritmo de geração de cronogramas,
 * podendo registrar o nível de domínio e a eligibilidade deste conteúdo para esses cronogramas.</p>
 * 
 * @author Fabiola Meireles Vilaça, Igor Wandekochen Bittencourt, Rafael Vieira de Almeida
 * @see io.github.scheduleguide.domain.Topico
 * @see io.github.scheduleguide.domain.Material
 */
@Entity
public class Conteudo {
	/** Identificador deste conteúdo, para armazenamento no banco de dados. */
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	/** Nome deste conteúdo, usado para apresentação ao usuário do sistema. */
	private String nome;

	/** Nível de domínio deste conteúdo, valor entre 0 e 10. */
	private int nivelDeDominio;

	/** Anotações referentes a este conteúdo. */
	private String anotacoes;

	/** Estado de atividade deste conteúdo. Um conteúdo ativo é aquele que,
	 * estando o tópico a que pertence também ativo, pode ser alocado para
	 * um período de foco durante a criação de um cronograma.
	 */
	private boolean ativo;

	/** Lista de materiais relacionados a este conteúdo. */
	@OneToMany
	private List<Material> materiais = new ArrayList<Material>();

	/** Tópico a que este conteúdo pertence. */
	@ManyToOne
	private Topico topico;

	/**
	 * Constrói um novo objeto da classe <code>Conteudo</code>, com os valores padrão.
	 * <p>
	 * Este <code>Conteudo</code> é construído com o nome e anotações sendo strings vazias,
	 * nível de domínio com valor 0 e a flag <code>ativo</code> verdadeira.
	 * <br>
	 * Este construtor é utilizado na leitura de requisições.
	 * 
	 */
	@JsonCreator
	public Conteudo() {
		nome = "";
		nivelDeDominio = 0;
		anotacoes = "";
		ativo = true;
		topico = null;
	}

	/** Constrói um novo objeto da classe <code>Conteudo</code>, a partir dos parâmetros recebidos.
	 * <br><br>
	 * Todo <code>Conteudo</code> é criado inicialmente ativo.
	 * 
	 * @param _nome Nome deste conteúdo, para apresentação ao usuário
	 * @param _nivel Nível de domínio deste conteúdo, entre 0 e 10
	 * @param _anotacoes Anotações referentes a este conteúdo
	 * @param _materiais Lista de materiais relacionados a este conteúdo
	 * @param _topico Tópico a que este conteúdo pertence
	 */
	public Conteudo(String _nome, int _nivel, String _anotacoes, List<Material> _materiais, Topico _topico){
		nome = _nome;
		setNivelDeDominio(_nivel);
		anotacoes = _anotacoes;
		materiais = _materiais;
		topico = _topico;

		ativo = true;
	}

	/** Retorna o identificador deste <code>Conteudo</code>.
	 * <p>
	 * Esse valor será utilizado como identificador deste <code>Conteudo</code> em requisições.
	 * @return Identificador deste <code>Conteudo</code>
	 */
	public long getId() {
		return id;
	}
	/** Atualiza o identificador deste <code>Conteudo</code>.
	 * <p>
	 * Um valor novo corresponderá a uma entrada diferente no banco de dados e objetos associados.
	 * @param id Identificador a ser atualizado
	 */
	public void setId(long id) {
		this.id = id;
	}

	/** Retorna o nome salvo deste <code>Conteudo</code>.
	 * <p>
	 * Este nome serve apenas para apresentação ao usuário.
	 * @return Nome salvo deste <code>Conteudo</code>
	 */
	public String getNome() {
		return nome;
	}
	/** Atualiza o nome deste <code>Conteudo</code>.
	 * <p>
	 * Este nome serve apenas para apresentação ao usuário.
	 * @param nome Nome a ser atualizado
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/** Retorna o nível de domínio reportado para este <code>Conteudo</code>.
	 * @return Nível de domínio reportado
	 */
	public int getNivelDeDominio() {
		return nivelDeDominio;
	}
	/** Atualiza o nível de domínio deste <code>Conteudo</code> para um valor dado, dentro dos limites do sistema.
	 * @param novo_nivel Novo nível de domínio a ser registrado. O valor é limitado no intervalo entre 0 e 10.
	 */
	public void setNivelDeDominio(int novo_nivel) {
		int nivel_corrigido = novo_nivel;
		
		if (novo_nivel < 0){
			nivel_corrigido = novo_nivel;
		} else if (novo_nivel > 10){
			nivel_corrigido = novo_nivel;
		}

		nivelDeDominio = nivel_corrigido;
	}

	/** Retorna as anotações salvas referentes a este <code>Conteudo</code>.
	 * @return <code>String</code> das anotações salvas
	 */
	public String getAnotacoes() {
		return anotacoes;
	}
	/** Atualiza o texto das anotações referentes a este <code>Conteudo</code>.
	 * @param anotacoes Novo texto para as anotações
	 */
	public void setAnotacoes(String anotacoes) {
		this.anotacoes = anotacoes;
	}

	/** Retorna se este <code>Conteudo</code> está ativo
	 * <p>
	 * Um <code>Conteudo</code> ativo pode ser alocado a um {@link PeriodoFoco} se o {@link Topico} a que pertence também estiver.
	 * @return <code>true</code> se estiver ativo, <code>false</code> em caso contrário.
	 */
	public boolean isAtivo() {
		return ativo;
	}
	/** Atualiza se o <code>Conteudo</code> está ou não ativo.
	 * <p>
	 * Um <code>Conteudo</code> que é desativado não é removido de objetos {@link PeriodoFoco} a que já estava alocado.
	 * @param valor Novo valor de ativo
	 */
	public void setAtivo(boolean valor) {
		this.ativo = valor;
	}

	/** Retorna a lista de materiais relacionados a este <code>Conteudo</code>.
	 * @return A lista de materiais.
	 */
	@JsonIgnore
	public List<Material> getMateriais() {
		return materiais;
	}
	/** Atualiza a lista de materiais relacionados a este <code>Conteudo</code>.
	 * @param materiais Nova lista de materiais.
	 */
	public void setMateriais(List<Material> materiais) {
		this.materiais = materiais;
	}

	/** Retorna o {@link Topico} a que este <code>Conteudo</code> está relacionado.
	 * @return Tópico relacionado.
	 */
	@JsonIgnore
	public Topico getTopico() {
		return topico;
	}
	/** Atualiza o {@link Topico} a que este <code>Conteudo</code> está relacionado.
	 * @param topico Novo tópico a ser salvo no atributo.
	 */
	public void setTopico(Topico topico) {
		this.topico = topico;
	}

	/** Adiciona um novo {@link Material} à lista de materiais. O <code>Material</code> é adicionado ao final da lista.
	 * @param mat Material a ser adicionado. Se o parâmetro for <code>null</code>, nada será feito.
	 */
	public void addMaterial(Material mat){
		if (mat != null){
			materiais.add(mat);
		}
	}

	/** Atualiza um {@link Material} específico presente na lista a partir de seu índice.
	 * <p>
	 * O <code>indice_material</code> deve estar dentro dos limites da lista de materiais. A função não fará nada em caso contrário.
	 * 
	 * @param indice_material Índice do material a ser atualizado.
	 * @param mat_novo Nova versão do material. Se esse parâmetro for <code>null</code>, o método é equivalente a {@link Conteudo#removeMaterial}.
	 */
	public void updateMaterial(int indice_material, Material mat_novo){
		if (mat_novo == null){
			removeMaterial(indice_material);
		} else if (indice_material >= 0 && indice_material < materiais.size()){
			materiais.set(indice_material, mat_novo);
		}
	}

	/** Remove um {@link Material} da lista de materiais a partir de seu índice.
	 * <p>
	 * O <code>indice_material</code> deve estar dentro dos limites da lista de materiais. A função não fará nada em caso contrário.
	 * @param indice_material Índice do material a ser removido.
	 */
	public void removeMaterial(int indice_material){
		if (indice_material >= 0 && indice_material < materiais.size()){
			materiais.remove(indice_material);
		}
	}


}
