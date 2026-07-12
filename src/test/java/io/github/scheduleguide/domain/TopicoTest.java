package io.github.scheduleguide.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TopicoTest{

    private Topico topico;

    @Mock
    private Categoria mockCategoria;    
    @Mock
    private Prioridade mockPrioridade;
    @Mock
    private Conteudo mockConteudo1;
    @Mock
    private Conteudo mockConteudo2;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        
        List<Conteudo> conteudos = new ArrayList<>();
        conteudos.add(mockConteudo1);

        topico = new Topico("Topico", "caminho/imagem.png", mockPrioridade, conteudos, mockCategoria);
    }


    // Testando a adição de conteúdos
    @Test
    public void testAddConteudoSucesso(){
        int tamanhoInicial = topico.getConteudos().size();
        
        topico.addConteudo(mockConteudo2);
        
        assertEquals(tamanhoInicial + 1, topico.getConteudos().size());
        
        assertTrue(topico.getConteudos().contains(mockConteudo2));
    }
    @Test
    public void testAddConteudoNuloIgnorado(){
        int tamanhoInicial = topico.getConteudos().size();
        
        topico.addConteudo(null);
        
        assertEquals(tamanhoInicial, topico.getConteudos().size());
    }


    // Testando a atualização de conteúdos
    @Test
    public void testUpdateConteudoSucesso(){
        topico.updateConteudo(0, mockConteudo2);
        
        assertEquals(mockConteudo2, topico.getConteudos().get(0));
    }
    @Test
    public void testUpdateConteudoNuloRemoveItem(){
        int tamanhoInicial = topico.getConteudos().size();
        
        topico.updateConteudo(0, null);
        
        assertEquals(tamanhoInicial - 1, topico.getConteudos().size());
    }
    @Test
    public void testUpdateConteudoIndiceInvalidoIgnorado(){
        int tamanhoInicial = topico.getConteudos().size();
        
        topico.updateConteudo(99, mockConteudo2);
        topico.updateConteudo(-1, mockConteudo2);
        
        assertEquals(tamanhoInicial, topico.getConteudos().size());
    }


    // Testando a remoção de conteúdos
    @Test
    public void testRemoveConteudoSucesso(){
        int tamanhoInicial = topico.getConteudos().size();
        
        topico.removeConteudo(0);
        
        assertEquals(tamanhoInicial - 1, topico.getConteudos().size());
    }
    @Test
    public void testRemoveConteudoIndiceInvalidoIgnorado(){
        int tamanhoInicial = topico.getConteudos().size();
        
        topico.removeConteudo(99);
        topico.removeConteudo(-1);
        
        assertEquals(tamanhoInicial, topico.getConteudos().size());
    }
}