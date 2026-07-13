package io.github.scheduleguide.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;

public class MaterialTest{

    @Mock
    private Conteudo mockConteudo;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    // Testando o Optional para link existente
    @Test
    public void testGetLinkQuandoLinkEstaPresente(){
        String linkEsperado = "https://link.com/teste";
    
        Material material = new Material("Material", mockConteudo, linkEsperado);
        
        Optional<String> linkRetornado = material.getLink();
        
        assertTrue(linkRetornado.isPresent());
        
        assertEquals(linkEsperado, linkRetornado.get());
    }

    // Testando Optional para link que NÃO existe (null)
    @Test
    public void testGetLinkQuandoLinkEhNulo(){
        Material material = new Material("Material", mockConteudo);
        
        Optional<String> linkRetornado = material.getLink();
        
        assertTrue(linkRetornado.isEmpty());
    }

    // Testando Construtor (valores padrão, com @JsonCreator)

    @Test
    public void testConstrutorPadrao() {
        Material material = new Material();
        
        assertEquals("", material.getNome(), "O nome padrão deve ser uma string vazia");
        assertNull(material.getConteudo(), "O conteúdo padrão deve ser nulo");
        
        assertTrue(material.getLink().isEmpty());
    }
}