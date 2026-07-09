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
public class ConteudoControllerTest {
	@Autowired
	MockMvc mvc;

	@Test
	@Order(1)
	public void testListaConteudosVazia() throws Exception {
		mvc.perform(get("/interno/conteudos").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$").isEmpty());
	}

	@Test
	@Order(2)
	public void testGetConteudoInvalido() throws Exception {
		mvc.perform(get("/interno/conteudos/123123").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
	}

	@Test
	@Order(2)
	public void testSetConteudoInvalido() throws Exception {
		mvc.perform(put("/interno/conteudos/123123").contentType(MediaType.APPLICATION_JSON)
				.content("{\"nome\":\"Função Quadrática\"}"))
		.andExpect(status().isNotFound());
	}
	
	@Test
	@Order(2)
	public void testPostConteudo() throws Exception {
		mvc.perform(post("/interno/conteudos").contentType(MediaType.APPLICATION_JSON)
				.content("{\"nome\":\"Função Quadrática\", \"ativo\":false}"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.nome").value("Função Quadrática"))
        .andExpect(jsonPath("$.nivelDeDominio").value("0"))
        .andExpect(jsonPath("$.anotacoes").value(""))
        .andExpect(jsonPath("$.ativo").value("false"));

		mvc.perform(post("/interno/conteudos").contentType(MediaType.APPLICATION_JSON)
				.content("{\"nome\":\"Função Linear\", \"nivelDeDominio\":8}"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(2))
		.andExpect(jsonPath("$.nome").value("Função Linear"))
        .andExpect(jsonPath("$.nivelDeDominio").value("8"))
        .andExpect(jsonPath("$.anotacoes").value(""))
        .andExpect(jsonPath("$.ativo").value("true"));
	}
	
	@Test
	@Order(3)
	public void testGetListaConteudoComFiltro() throws Exception {
		mvc.perform(get("/interno/conteudos").contentType(MediaType.APPLICATION_JSON).param("nome", "Função Linear"))
		.andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$[0].nome").value("Função Linear"));
	}
	
	@Test
	@Order(3)
	public void testSetConteudo() throws Exception {
		mvc.perform(put("/interno/conteudos/1").contentType(MediaType.APPLICATION_JSON)
				.content("{\"nome\":\"Função Polinomial\"}"))
		.andDo(print())
		.andExpect(status().isOk());
		
		mvc.perform(get("/interno/conteudos").contentType(MediaType.APPLICATION_JSON).param("nome", "Função Polinomial"))
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$[0].nome").value("Função Polinomial"));
	}
		
	@Test
	@Order(4)
	public void testDeleteConteudo() throws Exception
	{
		mvc.perform(get("/interno/conteudos/1").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());

		mvc.perform(delete("/interno/conteudos/1"))
		.andDo(print())
		.andExpect(status().isOk());

		mvc.perform(get("/interno/conteudos/1").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
	}
}
