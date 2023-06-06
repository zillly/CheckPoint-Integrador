package com.checkpoint.clinica;


import com.checkpoint.clinica.controller.dto.DentistaResponse;
import com.checkpoint.clinica.controller.dto.UsuarioRequest;
import com.checkpoint.clinica.controller.dto.UsuarioResponse;
import com.checkpoint.clinica.exeption.InvalidDataException;
import com.checkpoint.clinica.model.Dentista;
import com.checkpoint.clinica.model.Usuario;
import com.checkpoint.clinica.service.impl.DentistaService;
import com.checkpoint.clinica.service.impl.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class IntegrationUsuarioTest {
    @Autowired
	private MockMvc mockMvc;

    @MockBean
	private UsuarioService usuarioService;

	void loadDataSet() throws InvalidDataException {
		this.usuarioService.salvar(new UsuarioRequest("Gilmar","Miranda","123","user"));
	}


    @Test
	void deveCadastrarUsuariovComSucesso() throws Exception{
	Usuario usuario = new Usuario(1,"Gilmar","Miranda","123","user");
	UsuarioResponse usuarioResponse = new UsuarioResponse("Gilmar","Miranda","user");

		Mockito.when(usuarioService.salvar(Mockito.any())).thenReturn(usuarioResponse);

		ObjectWriter writer = new ObjectMapper()
				.configure(SerializationFeature.WRAP_ROOT_VALUE,false)
				.writer();

		String payLoadJson = writer.writeValueAsString(usuario);
		String responseJson = writer.writeValueAsString(usuarioResponse);

		MvcResult mvcResult = mockMvc.perform(post("/usuarios")
						.content(payLoadJson)
				        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(content().contentType("application/json"))
				.andReturn();

	}
	@Test
	void deveAtualizarUsuarioComSucesso() throws Exception{
		UsuarioResponse usuarioResponse = new UsuarioResponse("Gilmar","Miranda","user");
		UsuarioRequest usuarioRequest = new UsuarioRequest("Gilmar","Miranda","123","user");


		Mockito.when(usuarioService.atualizar(Mockito.any())).thenReturn(usuarioResponse);

		ObjectWriter writer = new ObjectMapper()
				.configure(SerializationFeature.WRAP_ROOT_VALUE,false)
				.writer();

		String payLoadJson = writer.writeValueAsString(usuarioRequest);
		String responseJson = writer.writeValueAsString(usuarioResponse);

		MvcResult mvcResult = mockMvc.perform(put("/usuarios")
						.content(payLoadJson)
						.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(content().contentType("application/json"))
				.andReturn();

	}

	@Test
	void deveAtualizarDentistaComFalha() throws Exception{
		UsuarioResponse usuarioResponse = new UsuarioResponse("Gilmar","Miranda","user");
		UsuarioRequest usuarioRequest = new UsuarioRequest("Gilmar","Miranda","123","user");

		Mockito.when(usuarioService.atualizar(Mockito.any())).thenReturn(usuarioResponse);

		ObjectWriter writer = new ObjectMapper()
				.configure(SerializationFeature.WRAP_ROOT_VALUE,true)
				.writer();

		String payLoadJson = writer.writeValueAsString(usuarioRequest);
		String responseJson = writer.writeValueAsString(usuarioResponse);

		MvcResult mvcResult = mockMvc.perform(put("/usuarios")
						.content(payLoadJson)
						.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andExpect(content().contentType("text/plain;charset=UTF-8"))
				.andReturn();

	}



	@Test
	void deveBuscarListaUsuariosExistente() throws Exception {
		this.loadDataSet();
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/usuarios")
		    	.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		Assert.assertFalse(mvcResult.getResponse().getContentAsString().isEmpty());
	}

	@Test
	void deveBuscarUsuarioPorIdSucesso () throws Exception{
		this.loadDataSet();
		mockMvc.perform(MockMvcRequestBuilders.get("/usuarios/{id}",1)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
	}

	@Test
	void deveBuscarListaUsuariosInexistente() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/usuarios")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andReturn();
	}

	@Test
	void deveBuscarUsuarioPorId_Erro () throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/usuarios/{id}",1)
			    .accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andReturn();
	}

	@Test
	void deverUsuarioDeletado_Erro () throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.delete("/usuarios/{id}",1)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andReturn();
	}
	@Test
	void deverUsuarioDeletado_Sucesso () throws Exception{
		this.loadDataSet();
		mockMvc.perform(MockMvcRequestBuilders.delete("/usuarios/{id}",1)
						.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
	}




}
