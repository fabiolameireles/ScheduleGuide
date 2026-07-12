package io.github.scheduleguide.domain;

import java.util.Optional;
/** <i>Documentação da classe Material.</i>
 * 
 * <p>A classe material é a forma do usuário centralizar materiais de apoio ao estudo
 * no sistema, possuindo simplesmente um nome apresentado ao usuário e um link opcional,
 * servindo para ter fácil acesso a alguma informação ou a algum arquivo externo ao sistema.</p>
 * 
 * @author Fabiola Meireles Vilaça, Igor Wandekochen Bittencourt, Rafael Vieira de Almeida
 * @see io.github.scheduleguide.domain.Conteudo
 */
public class Material {
	/** Nome deste material, usado para apresentação ao usuário */
	private String nome;

	/** Link relacionado a este material, atributo opcional e inicialmente nulo. */
	private String link;


	/** Constrói um material com apenas nome, mantendo o link <code>null</code>.
	 * 
	 * @param _nome Nome deste material.
	 */
	public Material(String _nome){
		nome = _nome;
	}

	/** Constrói um material com ambos nome e link, a partir dos parâmetros recebidos.
	 * 
	 * @param _nome Nome deste material.
	 * @param _link Link relacionado a este material, aberto ao clicar no nome.
	 */
	public Material(String _nome, String _link){
		nome = _nome;
		link = _link;
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
}
