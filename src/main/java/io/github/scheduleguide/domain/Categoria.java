package io.github.scheduleguide.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

/** <i>Documentação da classe Categoria</i>.
 * 
 * <p>A classe categoria ocupa a maior posição na "hierarquia" de objetos relacionados ao conteúdo de estudo, sendo
 * ela utilizada para agrupar diferentes tópicos, como um "filtro" para encontrar todos os tópicos registrados.</p>
 * 
 * @author Fabiola Meireles Vilaça, Igor Wandekochen Bittencourt, Rafael Vieira de Almeida
 * 
 * @see io.github.scheduleguide.domain.Topico
 */
@Entity
public class Categoria {
	/** Identificador desta categoria, para armazenamento no banco de dados. */
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	/** Nome desta categoria, utilizada para apresentá-la ao usuário. */
	private String nome;

	/** Lista de tópicos pertencentes a esta categoria, inicialmente vazia. */
	@OneToMany(mappedBy="categoria")
	private List<Topico> topicos = new ArrayList<Topico>();

	/**
	 * Constrói um novo objeto da classe <code>Categoria</code>, com os valores padrão.
	 * <p>
	 * Esta <code>Categoria</code> é construída com o nome sendo uma string vazia.
	 * <br>
	 * Este construtor é utilizado na leitura de requisições.
	 * 
	 */
	@JsonCreator
	public Categoria() {
		nome = "";
	}

	/** Constrói um novo objeto da classe <code>Categoria</code>, a partir dos parâmetros recebidos.
	 * <br><br>
	 * Toda <code>Categoria</code> inicia com a lista de tópicos vazia.
	 * 
	 * @param _nome Nome desta categoria, para apresentação ao usuário.
	 */
	public Categoria(String _nome){
		nome = _nome;
	}

	/** Retorna o identificador desta <code>Categoria</code>.
	 * <p>
	 * Esse valor será utilizado como identificador desta <code>Categoria</code> em requisições.
	 * @return Identificador desta <code>Categoria</code>
	 */
	public long getId() {
		return id;
	}
	/** Atualiza o identificador desta <code>Categoria</code>.
	 * <p>
	 * Um valor novo corresponderá a uma entrada diferente no banco de dados e objetos associados.
	 * @param id Identificador a ser atualizado
	 */
	public void setId(long id) {
		this.id = id;
	}

	/** Retorna o nome salvo desta <code>Categoria</code>.
	 * <p>
	 * Este nome serve apenas para apresentação ao usuário.
	 * @return Nome salvo desta <code>Categoria</code>
	 */
	public String getNome() {
		return nome;
	}
	/** Atualiza o nome desta <code>Categoria</code>.
	 * <p>
	 * Este nome serve apenas para apresentação ao usuário.
	 * @param nome Nome a ser atualizado
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/** Recebe a lista de tópicos que pertencem a esta <code>Categoria</code>.
	 * @return Lista de tópicos pertencentes.
	 */
	@JsonIgnore
	public List<Topico> getTopicos() {
		return topicos;
	}
	/** Atualiza a lista de tópicos que percentem a esta <code>Categoria</code>.
	 * @param topicos Nova lista de tópicos.
	 */
	public void setTopicos(List<Topico> topicos) {
		this.topicos = topicos;
	}

	/** Adiciona um novo {@link Topico} à lista de tópicos. O <code>Topico</code> é adicionado ao final da lista.
	 * @param top Tópico a ser adicionado. Se o parâmetro for <code>null</code>, nada será feito.
	 */
	public void addTopico(Topico top){
		if (top != null){
			topicos.add(top);
		}
	}

	/** Atualiza um {@link Topico} específico presente na lista a partir de seu índice.
	 * <p>
	 * O <code>indice_topico</code> deve estar dentro dos limites da lista de tópicos. A função não fará nada em caso contrário.
	 * 
	 * @param indice_topico Índice do tópico a ser atualizado.
	 * @param top_novo Nova versão do tópico. Se esse parâmetro for <code>null</code>, o método é equivalente a {@link Categoria#removeTopico}.
	 */
	public void updateTopico(int indice_topico, Topico top_novo){
		if (top_novo == null){
			removeTopico(indice_topico);
		} else if (indice_topico >= 0 && indice_topico < topicos.size()){
			topicos.set(indice_topico, top_novo);
		}
	}

	/** Remove um {@link Topico} da lista de tópicos a partir de seu índice.
	 * <p>
	 * O <code>indice_topico</code> deve estar dentro dos limites da lista de tópicos. A função não fará nada em caso contrário.
	 * @param indice_topico Índice do tópico a ser removido.
	 */
	public void removeTopico(int indice_topico){
		if (indice_topico >= 0 && indice_topico < topicos.size()){
			topicos.remove(indice_topico);
		}
	}
}
