package io.github.scheduleguide.domain;

/** <i>Documentação da classe Período.</i>
 * 
 * <p>A classe período é uma classe abstrata usada para construção de uma sessão de estudo. Ela é implementada
 * e diferenciada entre períodos de foco (que são atrelados a algum conteúdo) e períodos de descanso.</p>
 * 
 * @author Fabiola Meireles Vilaça, Igor Wandekochen Bittencourt, Rafael Vieira de Almeida
 * @see io.github.scheduleguide.domain.SessaoEstudo
 * @see io.github.scheduleguide.domain.PeriodoFoco
 * @see io.github.scheduleguide.domain.PeriodoDescanso
 */
public abstract class Periodo {
	/** Duração (em segundos) prevista para o período, inferida do {@link Intervalo} utilizado pelo algoritmo do cronograma. */
	private int duracaoPrevista;
	/** Duração (em segundos) real, obtida a partir do uso do sistema pelo usuário. */
	private int duracaoReal;

	/** Constrói um novo objeto <code>Periodo</code>, a partir dos parâmetros recebidos.
	 * 
	 * @param _duracaoPrevista Duração (em segundos) prevista, inferida do {@link Intervalo} usado para criação deste <code>Periodo</code>.
	 */
	public Periodo(int _duracaoPrevista){
		duracaoPrevista = _duracaoPrevista;
		duracaoReal = -1;
	}

	/** Retorna a duração prevista deste <code>Periodo</code>.
	 * @return Duração prevista, em segundos
	 */
	public int getDuracaoPrevista() {
		return duracaoPrevista;
	}
	/** Atualiza a duração prevista deste <code>Periodo</code>.
	 * @param duracaoPrevista Nova duração prevista, em segundos
	 */
	public void setDuracaoPrevista(int duracaoPrevista) {
		this.duracaoPrevista = duracaoPrevista;
	}

	/** Retorna a duração real deste <code>Periodo</code>.
	 * @return Duração real, em segundos.
	 */
	public int getDuracaoReal() {
		return duracaoReal;
	}
	/** Atualiza a duração real deste <code>Periodo</code>
	 * @param duracaoReal Nova duração real, em segundos.
	 */
	public void setDuracaoReal(int duracaoReal) {
		this.duracaoReal = duracaoReal;
	}
}
