package io.github.scheduleguide.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class Dia {
	private LocalDate data;
	private DayOfWeek diaDaSemana;
	private List<Intervalo> intervalos;

	public Dia(LocalDate _data, List<Intervalo> _intervalos){
		data = _data;
		diaDaSemana = data.getDayOfWeek();

		intervalos = _intervalos;
	}

	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
		this.diaDaSemana = data.getDayOfWeek();
	}

	public DayOfWeek getDiaDaSemana() {
		return diaDaSemana;
	}

	public List<Intervalo> getIntervalos() {
		return intervalos;
	}
	public void setIntervalos(List<Intervalo> intervalos) {
		this.intervalos = intervalos;
	}
}
