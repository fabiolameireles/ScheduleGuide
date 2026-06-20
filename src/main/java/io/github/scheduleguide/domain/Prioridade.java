package io.github.scheduleguide.domain;

/** <i>Documentação da enumeração Prioridade.</i>
 * 
 * <p>A enumeração Prioridade é uma enumeração que consta nos objetos de Tópico, e que sinaliza
 * qual importância deve ser dada a esse objeto durante o algoritmo de criação de cronograma.</p>
 * 
 * @author Fabiola Meireles Vilaça, Igor Wandekochen Bittencourt, Rafael Vieira de Almeida
 * @see io.github.scheduleguide.domain.Topico
 * 
 */
public enum Prioridade {
	/** Prioridade alta para o algoritmo de cronograma. */
	ALTA,

	/** Prioridade mediana para o algoritmo de cronograma. */
	MEDIA,

	/** Prioridade baixa para o algoritmo de cronograma. */
	BAIXA;
}
