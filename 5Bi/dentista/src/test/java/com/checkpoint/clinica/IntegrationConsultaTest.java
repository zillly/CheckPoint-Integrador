package com.checkpoint.clinica;


import com.checkpoint.clinica.controller.dto.ConsultaResponse;
import com.checkpoint.clinica.controller.dto.DentistaResponse;
import com.checkpoint.clinica.controller.dto.EnderecoResponse;
import com.checkpoint.clinica.controller.dto.PacienteResponse;
import com.checkpoint.clinica.model.Consulta;
import com.checkpoint.clinica.model.Dentista;
import com.checkpoint.clinica.model.Endereco;
import com.checkpoint.clinica.model.Paciente;
import com.checkpoint.clinica.service.impl.ConsultaService;
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
class IntegrationConsultaTest {
    @Autowired
	private MockMvc mockMvc;

    @MockBean
	private ConsultaService consultaService;

	void loadDataSet(){
		Endereco endereco = new Endereco(1,"12 setembro",12,"cachoeira","Ba");
		Paciente paciente = new Paciente(1,"Gilmar","Miranda","123",null,endereco);
		Dentista dentista = new Dentista(1, "Gilmar", "Miranda", 123, null);
		this.consultaService.salvar( new Consulta(1,paciente,dentista,null));

	}


    @Test
	void deveCadastrarConsultaComSucesso() throws Exception{
		Endereco endereco = new Endereco(1,"12 setembro",12,"cachoeira","Ba");
		Paciente paciente = new Paciente(1,"Gilmar","Miranda","123",null,endereco);
		EnderecoResponse enderecoResponse = new EnderecoResponse("12 setembro",12,"cachoeira","Ba");
		PacienteResponse pacienteResponse = new PacienteResponse("Gilmar","Miranda","123",enderecoResponse);
		Dentista dentista = new Dentista(1, "Gilmar", "Miranda", 123, null);
		DentistaResponse dentistaResponse = new DentistaResponse("Gilmar","Miranda",123);
		Consulta consulta = new Consulta(1,paciente,dentista,null);
		ConsultaResponse consultaResponse = new ConsultaResponse(pacienteResponse,dentistaResponse);


		Mockito.when(consultaService.salvar(Mockito.any())).thenReturn(consultaResponse);
		ObjectWriter writer = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE,false).writer();
		String payLoadJson = writer.writeValueAsString(consulta);

		 mockMvc.perform(post("/consultas").content(payLoadJson)
			    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(content().contentType("application/json"))
				.andReturn();
	}
	@Test
	void deveAtualizarConsultaComSucesso() throws Exception{
		Endereco endereco = new Endereco(1,"12 setembro",12,"cachoeira","Ba");
		Paciente paciente = new Paciente(1,"Gilmar","Miranda","123",null,endereco);
		EnderecoResponse enderecoResponse = new EnderecoResponse("12 setembro",12,"cachoeira","Ba");
		PacienteResponse pacienteResponse = new PacienteResponse("Gilmar","Miranda","123",enderecoResponse);
		Dentista dentista = new Dentista(1, "Gilmar", "Miranda", 123, null);
		DentistaResponse dentistaResponse = new DentistaResponse("Gilmar","Miranda",123);
		Consulta consulta = new Consulta(1,paciente,dentista,null);
		ConsultaResponse consultaResponse = new ConsultaResponse(pacienteResponse,dentistaResponse);

		Mockito.when(consultaService.atualizar(Mockito.any())).thenReturn(consultaResponse);
		ObjectWriter writer = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE,false).writer();
		String payLoadJson = writer.writeValueAsString(consulta);

		mockMvc.perform(put("/consultas").content(payLoadJson)
			    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(content().contentType("application/json"))
				.andReturn();
	}

	@Test
	void deveAtualizarConsultaComFalha() throws Exception{
		Endereco endereco = new Endereco(1,"12 setembro",12,"cachoeira","Ba");
		Paciente paciente = new Paciente(1,"Gilmar","Miranda","123",null,endereco);
		EnderecoResponse enderecoResponse = new EnderecoResponse("12 setembro",12,"cachoeira","Ba");
		PacienteResponse pacienteResponse = new PacienteResponse("Gilmar","Miranda","123",enderecoResponse);
		Dentista dentista = new Dentista(1, "Gilmar", "Miranda", 123, null);
		DentistaResponse dentistaResponse = new DentistaResponse("Gilmar","Miranda",123);
		Consulta consulta = new Consulta(1,paciente,dentista,null);
		ConsultaResponse consultaResponse = new ConsultaResponse(pacienteResponse,dentistaResponse);

		Mockito.when(consultaService.atualizar(Mockito.any())).thenReturn(consultaResponse);
		ObjectWriter writer = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE,false   ).writer();
		String payLoadJson = writer.writeValueAsString(consulta);

		mockMvc.perform(put("/consultas").content(payLoadJson)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andExpect(content().contentType("text/plain;charset=UTF-8")).andReturn();
	}
	@Test
	void deveSalvarConsultaComFalha() throws Exception{
		Endereco endereco = new Endereco(1,"12 setembro",12,"cachoeira","Ba");
		Paciente paciente = new Paciente(1,"Gilmar","Miranda","123",null,endereco);
		EnderecoResponse enderecoResponse = new EnderecoResponse("12 setembro",12,"cachoeira","Ba");
		PacienteResponse pacienteResponse = new PacienteResponse("Gilmar","Miranda","123",enderecoResponse);
		Dentista dentista = new Dentista(1, "Gilmar", "Miranda", 123, null);
		DentistaResponse dentistaResponse = new DentistaResponse("Gilmar","Miranda",123);
		Consulta consulta = new Consulta(1,paciente,dentista,null);
		ConsultaResponse consultaResponse = new ConsultaResponse(pacienteResponse,dentistaResponse);

		Mockito.when(consultaService.salvar(Mockito.any())).thenReturn(consultaResponse);
		ObjectWriter writer = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE,false).writer();
		String payLoadJson = writer.writeValueAsString(consulta);

		mockMvc.perform(put("/consultas").content(payLoadJson)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andExpect(content().contentType("text/plain;charset=UTF-8")).andReturn();

	}



	@Test
	void deveBuscarListaConsultasExistente() throws Exception {
		this.loadDataSet();
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/consultas")
		    	.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		Assert.assertFalse(mvcResult.getResponse().getContentAsString().isEmpty());
	}

	@Test
	void deveBuscarConsultaPorIdSucesso () throws Exception{
		this.loadDataSet();
		mockMvc.perform(MockMvcRequestBuilders.get("/consultas/{id}",1)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
	}

	@Test
	void deveBuscarListaConsultasInexistente() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/consultas")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andReturn();
	}

	@Test
	void deveBuscarConsultaPorId_Erro () throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/consultas/{id}",1)
			    .accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andReturn();
	}

	@Test
	void deverConsultaDeletado_Erro () throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.delete("/pacientes/{id}",1)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andReturn();
	}
	@Test
	void deverConsultaDeletado_Sucesso () throws Exception{
		this.loadDataSet();
		mockMvc.perform(MockMvcRequestBuilders.delete("/pacientes/{id}",1)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
	}






}
