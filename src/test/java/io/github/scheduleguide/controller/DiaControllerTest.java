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
public class DiaControllerTest {
	@Autowired
	MockMvc mvc;

	@Test
	@Order(1)
	public void testListaDiasVazia() throws Exception {
		mvc.perform(get("/interno/dias").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
        .andDo(print())
        .andExpect(jsonPath("$").isEmpty());
	}

	@Test
	@Order(2)
	public void testGetDiaInvalido() throws Exception {
		mvc.perform(get("/interno/dias/123123").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
	}

	@Test
	@Order(2)
	public void testSetDiaInvalido() throws Exception {
		mvc.perform(put("/interno/dias/123123").contentType(MediaType.APPLICATION_JSON)
				.content("{\"data\":\"2026-07-01\"}"))
		.andExpect(status().isNotFound());
	}
	
	@Test
	@Order(2)
	public void testPostDia() throws Exception {
		mvc.perform(post("/interno/dias").contentType(MediaType.APPLICATION_JSON)
				.content("{\"data\":\"2026-07-01\"}"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.data").value("2026-07-01"))
		.andExpect(jsonPath("$.diaDaSemana").value("WEDNESDAY"));
		
		mvc.perform(post("/interno/dias").contentType(MediaType.APPLICATION_JSON)
		.content("{\"data\":\"2026-07-02\"}"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(2))
		.andExpect(jsonPath("$.data").value("2026-07-02"))
		.andExpect(jsonPath("$.diaDaSemana").value("THURSDAY"));
	}
	
	@Test
	@Order(3)
	public void testSetDia() throws Exception {
		mvc.perform(put("/interno/dias/1").contentType(MediaType.APPLICATION_JSON)
				.content("{\"data\":\"2027-06-30\"}"))
		.andDo(print())
		.andExpect(status().isOk());
		
		mvc.perform(get("/interno/dias").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$[0].data").value("2027-06-30"));
	}
		
	@Test
	@Order(4)
	public void testDeleteDia() throws Exception
	{
		mvc.perform(get("/interno/dias/1").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());

		mvc.perform(delete("/interno/dias/1"))
		.andDo(print())
		.andExpect(status().isOk());

		mvc.perform(get("/interno/dias/1").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
	}
}
