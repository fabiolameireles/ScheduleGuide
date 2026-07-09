package io.github.scheduleguide.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

/** <i>Documentação da classe Tópico</i>.
 * 
 * <p>A classe tópico abrange o coletivo de conteúdos de um assunto, sendo separados em categorias para melhor
 * busca no sistema e classificados com base em sua prioridade, atributo utilizado durante o algoritmo de
 * geração de cronogramas.</p>
 * 
 * @author Fabiola Meireles Vilaça, Igor Wandekochen Bittencourt, Rafael Vieira de Almeida
 * 
 * @see io.github.scheduleguide.domain.Categoria
 * @see io.github.scheduleguide.domain.Conteudo
 */
@Entity
public class Topico {
	/** Identificador deste tópico, para armazenamento no banco de dados. */
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	/** Nome deste tópico, para apresentação ao usuário do sistema. */
	private String nome;

	/** Caminho para imagem deste tópico, para melhor visualização pelo usuário do sistema. */
	private String imagem;

	/** Prioridade deste tópico, valor utilizado ao gerar cronogramas. */
	private Prioridade prioridade;

	/** Estado de atividade deste conteúdo. Um conteúdo ativo é aquele que,
	 * estando o tópico a que pertence também ativo, pode ser alocado para
	 * um período de foco durante a criação de um cronograma.
	 */
	private boolean ativo;

	/** Lista de conteúdos que pertencem a este tópico. */
	@OneToMany(mappedBy="topico", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Conteudo> conteudos = new ArrayList<>();

	/** Categoria a que pertence este tópico, para melhor filtragem. */
	@ManyToOne
	private Categoria categoria;


	/**
	 * Constrói um novo objeto da classe <code>Topico</code>, com os valores padrão.
	 * <p>
	 * Este <code>Topico</code> é construído com nome e caminho para imagem vazios,
	 * prioridade alta, ativo e sem categoria.
	 * <br>
	 * Este construtor é utilizado na leitura de requisições.
	 * 
	 */
	@JsonCreator
	public Topico() {
		nome = "";
		imagem = "";
		prioridade = Prioridade.ALTA;
		ativo = true;
		categoria = null;
	}

	/** Constrói um novo objeto da classe <code>Topico</code>, a partir dos parâmetros recebidos.
	 * <br><br>
	 * Todo <code>Topico</code> é criado inicialmente ativo.
	 * 
	 * @param _nome Nome deste tópico, para apresentação ao usuário
	 * @param _imagem Caminho para imagem deste tópico, para apresentação ao usuário
	 * @param _prioridade Prioridade deste tópico, para uso no algoritmo do cronograma
	 * @param _conteudos Lista de conteúdos que pertencem a este tópico
	 * @param _categoria Categoria a que este tópico pertence
	 */
	public Topico(String _nome, String _imagem, Prioridade _prioridade, List<Conteudo> _conteudos, Categoria _categoria){
		nome = _nome;
		imagem = _imagem;
		prioridade = _prioridade;
		conteudos = _conteudos;
		categoria = _categoria;

		ativo = true;
	}

	/** Retorna o identificador deste <code>Topico</code>.
	 * <p>
	 * Esse valor deve ser utilizado pela categoria associada a este <code>Topico</code>.
	 * @return Identificador deste <code>Topico</code>
	 */
	public long getId() {
		return id;
	}
	/** Atualiza o identificador deste <code>Topico</code>.
	 * <p>
	 * Um valor novo corresponderá a uma entrada diferente no banco de dados e objetos associados.
	 * @param id Identificador a ser atualizado
	 */
	public void setId(long id) {
		this.id = id;
	}

	/** Retorna o nome salvo deste <code>Topico</code>.
	 * <p>
	 * Este nome serve apenas para apresentação ao usuário.
	 * @return Nome salvo deste <code>Topico</code>
	 */
	public String getNome() {
		return nome;
	}
	/** Atualiza o nome deste <code>Topico</code>.
	 * <p>
	 * Este nome serve apenas para apresentação ao usuário.
	 * @param nome Nome a ser atualizado
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/** Retorna o caminho para a imagem deste <code>Topico</code>.
	 * @return Caminho salvo para a imagem deste <code>Topico</code>
	 */
	public String getImagem() {
		return imagem;
	}
	/** Atualiza o caminho de imagem deste <code>Topico</code>.
	 * @param imagem Novo caminho a ser atualizado
	 */
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	/** Retorna a {@link Prioridade} salva para este <code>Topico</code>
	 * @return Prioridade salva
	 */
	public Prioridade getPrioridade() {
		return prioridade;
	}
	/** Atualiza o valor de {@link Prioridade} registrada neste <code>Topico</code>
	 * @param prioridade Nova prioridade a ser registrada
	 */
	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade;
	}

	/** Retorna se este <code>Topico</code> está ativo
	 * <p>
	 * Um <code>Topico</code> ativo pode ter seus objetos {@link Conteudo} alocados a objetos {@link PeriodoFoco}, se estes também estiverem ativos.
	 * @return <code>true</code> se estiver ativo, <code>false</code> em caso contrário
	 */
	public boolean isAtivo() {
		return ativo;
	}
	/** Atualiza se o <code>Topico</code> está ou não ativo.
	 * <p>
	 * Um <code>Topico</code> que é desativado não tem seus objetos {@link Conteudo} removidos de objetos {@link PeriodoFoco} existentes.
	 * @param valor Novo valor de eligibilidade
	 */
	public void setAtivo(boolean valor) {
		this.ativo = valor;
	}

	/** Retorna a lista de conteúdos relacionados a este <code>Topico</code>.
	 * @return Lista de conteúdos.
	 */
	@JsonIgnore
	public List<Conteudo> getConteudos() {
		return conteudos;
	}
	/** Atualiza a lista de conteúdos relacionados a este <code>Topico</code>.
	 * @param conteudos Nova lista de conteúdos.
	 */
	public void setConteudos(List<Conteudo> conteudos) {
		this.conteudos = conteudos;
	}

	/** Adiciona um novo {@link Conteudo} à lista de conteúdos. O <code>Conteudo</code> é adicionado ao final da lista.
	 * @param cont Conteudo a ser adicionado. Se o parâmetro for <code>null</code>, nada será feito.
	 */
	public void addConteudo(Conteudo cont){
		if (cont != null){
			conteudos.add(cont);
		}
	}

	/** Atualiza um {@link Conteudo} específico presente na lista a partir de seu índice.
	 * <p>
	 * O <code>indice_conteudo</code> deve estar dentro dos limites da lista de conteúdos. A função não fará nada em caso contrário.
	 * 
	 * @param indice_conteudo Índice do conteudo a ser atualizado.
	 * @param cont_novo Nova versão do conteúdo. Se esse parâmetro for <code>null</code>, o método é equivalente a {@link Topico#removeConteudo}.
	 */
	public void updateConteudo(int indice_conteudo, Conteudo cont_novo){
		if (cont_novo == null){
			removeConteudo(indice_conteudo);
		} else if (indice_conteudo >= 0 && indice_conteudo < conteudos.size()){
			conteudos.set(indice_conteudo, cont_novo);
		}
	}

	/** Remove um {@link Conteudo} da lista de conteúdos a partir de seu índice.
	 * <p>
	 * O <code>indice_conteudo</code> deve estar dentro dos limites da lista de conteúdos. A função não fará nada em caso contrário.
	 * @param indice_conteudo Índice do conteúdo a ser removido.
	 */
	public void removeConteudo(int indice_conteudo){
		if (indice_conteudo >= 0 && indice_conteudo < conteudos.size()){
			conteudos.remove(indice_conteudo);
		}
	}

	/** Retorna a categoria a que este <code>Topico</code> pertence.
	 * @return Categoria relacionada
	 */
	@JsonIgnore
	public Categoria getCategoria() {
		return categoria;
	}
	/** Atualiza a categoria a que este código pertence.
	 * <p>
	 * Um tópico sempre está relacionado a alguma categoria, sendo a categoria
	 * "Sem Categoria" a padrão, em caso de nenhuma específica selecionada.
	 * @param categoria Nova categoria a ser registrada.
	 */
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
