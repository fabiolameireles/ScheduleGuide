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
public class IntervaloControllerTest {
	@Autowired
	MockMvc mvc;

	@Test
	@Order(1)
	public void testListaIntervaloVazia() throws Exception {
		mvc.perform(get("/interno/intervalos").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$").isEmpty());
	}

	@Test
	@Order(2)
	public void testGetIntervaloInvalido() throws Exception {
		mvc.perform(get("/interno/intervalos/123123").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
	}

	@Test
	@Order(2)
	public void testSetIntervaloInvalido() throws Exception {
		mvc.perform(put("/interno/intervalos/123123").contentType(MediaType.APPLICATION_JSON)
				.content("{\"nome\":\"Antes do jantar\"}"))
		.andExpect(status().isNotFound());
	}
	
	@Test
	@Order(2)
	public void testPostIntervalo() throws Exception {
		mvc.perform(post("/interno/intervalos").contentType(MediaType.APPLICATION_JSON)
				.content("{\"nome\":\"Antes do Jantar\"}"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.nome").value("Antes do Jantar"));

		mvc.perform(post("/interno/intervalos").contentType(MediaType.APPLICATION_JSON)
				.content("{\"nome\":\"Depois do Almoço\"}"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(2))
		.andExpect(jsonPath("$.nome").value("Depois do Almoço"));
	}
	
	@Test
	@Order(3)
	public void testGetListaIntervaloComFiltro() throws Exception {
		mvc.perform(get("/interno/intervalos").contentType(MediaType.APPLICATION_JSON).param("nome", "Antes do Jantar"))
		.andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$[0].nome").value("Antes do Jantar"));
	}
	
	@Test
	@Order(3)
	public void testSetIntervalo() throws Exception {
		mvc.perform(put("/interno/intervalos/1").contentType(MediaType.APPLICATION_JSON)
				.content("{\"nome\":\"Antes de dormir\"}"))
		.andDo(print())
		.andExpect(status().isOk());
		
		mvc.perform(get("/interno/intervalos").contentType(MediaType.APPLICATION_JSON).param("nome", "Antes de dormir"))
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$[0].nome").value("Antes de dormir"));
	}
		
	@Test
	@Order(4)
	public void testDeleteCategoria() throws Exception
	{
		mvc.perform(get("/interno/intervalos/1").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());

		mvc.perform(delete("/interno/intervalos/1"))
		.andDo(print())
		.andExpect(status().isOk());

		mvc.perform(get("/interno/intervalos/1").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
	}
}
