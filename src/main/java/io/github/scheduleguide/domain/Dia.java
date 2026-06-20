package io.github.scheduleguide.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

/** <i>Documentação da classe Dia.</i>
 * 
 * <p>A classe dia armazena informações sobre um dia do calendário, e visa agrupar intervalos
 * disponíveis nos quais o algoritmo do cronograma de estudos possa atuar neste dia.</p>
 * 
 * @author Fabiola Meireles Vilaça, Igor Wandekochen Bittencourt, Rafael Vieira de Almeida
 * @see io.github.scheduleguide.domain.Intervalo
 */
public class Dia {
	/** Data deste dia, definida pelo usuário a partir do calendário do site. */
	private LocalDate data;

	/** Dia da semana deste dia, atributo para mais fácil acesso dessa propriedade de <code>LocalDate</code>. */
	private DayOfWeek diaDaSemana;

	/** Lista de intervalos disponíveis para serem atribuídos a algum período de foco ou descanso. */
	private List<Intervalo> intervalos;

	/** Constrói um novo objeto de <code>Dia</code>, a partir dos parâmetros recebidos.
	 * <br><br>
	 * O dia da semana é inferido da data passada como parâmetro.
	 * 
	 * @param _data Data deste dia sendo criado
	 * @param _intervalos Intervalos definidos neste dia
	 */
	public Dia(LocalDate _data, List<Intervalo> _intervalos){
		data = _data;
		diaDaSemana = data.getDayOfWeek();

		intervalos = _intervalos;
	}

	/** Retorna a data a que este <code>Dia</code> se refere.
	 * @return Data referida
	 */
	public LocalDate getData() {
		return data;
	}
	/** Atualiza a data a que este <code>Dia</code> se refere, atualizando também o dia da semana.
	 * @param data Nova data a ser registrada
	 */
	public void setData(LocalDate data) {
		this.data = data;
		this.diaDaSemana = data.getDayOfWeek();
	}

	/** Retorna o dia da semana a que este <code>Dia</code> se refere.
	 * @return Dia da semana referido
	 */
	public DayOfWeek getDiaDaSemana() {
		return diaDaSemana;
	}

	/** Retorna a lista de intervalos criados neste <code>Dia</code>.
	 * @return Lista de intervalos
	 */
	public List<Intervalo> getIntervalos() {
		return intervalos;
	}
	/** Atualiza a lista de intervalos salvos neste <code>Dia</code>.
	 * @param intervalos Nova lista de intervalos a ser registrada
	 */
	public void setIntervalos(List<Intervalo> intervalos) {
		this.intervalos = intervalos;
	}

	/** Adiciona um novo {@link Intervalo} à lista de intervalos. O <code>Intervalo</code> é adicionado ao final da lista.
	 * @param interv Intervalo a ser adicionado. Se o parâmetro for <code>null</code>, nada será feito.
	 */
	public void addIntervalo(Intervalo interv){
		if (interv != null){
			intervalos.add(interv);
		}
	}

	/** Atualiza um {@link Intervalo} específico presente na lista a partir de seu índice.
	 * <br><br>
	 * O <code>indice_intervalo</code> deve estar dentro dos limites da lista de intervalos. A função não fará nada em caso contrário.
	 * 
	 * @param indice_intervalo Índice do intervalo a ser atualizado.
	 * @param interv_novo Nova versão do intervalo. Se esse parâmetro for <code>null</code>, o método é equivalente a {@link Dia#removeIntervalo}.
	 */
	public void updateIntervalo(int indice_intervalo, Intervalo interv_novo){
		if (interv_novo == null){
			removeIntervalo(indice_intervalo);
		} else if (indice_intervalo >= 0 && indice_intervalo < intervalos.size()){
			intervalos.set(indice_intervalo, interv_novo);
		}
	}

	/** Remove um {@link Intervalo} da lista de intervalos a partir de seu índice.
	 * <br><br>
	 * O <code>indice_intervalo</code> deve estar dentro dos limites da lista de intervalos. A função não fará nada em caso contrário.
	 * @param indice_intervalo Índice do intervalo a ser removido.
	 */
	public void removeIntervalo(int indice_intervalo){
		if (indice_intervalo >= 0 && indice_intervalo < intervalos.size()){
			intervalos.remove(indice_intervalo);
		}
	}
}
