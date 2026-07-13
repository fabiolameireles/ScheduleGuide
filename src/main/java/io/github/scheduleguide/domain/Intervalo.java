package io.github.scheduleguide.domain;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

/** <i>Documentação da classe Intervalo.</i>
 * 
 * <p>A classe intervalo define uma faixa de tempo, dentro de um dia, que pode ser usada
 * durante a criação de uma sessão de estudo a partir do algoritmo utilizado no sistema.</p>
 * 
 * @author Fabiola Meireles Vilaça, Igor Wandekochen Bittencourt, Rafael Vieira de Almeida
 * @see io.github.scheduleguide.domain.Dia
 * @see io.github.scheduleguide.domain.SessaoEstudo
 */
@Entity
public class Intervalo {
	/** Identificador deste conteúdo, para armazenamento no banco de dados. */
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	/** Nome deste intervalo, para apresentação ao usuário e apoio à organização. */
	private String nome;

	/** Horário de início deste intervalo. */
	private LocalTime horarioInicio;

	/** Horário de fim deste intervalo. */
	private LocalTime horarioFim;

	/** Definição sobre este intervalo ser um tempo reserva. Um tempo reserva não é
	 * inicialmente atribuído para sessões de estudo, sendo utilizado somente quando
	 * fica algum conteúdo pendente de alguma sessão de estudo anterior.
	 */
	private boolean tempoReserva;

	/** Dia a que esse intervalo está atrelado */	
	@ManyToOne
	private Dia dia;

	/**
	 * Constrói um novo objeto da classe <code>Intervalo</code>, com os valores padrão.
	 * <p>
	 * Este <code>Intervalo</code> é construído com nome vazio, horários iguais ao atual
	 * e não sendo um tempo reserva
	 * <p>
	 * Este construtor é utilizado na leitura de requisições.
	 * 
	 */
	@JsonCreator
	public Intervalo() {
		nome = "";
		horarioInicio = LocalTime.now();
		horarioFim = LocalTime.now();
		tempoReserva = false;
	}

	/** Constrói um novo <code>Intervalo</code>, a partir dos parâmetros recebidos.
	 * 
	 * @param _nome Nome deste intervalo, para apresentação ao usuário.
	 * @param _horarioInicio Horário de início deste intervalo.
	 * @param _horarioFim Horário de fim deste intervalo. Precisa ser depois do horário de início, ou resultará numa Exceção.
	 * @param _tempoReserva Estado deste intervalo, se é de tempo reserva ou não.
	 */
	public Intervalo(String _nome, LocalTime _horarioInicio, LocalTime _horarioFim, boolean _tempoReserva){
		nome = _nome;

		horarioInicio = _horarioInicio;
		setHorarioFim(_horarioFim);

		tempoReserva = _tempoReserva;
	}

	/** Retorna o identificador deste <code>Intervalo</code>.
	 * <p>
	 * Esse valor será utilizado como identificador deste <code>Intervalo</code> em requisições.
	 * @return Identificador deste <code>Intervalo</code>
	 */
	public long getId() {
		return id;
	}
	/** Atualiza o identificador deste <code>Intervalo</code>.
	 * <p>
	 * Um valor novo corresponderá a uma entrada diferente no banco de dados e objetos associados.
	 * @param id Identificador a ser atualizado
	 */
	public void setId(long id) {
		this.id = id;
	}

	/** Retorna o nome salvo deste <code>Intervalo</code>, para apresentação ao usuário
	 * @return Nome deste <code>Intervalo</code>
	 */
	public String getNome() {
		return nome;
	}
	/** Atualiza o nome deste <code>Intervalo</code>, para apresentação ao usuário
	 * @param nome Novo nome a ser registrado
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/** Retorna o horário de início deste <code>Intervalo</code>.
	 * @return Horário de início registrado.
	 */
	public LocalTime getHorarioInicio() {
		return horarioInicio;
	}
	/** Atualiza o horário de início deste <code>Intervalo</code>.
	 * @param horarioInicio Novo horário de início
	 */
	public void setHorarioInicio(LocalTime horarioInicio) {
		this.horarioInicio = horarioInicio;
	}

	/** Retorna o horário de fim deste <code>Intervalo</code>.
	 * @return Horário de fim registrado.
	 */
	public LocalTime getHorarioFim() {
		return horarioFim;
	}
	/** Atualiza o horário de fim deste <code>Intervalo</code>.
	 * <br><br>
	 * Caso o <code>horarioFim</code> ocorra antes do horário de início salvo, a função
	 * acabará em uma Exceção do tipo <code>IllegalArgumentException</code>.
	 * 
	 * @param horarioFim Novo horário de fim
	 */
	public void setHorarioFim(LocalTime horarioFim) {
		if (!horarioFim.isAfter(horarioInicio)) {
			throw new IllegalArgumentException("Horário de fim não pode ser anterior ao de início.");
		}

		this.horarioFim = horarioFim;
	}

	/** Define os novos horários de início e fim deste <code>Intervalo</code>.
	 * <br><br>
	 * Caso o <code>novoHorarioFim</code> ocorra antes do <code>novoHorarioInicio</code>, a função
	 * acabará em uma Exceção do tipo <code>IllegalArgumentException</code>.
	 * 
	 * @param novoHorarioInicio Novo horário de início
	 * @param novoHorarioFim Novo horário de fim
	 */
	public void atualizaHorarios(LocalTime novoHorarioInicio, LocalTime novoHorarioFim) {
		setHorarioInicio(novoHorarioInicio);
		setHorarioFim(novoHorarioFim);
	}

	/** Retorna se este <code>Intervalo</code> está definido como tempo reserva.
	 * @return <code>true</code> se for tempo reserva, <code>false</code> em caso contrário.
	 */
	public boolean isTempoReserva() {
		return tempoReserva;
	}
	/** Atualiza o estado de tempo reserva deste <code>Intervalo</code>.
	 * @param tempoReserva Novo valor para o estado.
	 */
	public void setTempoReserva(boolean tempoReserva) {
		this.tempoReserva = tempoReserva;
	}

	/** Retorna o dia relacionado a esse <code>Intervalo</code>
	 * 
	 * @return Dia relacionado
	 */
	@JsonIgnore
	public Dia getDia(){
		return dia;
	}

	/** Atualiza o dia relacionado a esse <code>Intervalo</code>.
	 * 
	 * @param d Novo dia a ser atualizado
	 */
	@JsonProperty("dia")
	public void setDia(Dia d){
		dia = d;
	}
}
