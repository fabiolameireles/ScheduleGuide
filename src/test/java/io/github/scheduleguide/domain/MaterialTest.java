package io.github.scheduleguide.domain;

import org.junit.jupiter.api.Test;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;

public class MaterialTest{

    // Testando o Optional para link existente
    @Test
    public void testGetLinkQuandoLinkEstaPresente(){
        String linkEsperado = "https://link.com/teste";
    
        Material material = new Material("Material", linkEsperado);
        
        Optional<String> linkRetornado = material.getLink();
        
        assertTrue(linkRetornado.isPresent());
        
        assertEquals(linkEsperado, linkRetornado.get());
    }

    // Testando Optional para link que NÃO existe (null)
    @Test
    public void testGetLinkQuandoLinkEhNulo(){
        Material material = new Material("Material");
        
        Optional<String> linkRetornado = material.getLink();
        
        assertTrue(linkRetornado.isEmpty());
    }
}