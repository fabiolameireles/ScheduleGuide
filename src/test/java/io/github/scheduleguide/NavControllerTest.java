package io.github.scheduleguide;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class NavControllerTest {
    @Autowired
    MockMvc mvc;

    @Test
    void testGetIndex() throws Exception {
        mvc.perform(get("/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void testGetTopicos() throws Exception {
        mvc.perform(get("/topicos").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void testGetCronograma() throws Exception {
        mvc.perform(get("/cronograma").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void testGetRelatorio() throws Exception {
        mvc.perform(get("/relatorio").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    void testGetEstudo() throws Exception {
        mvc.perform(get("/estudo").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
}
