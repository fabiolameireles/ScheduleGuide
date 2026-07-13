package io.github.scheduleguide.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DiaTest{

    private Dia dia;

    @Mock
    private Intervalo mockIntervalo1;

    @Mock
    private Intervalo mockIntervalo2;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        
        LocalDate dataInicial = LocalDate.of(2026, 7, 10);
        
        List<Intervalo> intervalos = new ArrayList<>();
        intervalos.add(mockIntervalo1);

        dia = new Dia(dataInicial, intervalos);
    }

    // Testando a sincronização de Data e Dia
    @Test
    public void testSetDataAtualizaDiaDaSemana(){
        assertEquals(DayOfWeek.FRIDAY, dia.getDiaDaSemana());
        
        // muda a data para o dia seguinte
        LocalDate novaData = LocalDate.of(2026, 7, 11);
        dia.setData(novaData);
        
        assertEquals(DayOfWeek.SATURDAY, dia.getDiaDaSemana());
    }

    // Testando a adição de Intervalos
    @Test
    public void testAddIntervaloSucesso(){
        int tamanhoInicial = dia.getIntervalos().size();
        
        dia.addIntervalo(mockIntervalo2);
        
        assertEquals(tamanhoInicial + 1, dia.getIntervalos().size());
        
        assertTrue(dia.getIntervalos().contains(mockIntervalo2));
    }
    @Test
    public void testAddIntervaloNuloIgnorado(){
        int tamanhoInicial = dia.getIntervalos().size();
        
        dia.addIntervalo(null);
        
        assertEquals(tamanhoInicial, dia.getIntervalos().size());
    }

    // Testando a atualização de Intervalos
    @Test
    public void testUpdateIntervaloSucesso(){
        dia.updateIntervalo(0, mockIntervalo2);
        
        assertEquals(mockIntervalo2, dia.getIntervalos().get(0));
    }
    @Test
    public void testUpdateIntervaloNuloRemoveItem(){
        int tamanhoInicial = dia.getIntervalos().size();
        
        dia.updateIntervalo(0, null);
        
        assertEquals(tamanhoInicial - 1, dia.getIntervalos().size());
    }
    @Test
    public void testUpdateIntervaloIndiceInvalidoIgnorado(){
        int tamanhoInicial = dia.getIntervalos().size();
        
        dia.updateIntervalo(99, mockIntervalo2);
        dia.updateIntervalo(-1, mockIntervalo2);
        
        assertEquals(tamanhoInicial, dia.getIntervalos().size());
    }

    // Testando a remoção de Intervalos
    @Test
    public void testRemoveIntervaloSucesso(){
        int tamanhoInicial = dia.getIntervalos().size();
        
        dia.removeIntervalo(0);
        
        assertEquals(tamanhoInicial - 1, dia.getIntervalos().size());
    }
    @Test
    public void testRemoveIntervaloIndiceInvalidoIgnorado(){
        int tamanhoInicial = dia.getIntervalos().size();
        
        dia.removeIntervalo(99);
        dia.removeIntervalo(-1);
        
        assertEquals(tamanhoInicial, dia.getIntervalos().size());
    }
}