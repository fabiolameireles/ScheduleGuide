package io.github.scheduleguide.domain;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
/** <i>Documentação da classe Material.</i>
 * 
 * <p>A classe material é a forma do usuário centralizar materiais de apoio ao estudo
 * no sistema, possuindo simplesmente um nome apresentado ao usuário e um link opcional,
 * servindo para ter fácil acesso a alguma informação ou a algum arquivo externo ao sistema.</p>
 * 
 * @author Fabiola Meireles Vilaça, Igor Wandekochen Bittencourt, Rafael Vieira de Almeida
 * @see io.github.scheduleguide.domain.Conteudo
 */
@Entity
public class Material {
	/** Identificador deste material, para armazenamento no banco de dados. */
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	/** Nome deste material, usado para apresentação ao usuário */
	private String nome;

	/** Link relacionado a este material, atributo opcional e inicialmente nulo. */
	private String link;

	/** Conteudo a que pertence este material, para ter noção de pertencimento. */
	@ManyToOne
	private Conteudo conteudo;

	/**
	 * Constrói um novo objeto da classe <code>Material</code>, com os valores padrão.
	 * <p>
	 * Este <code>Material</code> é construída com o nome sendo uma string vazia, e
	 * o link e conteudo sendo <code>null</code>.
	 * <br>
	 * Este construtor é utilizado na leitura de requisições.
	 * 
	 */
	@JsonCreator
	public Material() {
		nome = "";
		conteudo = null;
	}

	/** Constrói um material com apenas nome, mantendo o link <code>null</code>.
	 * 
	 * @param _nome Nome deste material.
	 */
	public Material(String _nome, Conteudo _conteudo){
		nome = _nome;
		conteudo = _conteudo;
	}

	/** Constrói um material com ambos nome e link, a partir dos parâmetros recebidos.
	 * 
	 * @param _nome Nome deste material.
	 * @param _link Link relacionado a este material, aberto ao clicar no nome.
	 */
	public Material(String _nome, Conteudo _conteudo, String _link){
		nome = _nome;
		link = _link;
		conteudo = _conteudo;
	}

	/** Retorna o identificador deste <code>Material</code>.
	 * <p>
	 * Esse valor será utilizado como identificador deste <code>Material</code> em requisições.
	 * @return Identificador deste <code>Material</code>
	 */
	public long getId() {
		return id;
	}
	/** Atualiza o identificador deste <code>Material</code>.
	 * <p>
	 * Um valor novo corresponderá a uma entrada diferente no banco de dados e objetos associados.
	 * @param id Identificador a ser atualizado
	 */
	public void setId(long id) {
		this.id = id;
	}

	/** Retorna o nome deste <code>Material</code>, para apresentação ao usuário
	 * @return Nome do material
	 */
	public String getNome() {
		return nome;
	}
	/** Atualiza o nome deste <code>Material</code>.
	 * @param nome Novo nome a ser salvo
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/** Retorna o link relacinado a este <code>Material</code>, se existir.
	 * @return String com o link, se existir. Caso contrário, retorna <code>null</code>.
	 */
	public Optional<String> getLink() {
		return Optional.ofNullable(link);
	}
	/** Atualiza o link relacionado a este <code>Material</code>.
	 * @param link Novo link a ser registrado
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/** Retorna o {@link Conteudo} a que este <code>Material</code> pertence.
	 * 
	 * @return Conteudo relacionado
	 */
	@JsonIgnore
	public Conteudo getConteudo() {
		return conteudo;
	}

	/** Atualiza o {@link Conteudo} a que este <code>Material</code> pertence.
	 * @param cont Novo conteúdo a ser salvo no atributo.
	 */
	public void setConteudo(Conteudo cont) {
		this.conteudo = cont;
	}
}
