package io.github.scheduleguide.domain;

/** <i>Documentação da classe Período de Foco.</i>
 * 
 * <p>A classe período de foco implementa a classe abstrata <code>Periodo</code>, e sua função
 * é demarcar um tempo em que o usuário deve focar em um conteúdo, que é atrelado a esta classe.</p>
 * 
 * @author Fabiola Meireles Vilaça, Igor Wandekochen Bittencourt, Rafael Vieira de Almeida
 * @see io.github.scheduleguide.domain.Periodo
 */
public class PeriodoFoco extends Periodo {
	/** Conteúdo atrelado a este período de foco */
	private Conteudo conteudo;

	/** Constrói um novo <code>PeriodoFoco</code>, a partir dos parâmetros recebidos.
     * <br><br>
     * Chama o construtor da classe <code>Periodo</code>.
     * 
     * @param duracaoPrevista Duração (em segundos) prevista, inferida do {@link Intervalo} usado para criação deste <code>PeriodoFoco</code>.
	 * @param _conteudo Conteúdo que deve ser atrelado a este <code>PeriodoFoco</code>.
     */
	public PeriodoFoco(int duracaoPrevista, Conteudo _conteudo){
        super(duracaoPrevista);
		conteudo = _conteudo;
    }

	/** Retorna o {@link Conteudo} atrelado a este <code>PeriodoFoco</code>.
	 * 
	 * @return Conteúdo atrelado
	 */
	public Conteudo getConteudo() {
		return conteudo;
	}
	/** Atualiza o {@link Conteudo} que deve ser atrelado a este <code>PeriodoFoco</code>.
	 * 
	 * @param conteudo Novo conteúdo a ser atrelado
	 */
	public void setConteudo(Conteudo conteudo) {
		this.conteudo = conteudo;
	}
}
