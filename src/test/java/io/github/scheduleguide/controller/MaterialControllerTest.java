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
import static org.hamcrest.Matchers.nullValue;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class MaterialControllerTest {
	@Autowired
	MockMvc mvc;

	@Test
	@Order(1)
	public void testListaMateriaisVazia() throws Exception {
		mvc.perform(get("/interno/materiais").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$").isEmpty());
	}

	@Test
	@Order(2)
	public void testGetMaterialInvalido() throws Exception {
		mvc.perform(get("/interno/materiais/123123").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
	}

	@Test
	@Order(2)
	public void testSetMaterialInvalido() throws Exception {
		mvc.perform(put("/interno/materiais/123123").contentType(MediaType.APPLICATION_JSON)
				.content("{\"nome\":\"Planilha de aulas\",\"link\":\"http://www.example.com\"}"))
		.andExpect(status().isNotFound());
	}
	
	@Test
	@Order(2)
	public void testPostMaterial() throws Exception {
		mvc.perform(post("/interno/materiais").contentType(MediaType.APPLICATION_JSON)
				.content("{\"nome\":\"Folha de exercícios\"}"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.nome").value("Folha de exercícios"))
        .andExpect(jsonPath("$.link").value(nullValue()));

		mvc.perform(post("/interno/materiais").contentType(MediaType.APPLICATION_JSON)
				.content("{\"nome\":\"Videoaula do conteúdo\",\"link\":\"http://www.example.com\"}"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(2))
		.andExpect(jsonPath("$.nome").value("Videoaula do conteúdo"))
        .andExpect(jsonPath("$.link").value("http://www.example.com"));
	}
	
	@Test
	@Order(3)
	public void testGetListaMateriaisComFiltro() throws Exception {
		mvc.perform(get("/interno/materiais").contentType(MediaType.APPLICATION_JSON).param("nome", "Videoaula do conteúdo"))
		.andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$[0].nome").value("Videoaula do conteúdo"))
        .andExpect(jsonPath("$[0].link").value("http://www.example.com"));
	}
	
	@Test
	@Order(3)
	public void testSetMaterial() throws Exception {
		mvc.perform(put("/interno/materiais/1").contentType(MediaType.APPLICATION_JSON)
				.content("{\"nome\":\"Pasta do drive\",\"link\":\"http://www.example.com\"}"))
		.andDo(print())
		.andExpect(status().isOk());
		
		mvc.perform(get("/interno/materiais").contentType(MediaType.APPLICATION_JSON).param("nome", "Pasta do drive"))
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$[0].nome").value("Pasta do drive"))
        .andExpect(jsonPath("$[0].link").value("http://www.example.com"));
	}
		
	@Test
	@Order(4)
	public void testDeleteMaterial() throws Exception
	{
		mvc.perform(get("/interno/materiais/1").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());

		mvc.perform(delete("/interno/materiais/1"))
		.andDo(print())
		.andExpect(status().isOk());

		mvc.perform(get("/interno/materiais/1").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
	}
}
