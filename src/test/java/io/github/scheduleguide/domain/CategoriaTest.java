package io.github.scheduleguide.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CategoriaTest{

    private Categoria categoria;

    @Mock
    private Topico mockTopico1;
    @Mock
    private Topico mockTopico2;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        
        categoria = new Categoria("Categoria");
        
        // construtor sem lista
        List<Topico> topicos = new ArrayList<>();
        topicos.add(mockTopico1);
        categoria.setTopicos(topicos); 
    }

    // Testando Construtor
    @Test
    public void testConstrutorDeveInicializarListaDeTopicosVazia(){
        Categoria categoriaNova = new Categoria("Categoria nova");
        
        assertNotNull(categoriaNova.getTopicos(), "A lista de tópicos não deveria ser nula após a construção");
        assertTrue(categoriaNova.getTopicos().isEmpty(), "A lista de tópicos deveria começar vazia");
    }

    // Testando a adição de Tópicos
    @Test
    public void testAddTopicoSucesso(){
        int tamanhoInicial = categoria.getTopicos().size();
        
        categoria.addTopico(mockTopico2);
        
        assertEquals(tamanhoInicial + 1, categoria.getTopicos().size());
        assertTrue(categoria.getTopicos().contains(mockTopico2));
    }
    @Test
    public void testAddTopicoNuloIgnorado(){
        int tamanhoInicial = categoria.getTopicos().size();
        
        categoria.addTopico(null);
        
        assertEquals(tamanhoInicial, categoria.getTopicos().size());
    }

    // Testando a atualização de Tópicos
    @Test
    public void testUpdateTopicoSucesso(){
        categoria.updateTopico(0, mockTopico2);
        
        assertEquals(mockTopico2, categoria.getTopicos().get(0));
    }
    @Test
    public void testUpdateTopicoNuloRemoveItem(){
        int tamanhoInicial = categoria.getTopicos().size();
        
        categoria.updateTopico(0, null);
        
        assertEquals(tamanhoInicial - 1, categoria.getTopicos().size());
    }
    @Test
    public void testUpdateTopicoIndiceInvalidoIgnorado(){
        int tamanhoInicial = categoria.getTopicos().size();
        
        categoria.updateTopico(99, mockTopico2);
        categoria.updateTopico(-1, mockTopico2);
        
        assertEquals(tamanhoInicial, categoria.getTopicos().size());
    }

    // Testando a remoção de Tópicos
    @Test
    public void testRemoveTopicoSucesso(){
        int tamanhoInicial = categoria.getTopicos().size();
        
        categoria.removeTopico(0);
        
        assertEquals(tamanhoInicial - 1, categoria.getTopicos().size());
    }
    @Test
    public void testRemoveTopicoIndiceInvalidoIgnorado(){
        int tamanhoInicial = categoria.getTopicos().size();
        
        categoria.removeTopico(99);
        categoria.removeTopico(-1);
        
        assertEquals(tamanhoInicial, categoria.getTopicos().size());
    }
}