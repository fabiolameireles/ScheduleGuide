package io.github.scheduleguide.controller;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class TopicoControllerTest {
	@Autowired
	MockMvc mvc;

	@Test
	@Order(1)
	public void testListaTopicosVazia() throws Exception {
		mvc.perform(get("/interno/topicos").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$").isEmpty());
	}

	@Test
	@Order(2)
	public void testGetTopicoInvalido() throws Exception {
		mvc.perform(get("/interno/topicos/123123").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
	}

	@Test
	@Order(2)
	public void testSetTopicoInvalido() throws Exception {
		mvc.perform(put("/interno/topicos/123123").contentType(MediaType.APPLICATION_JSON)
				.content("{\"nome\":\"Derivadas\",\"imagem\":\"http://www.example.com\",\"prioridade\":\"BAIXA\"}"))
		.andExpect(status().isNotFound());
	}
	
	@Test
	@Order(2)
	public void testPostTopico() throws Exception {
		mvc.perform(post("/interno/topicos").contentType(MediaType.APPLICATION_JSON)
				.content("{\"nome\":\"Derivadas\"}"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.nome").value("Derivadas"))
        .andExpect(jsonPath("$.imagem").value(""))
		.andExpect(jsonPath("$.prioridade").value("ALTA"))
        .andExpect(jsonPath("$.ativo").value("true"));

		mvc.perform(post("/interno/topicos").contentType(MediaType.APPLICATION_JSON)
				.content("{\"nome\":\"Integrais\",\"imagem\":\"http://www.example.com\",\"prioridade\":\"MEDIA\"}"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(2))
		.andExpect(jsonPath("$.nome").value("Integrais"))
        .andExpect(jsonPath("$.imagem").value("http://www.example.com"))
		.andExpect(jsonPath("$.prioridade").value("MEDIA"))
        .andExpect(jsonPath("$.ativo").value("true"));
	}
	
	@Test
	@Order(3)
	public void testGetListaTopicosComFiltro() throws Exception {
		mvc.perform(get("/interno/topicos").contentType(MediaType.APPLICATION_JSON).param("nome", "Integrais"))
		.andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$[0].nome").value("Integrais"))
        .andExpect(jsonPath("$[0].imagem").value("http://www.example.com"))
		.andExpect(jsonPath("$[0].prioridade").value("MEDIA"))
        .andExpect(jsonPath("$[0].ativo").value("true"));
	}
	
	@Test
	@Order(3)
	public void testSetTopico() throws Exception {
		mvc.perform(put("/interno/topicos/1").contentType(MediaType.APPLICATION_JSON)
				.content("{\"nome\":\"Derivadas\",\"imagem\":\"http://www.example.com\",\"prioridade\":\"BAIXA\"}"))
		.andDo(print())
		.andExpect(status().isOk());
		
		mvc.perform(get("/interno/topicos").contentType(MediaType.APPLICATION_JSON).param("nome", "Derivadas"))
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$[0].nome").value("Derivadas"))
        .andExpect(jsonPath("$[0].imagem").value("http://www.example.com"))
		.andExpect(jsonPath("$[0].prioridade").value("BAIXA"))
        .andExpect(jsonPath("$[0].ativo").value("true"));
	}
		
	@Test
	@Order(4)
	public void testDeleteTopico() throws Exception
	{
		mvc.perform(get("/interno/topicos/1").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());

		mvc.perform(delete("/interno/topicos/1"))
		.andDo(print())
		.andExpect(status().isOk());

		mvc.perform(get("/interno/topicos/1").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
	}
}
