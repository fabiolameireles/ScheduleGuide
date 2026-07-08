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
public class CategoriaControllerTest {
	@Autowired
	MockMvc mvc;

	@Test
	@Order(1)
	public void testListaCategoriasVazia() throws Exception {
		mvc.perform(get("/interno/categorias").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$").isEmpty());
	}

	@Test
	@Order(2)
	public void testGetCategoriaInvalida() throws Exception {
		mvc.perform(get("/interno/categorias/123123").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
	}

	@Test
	@Order(2)
	public void testSetCategoriaInvalida() throws Exception {
		mvc.perform(put("/interno/categorias/123123").contentType(MediaType.APPLICATION_JSON)
				.content("{\"nome\":\"Ciências Exatas\"}"))
		.andExpect(status().isNotFound());
	}
	
	@Test
	@Order(2)
	public void testPostCategoria() throws Exception {
		mvc.perform(post("/interno/categorias").contentType(MediaType.APPLICATION_JSON)
				.content("{\"nome\":\"Ciências Exatas\"}"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.nome").value("Ciências Exatas"));

		mvc.perform(post("/interno/categorias").contentType(MediaType.APPLICATION_JSON)
				.content("{\"nome\":\"Linguagens\"}"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(2))
		.andExpect(jsonPath("$.nome").value("Linguagens"));
	}
	
	@Test
	@Order(3)
	public void testGetListaCategoriaComFiltro() throws Exception {
		mvc.perform(get("/interno/categorias").contentType(MediaType.APPLICATION_JSON).param("nome", "Linguagens"))
		.andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$[0].nome").value("Linguagens"));
	}
	
	@Test
	@Order(3)
	public void testSetCategoria() throws Exception {
		mvc.perform(put("/interno/categorias/1").contentType(MediaType.APPLICATION_JSON)
				.content("{\"nome\":\"Ciências da Natureza\"}"))
		.andDo(print())
		.andExpect(status().isOk());
		
		mvc.perform(get("/interno/categorias").contentType(MediaType.APPLICATION_JSON).param("nome", "Ciências da Natureza"))
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$[0].nome").value("Ciências da Natureza"));
	}
		
	@Test
	@Order(4)
	public void testDeleteCategoria() throws Exception
	{
		mvc.perform(get("/interno/categorias/1").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());

		mvc.perform(delete("/interno/categorias/1"))
		.andDo(print())
		.andExpect(status().isOk());

		mvc.perform(get("/interno/categorias/1").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
	}
}
