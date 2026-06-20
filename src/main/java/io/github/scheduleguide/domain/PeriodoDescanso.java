package io.github.scheduleguide.domain;

/** <i>Documentação da classe Período de Descanso.</i>
 * 
 * <p>A classe período de descanço implementa a classe abstrata <code>Periodo</code>, e sua função
 * é demarcar um tempo que o usuário precisa descansar em meio aos estudos.</p>
 * 
 * @author Fabiola Meireles Vilaça, Igor Wandekochen Bittencourt, Rafael Vieira de Almeida
 * @see io.github.scheduleguide.domain.Periodo
 */
public class PeriodoDescanso extends Periodo {

    /** Constrói um novo <code>PeriodoDescanso</code>, a partir dos parâmetros recebidos.
     * <br><br>
     * Chama o construtor da classe <code>Periodo</code>.
     * 
     * @param duracaoPrevista Duração (em segundos) prevista, inferida do {@link Intervalo} usado para criação deste <code>PeriodoDescanso</code>.
     */
    public PeriodoDescanso(int duracaoPrevista){
        super(duracaoPrevista);
    }
}
