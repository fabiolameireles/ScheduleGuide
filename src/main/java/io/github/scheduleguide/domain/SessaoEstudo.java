package io.github.scheduleguide.domain;

import java.time.LocalDateTime;
import java.util.List;

public class SessaoEstudo {
	private String nome;
	private LocalDateTime dataHoraInicio;
	private LocalDateTime dataHoraFim;
	private List<Periodo> periodos;

	public SessaoEstudo(String _nome, LocalDateTime _dataHoraInicio, LocalDateTime _dataHoraFim, List<Periodo> _periodos){
		nome = _nome;
		dataHoraInicio = _dataHoraInicio;
		dataHoraFim = _dataHoraFim;
		periodos = _periodos;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDateTime getDataHoraInicio() {
		return dataHoraInicio;
	}
	public void setDataHoraInicio(LocalDateTime dataHoraInicio) {
		this.dataHoraInicio = dataHoraInicio;
	}

	public LocalDateTime getDataHoraFim() {
		return dataHoraFim;
	}
	public void setDataHoraFim(LocalDateTime dataHoraFim) {
		if (!dataHoraFim.isAfter(dataHoraInicio)){
			throw new IllegalArgumentException("Horário de fim não pode ser anterior ao de início.");
		}
		
		this.dataHoraFim = dataHoraFim;
	}

	public List<Periodo> getPeriodos() {
		return periodos;
	}
	public void setPeriodos(List<Periodo> periodos) {
		this.periodos = periodos;
	}
}
