package io.github.scheduleguide.domain;

import java.time.LocalTime;

public class Intervalo {
	private String nome;
	private LocalTime horarioInicio;
	private LocalTime horarioFim;
	private boolean tempoReserva;

	public Intervalo(String _nome, LocalTime _horarioInicio, LocalTime _horarioFim, boolean _tempoReserva){
		nome = _nome;

		horarioInicio = _horarioInicio;
		setHorarioFim(_horarioFim);

		tempoReserva = _tempoReserva;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalTime getHorarioInicio() {
		return horarioInicio;
	}
	public void setHorarioInicio(LocalTime horarioInicio) {
		this.horarioInicio = horarioInicio;
	}

	public LocalTime getHorarioFim() {
		return horarioFim;
	}
	public void setHorarioFim(LocalTime horarioFim) {
		if (!horarioFim.isAfter(horarioInicio)) {
			throw new IllegalArgumentException("Horário de fim não pode ser anterior ao de início.");
		}

		this.horarioFim = horarioFim;
	}

	public boolean isTempoReserva() {
		return tempoReserva;
	}
	public void setTempoReserva(boolean tempoReserva) {
		this.tempoReserva = tempoReserva;
	}
}
