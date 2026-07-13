package io.github.scheduleguide.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SessaoEstudoTest{

    private SessaoEstudo sessaoEstudo;
    private LocalDateTime inicioPadrao;

    // dublês de Periodo
    @Mock
    private Periodo mockPeriodo1;
    @Mock
    private Periodo mockPeriodo2;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        
        inicioPadrao = LocalDateTime.of(2026, 7, 2, 10, 0);
        LocalDateTime fimPadrao = LocalDateTime.of(2026, 7, 2, 12, 0);
        
        List<Periodo> periodos = new ArrayList<>();
        periodos.add(mockPeriodo1);

        sessaoEstudo = new SessaoEstudo("Sessao de Estudo", inicioPadrao, fimPadrao, periodos);
    }


    // Testando restrição de horário de fim
    @Test
    public void testSetDataHoraFimSucesso(){
        // fim após o início (válido)
        LocalDateTime novoFim = inicioPadrao.plusHours(3);
        
        // não vaza exceção se o comportamento estiver correto
        assertDoesNotThrow(() -> sessaoEstudo.setDataHoraFim(novoFim));
        
        // conferindo se o valor foi salvo
        assertEquals(novoFim, sessaoEstudo.getDataHoraFim());
    }
    @Test
    public void testSetDataHoraFimException(){
        // fim antes do início (inválido)
        LocalDateTime fimInvalido = inicioPadrao.minusHours(1);
        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            sessaoEstudo.setDataHoraFim(fimInvalido);
        });
        
        assertEquals("Horário de fim não pode ser anterior ao de início.", exception.getMessage());
    }


    // Testando a adição de Períodos
    @Test
    public void testAddPeriodoSucesso(){
        int tamanhoInicial = sessaoEstudo.getPeriodos().size();
        
        // adiciona mais um período (esse é o segundo)
        sessaoEstudo.addPeriodo(mockPeriodo2);
        
        // verifica se a lista aumentou
        assertEquals(tamanhoInicial + 1, sessaoEstudo.getPeriodos().size());
        
        // verifica se está na lista
        assertTrue(sessaoEstudo.getPeriodos().contains(mockPeriodo2));
    }
    @Test
    public void testAddPeriodoNuloIgnorado(){
        int tamanhoInicial = sessaoEstudo.getPeriodos().size();
        
        sessaoEstudo.addPeriodo(null);
        
        // verifica se o tamanho da lista não mudou
        assertEquals(tamanhoInicial, sessaoEstudo.getPeriodos().size());
    }


    // Testando a atualização de Períodos
    @Test
    public void testUpdatePeriodoSucesso(){
        // substitui o 0-ésimo período por outro
        sessaoEstudo.updatePeriodo(0, mockPeriodo2);
        
        assertEquals(mockPeriodo2, sessaoEstudo.getPeriodos().get(0));
    }
    @Test
    public void testUpdatePeriodoNuloRemoveItem(){
        int tamanhoInicial = sessaoEstudo.getPeriodos().size();
        
        // se o substituto for nulo, o 0-ésimo período tem que ser removido
        sessaoEstudo.updatePeriodo(0, null);
        
        assertEquals(tamanhoInicial - 1, sessaoEstudo.getPeriodos().size());
    }
    @Test
    public void testUpdatePeriodoIndiceInvalidoIgnorado(){
        int tamanhoInicial = sessaoEstudo.getPeriodos().size();
        
        // tenta atualizar índices que não existem
        sessaoEstudo.updatePeriodo(99, mockPeriodo2);
        sessaoEstudo.updatePeriodo(-1, mockPeriodo2);
        
        // tamanho da lista não muda
        assertEquals(tamanhoInicial, sessaoEstudo.getPeriodos().size());
    }


    // Testando a remoção de Períodos
    @Test
    public void testRemovePeriodoSucesso(){
        int tamanhoInicial = sessaoEstudo.getPeriodos().size();
        
        sessaoEstudo.removePeriodo(0);
        
        assertEquals(tamanhoInicial - 1, sessaoEstudo.getPeriodos().size());
    }
    @Test
    public void testRemovePeriodoIndiceInvalidoIgnorado(){
        int tamanhoInicial = sessaoEstudo.getPeriodos().size();
        
        sessaoEstudo.removePeriodo(99);
        sessaoEstudo.removePeriodo(-1);
        
        // tamanho da lista não muda
        assertEquals(tamanhoInicial, sessaoEstudo.getPeriodos().size());
    }
}