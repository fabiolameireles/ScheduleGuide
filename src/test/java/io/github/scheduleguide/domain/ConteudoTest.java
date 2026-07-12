package io.github.scheduleguide.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ConteudoTest{

    private Conteudo conteudo;

    @Mock
    private Topico mockTopico;
    @Mock
    private Material mockMaterial1;
    @Mock
    private Material mockMaterial2;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        
        List<Material> materiais = new ArrayList<>();
        materiais.add(mockMaterial1);

        conteudo = new Conteudo("Conteudo", 5, "revisar conteudo", materiais, mockTopico);
    }

    // Testando as regras do Nível de Domínio
    @Test
    public void testSetNivelDeDominioDentroDoLimite(){
        conteudo.setNivelDeDominio(7);
        assertEquals(7, conteudo.getNivelDeDominio());
    }
    @Test
    public void testSetNivelDeDominioAbaixoDoLimiteDeveLimitarAZero(){
        conteudo.setNivelDeDominio(-5);
        
        assertEquals(0, conteudo.getNivelDeDominio());
    }
    @Test
    public void testSetNivelDeDominioAcimaDoLimiteDeveLimitarADez(){
        conteudo.setNivelDeDominio(15);
        
        assertEquals(10, conteudo.getNivelDeDominio());
    }

    // Testando a adição de Materiais
    @Test
    public void testAddMaterialSucesso(){
        int tamanhoInicial = conteudo.getMateriais().size();
        
        conteudo.addMaterial(mockMaterial2);
        
        assertEquals(tamanhoInicial + 1, conteudo.getMateriais().size());
        assertTrue(conteudo.getMateriais().contains(mockMaterial2));
    }
    @Test
    public void testAddMaterialNuloIgnorado(){
        int tamanhoInicial = conteudo.getMateriais().size();
        
        conteudo.addMaterial(null);
        
        assertEquals(tamanhoInicial, conteudo.getMateriais().size());
    }

    // Testando a atualização de Materiais
    @Test
    public void testUpdateMaterialSucesso(){
        conteudo.updateMaterial(0, mockMaterial2);
        
        assertEquals(mockMaterial2, conteudo.getMateriais().get(0));
    }
    @Test
    public void testUpdateMaterialNuloRemoveItem(){
        int tamanhoInicial = conteudo.getMateriais().size();
        
        conteudo.updateMaterial(0, null);
        
        assertEquals(tamanhoInicial - 1, conteudo.getMateriais().size());
    }
    @Test
    public void testUpdateMaterialIndiceInvalidoIgnorado(){
        int tamanhoInicial = conteudo.getMateriais().size();
        
        conteudo.updateMaterial(99, mockMaterial2);
        conteudo.updateMaterial(-1, mockMaterial2);
        
        assertEquals(tamanhoInicial, conteudo.getMateriais().size());
    }

    // Testando a remoção de Materiais
    @Test
    public void testRemoveMaterialSucesso(){
        int tamanhoInicial = conteudo.getMateriais().size();
        
        conteudo.removeMaterial(0);
        
        assertEquals(tamanhoInicial - 1, conteudo.getMateriais().size());
    }
    @Test
    public void testRemoveMaterialIndiceInvalidoIgnorado(){
        int tamanhoInicial = conteudo.getMateriais().size();
        
        conteudo.removeMaterial(99);
        conteudo.removeMaterial(-1);
        
        assertEquals(tamanhoInicial, conteudo.getMateriais().size());
    }
}