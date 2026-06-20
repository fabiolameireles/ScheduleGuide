package io.github.scheduleguide.domain;

import java.time.LocalDateTime;
import java.util.List;

/** <i>Documentação da classe Sessão de Estudo.</i>
 * 
 * <p>A classe sessão de estudo engloba um dia de "resultado" do cronograma, sendo composta por vários períodos, 
 * que serão divididos entre períodos de foco (possuindo conteúdo atrelado) e períodos de descanso.</p>
 * 
 * @author Fabiola Meireles Vilaça, Igor Wandekochen Bittencourt, Rafael Vieira de Almeida
 * @see io.github.scheduleguide.domain.Periodo
 * @see io.github.scheduleguide.domain.PeriodoFoco
 * @see io.github.scheduleguide.domain.PeriodoDescanso
 */
public class SessaoEstudo {
	/** Nome desta sessão de estudo. */
	private String nome;

	/** Data e hora de início desta sessão de estudo. */
	private LocalDateTime dataHoraInicio;

	/** Data e hora de fim desta sessão de estudo. */
	private LocalDateTime dataHoraFim;

	/** Períodos que compõem esta sessão de estudo. */
	private List<Periodo> periodos;

	/** Constrói uma nova <code>SessaoEstudo</code>, a partir dos parâmetros recebidos.
	 * 
	 * @param _nome Nome desta sessão de estudo
	 * @param _dataHoraInicio Data e hora de início desta sessão de estudo
	 * @param _dataHoraFim Data e hora de fim desta sessão de estudo
	 * @param _periodos Períodos que compõem esta sessão de estudo
	 */
	public SessaoEstudo(String _nome, LocalDateTime _dataHoraInicio, LocalDateTime _dataHoraFim, List<Periodo> _periodos){
		nome = _nome;
		dataHoraInicio = _dataHoraInicio;
		dataHoraFim = _dataHoraFim;
		periodos = _periodos;
	}

	/** Retorna o nome desta <code>SessaoEstudo</code>
	 * @return Nome registrado
	 */
	public String getNome() {
		return nome;
	}
	/** Atualiza o nome desta <code>SessaoEstudo</code>
	 * @param nome Novo nome a ser salvo
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/** Retorna a data e horário de início desta <code>SessaoEstudo</code>
	 * @return Data e horário de início
	 */
	public LocalDateTime getDataHoraInicio() {
		return dataHoraInicio;
	}
	/** Atualiza a data e horário de início desta <code>SessaoEstudo</code>
	 * @param dataHoraInicio Nova data e horário de início
	 */
	public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	/** Retorna a data e horário de fim desta <code>SessaoEstudo</code>
	 * @return Data e horário de fim
	 */
	public LocalDateTime getDataHoraFim() {
		return dataHoraFim;
	}
	/** Atualiza a data e horário de início desta <code>SessaoEstudo</code>.
	 * <br><br>
	 * Caso a <code>dataHoraFim</code> ocorra antes da data e horário de início salvo, a função
	 * acabará em uma Exceção do tipo <code>IllegalArgumentException</code>.
	 * @param dataHoraFim Nova data e horário de fim
	 */
	public void setDataHoraFim(LocalDateTime dataHoraFim) {
		if (!dataHoraFim.isAfter(dataHoraInicio)){
			throw new IllegalArgumentException("Horário de fim não pode ser anterior ao de início.");
		}
		
		this.dataHoraFim = dataHoraFim;
	}

	/** Retorna a lista de períodos desta <code>SessaoEstudo</code>
	 * @return Lista de peŕiodos
	 */
	public List<Periodo> getPeriodos() {
		return periodos;
	}
	/** Atualiza/Sobrescreve a lista de períodos desta <code>SessaoEstudo</code>
	 * @param periodos Nova lista de períodos
	 */
	public void setPeriodos(List<Periodo> periodos) {
		this.periodos = periodos;
	}

	/** Adiciona um novo {@link Periodo} à lista de períodos. O <code>Periodo</code> é adicionado ao final da lista.
	 * @param per Periodo a ser adicionado. Se o parâmetro for <code>null</code>, nada será feito.
	 */
	public void addPeriodo(Periodo per){
		if (per != null){
			periodos.add(per);
		}
	}

	/** Atualiza um {@link Periodo} específico presente na lista a partir de seu índice.
	 * <br><br>
	 * O <code>indice_periodo</code> deve estar dentro dos limites da lista de períodos. A função não fará nada em caso contrário.
	 * 
	 * @param indice_periodo Índice do período a ser atualizado.
	 * @param per_novo Nova versão do período. Se esse parâmetro for <code>null</code>, o método é equivalente a {@link SessaoEstudo#removePeriodo}.
	 */
	public void updatePeriodo(int indice_periodo, Periodo per_novo){
		if (per_novo == null){
			removePeriodo(indice_periodo);
		} else if (indice_periodo >= 0 && indice_periodo < periodos.size()){
			periodos.set(indice_periodo, per_novo);
		}
	}

	/** Remove um {@link Periodo} da lista de períodos a partir de seu índice.
	 * <br><br>
	 * O <code>indice_periodo</code> deve estar dentro dos limites da lista de períodos. A função não fará nada em caso contrário.
	 * @param indice_periodo Índice do material a ser removido.
	 */
	public void removePeriodo(int indice_periodo){
		if (indice_periodo >= 0 && indice_periodo < periodos.size()){
			periodos.remove(indice_periodo);
		}
	}
}
