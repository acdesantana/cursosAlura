package br.com.alura.forum.controller;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


/**
 * exemplo de teste end-to-end/
 *
 */
//@WebMvcTest // carrega no contexto da aplicação só as classes da parte MVC do projeto mas nao usaremos aqui pq pra testar a parte de autenticacao precisa carregar AutenticacaoService, que esta fora da camada de controller
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AutenticacaoControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void badRequest_http400_Test() throws Exception {
		URI uri = new URI("/auth");
		String json = "{\"email\":\"invalido@email.com\", \"senha\":\"123456\"}";
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status()
				.is(400));
	}

}
