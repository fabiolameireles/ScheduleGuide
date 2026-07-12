package io.github.scheduleguide.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class IntervaloTest{
    private Intervalo intervalo;
    private LocalTime inicioPadrao;

    @BeforeEach
    public void init() {
        inicioPadrao = LocalTime.of(14, 0);
        LocalTime fimPadrao = LocalTime.of(16, 0);
        
        intervalo = new Intervalo("Intervalo", inicioPadrao, fimPadrao, false);
    }

    // Testando o Construtor
    @Test
    public void testConstrutorFimAnteriorAoInicioThrowsException(){
        LocalTime inicio = LocalTime.of(10, 0);
        LocalTime fimInvalido = LocalTime.of(9, 0);
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Intervalo("Intervalo invalido", inicio, fimInvalido, false);
        });
        
        assertEquals("Horário de fim não pode ser anterior ao de início.", exception.getMessage());
    }

    // Testando horário
    @Test
    public void testSetHorarioFimSucesso(){
        // fim após início
        LocalTime novoFim = LocalTime.of(18, 0);
        
        assertDoesNotThrow(() -> intervalo.setHorarioFim(novoFim));
        
        assertEquals(novoFim, intervalo.getHorarioFim());
    }

    @Test
    public void testSetHorarioFimLancaExcecaoQuandoAnteriorOuIgualAoInicio(){
        LocalTime fimInvalido = LocalTime.of(13, 0);
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            intervalo.setHorarioFim(fimInvalido);
        });
        
        assertEquals("Horário de fim não pode ser anterior ao de início.", exception.getMessage());
    }

    // Testando atualizaHorarios
    @Test
    public void testAtualizaHorariosSucesso(){
        LocalTime novoInicio = LocalTime.of(8, 0);
        LocalTime novoFim = LocalTime.of(10, 0);
        
        assertDoesNotThrow(() -> intervalo.atualizaHorarios(novoInicio, novoFim));
        
        assertEquals(novoInicio, intervalo.getHorarioInicio());
        assertEquals(novoFim, intervalo.getHorarioFim());
    }
    @Test
    public void testAtualizaHorariosLancaExcecaoQuandoFimAnteriorAoInicio(){
        LocalTime novoInicio = LocalTime.of(20, 0);
        LocalTime novoFim = LocalTime.of(19, 0);
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            intervalo.atualizaHorarios(novoInicio, novoFim);
        });
        
        assertEquals("Horário de fim não pode ser anterior ao de início.", exception.getMessage());
    }
}