package com.checkpoint.clinica;


import com.checkpoint.clinica.controller.dto.DentistaResponse;
import com.checkpoint.clinica.controller.dto.EnderecoResponse;
import com.checkpoint.clinica.controller.dto.PacienteResponse;
import com.checkpoint.clinica.model.Dentista;
import com.checkpoint.clinica.model.Endereco;
import com.checkpoint.clinica.model.Paciente;
import com.checkpoint.clinica.service.impl.PacienteService;
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
class IntegrationPacienteTest {
    @Autowired
	private MockMvc mockMvc;

    @MockBean
	private PacienteService pacienteService;

	void loadDataSet(){
		Endereco endereco = new Endereco(1,"12 setembro",12,"cachoeira","Ba");
		this.pacienteService.salvar(new Paciente(1,"Gilmar","Miranda","123",null,endereco));

	}


    @Test
	void deveCadastrarPacienteComSucesso() throws Exception{
		Endereco endereco = new Endereco(1,"12 setembro",12,"cachoeira","Ba");
		Paciente paciente = new Paciente(1,"Gilmar","Miranda","123",null,endereco);
		EnderecoResponse enderecoResponse = new EnderecoResponse("12 setembro",12,"cachoeira","Ba");
		PacienteResponse pacienteResponse = new PacienteResponse("Gilmar","Miranda","123",enderecoResponse);


		Mockito.when(pacienteService.salvar(Mockito.any())).thenReturn(pacienteResponse);
		ObjectWriter writer = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE,false).writer();
		String payLoadJson = writer.writeValueAsString(paciente);

		 mockMvc.perform(post("/pacientes").content(payLoadJson)
			    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(content().contentType("application/json"))
				.andReturn();
	}
	@Test
	void deveAtualizarPacienteComSucesso() throws Exception{
		Endereco endereco = new Endereco(1,"12 setembro",12,"cachoeira","Ba");
		Paciente paciente = new Paciente(1,"Gilmar","Miranda","123",null,endereco);
		EnderecoResponse enderecoResponse = new EnderecoResponse("12 setembro",12,"cachoeira","Ba");
		PacienteResponse pacienteResponse = new PacienteResponse("Gilmar","Miranda","123",enderecoResponse);

		Mockito.when(pacienteService.atualizar(Mockito.any())).thenReturn(pacienteResponse);
		ObjectWriter writer = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE,false).writer();
		String payLoadJson = writer.writeValueAsString(paciente);

		mockMvc.perform(put("/pacientes").content(payLoadJson)
			    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(content().contentType("application/json"))
				.andReturn();
	}

	@Test
	void deveAtualizarPacienteComFalha() throws Exception{
		Endereco endereco = new Endereco(1,"12 setembro",12,"cachoeira","Ba");
		Paciente paciente = new Paciente(1,"Gilmar","Miranda","123",null,endereco);
		EnderecoResponse enderecoResponse = new EnderecoResponse("12 setembro",12,"cachoeira","Ba");
		PacienteResponse pacienteResponse = new PacienteResponse("Gilmar","Miranda","123",enderecoResponse);

		Mockito.when(pacienteService.atualizar(Mockito.any())).thenReturn(pacienteResponse);
		ObjectWriter writer = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE,false   ).writer();
		String payLoadJson = writer.writeValueAsString(paciente);

		mockMvc.perform(put("/pacientes").content(payLoadJson)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andExpect(content().contentType("text/plain;charset=UTF-8")).andReturn();
	}
	@Test
	void deveSalvarPacienteComFalha() throws Exception{
		Endereco endereco = new Endereco(1,"12 setembro",12,"cachoeira","Ba");
		Paciente paciente = new Paciente(1,"Gilmar","Miranda","123",null,endereco);
		EnderecoResponse enderecoResponse = new EnderecoResponse("12 setembro",12,"cachoeira","Ba");
		PacienteResponse pacienteResponse = new PacienteResponse("Gilmar","Miranda","123",enderecoResponse);

		Mockito.when(pacienteService.salvar(Mockito.any())).thenReturn(pacienteResponse);
		ObjectWriter writer = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE,true).writer();
		String payLoadJson = writer.writeValueAsString(paciente);

		mockMvc.perform(put("/dentistas").content(payLoadJson)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andExpect(content().contentType("text/plain;charset=UTF-8")).andReturn();

	}



	@Test
	void deveBuscarListaPacientesExistente() throws Exception {
		this.loadDataSet();
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/dentistas")
		    	.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		Assert.assertFalse(mvcResult.getResponse().getContentAsString().isEmpty());
	}

	@Test
	void deveBuscarPacientePorIdSucesso () throws Exception{
		this.loadDataSet();
		mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/{id}",1)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
	}

	@Test
	void deveBuscarListaPacientesInexistente() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/pacientes")
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andReturn();
	}

	@Test
	void deveBuscarPacientePorId_Erro () throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/pacientes/{id}",1)
			    .accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andReturn();
	}

	@Test
	void deverPacienteDeletado_Erro () throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.delete("/pacientes/{id}",1)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andReturn();
	}
	@Test
	void deverPacienteDeletado_Sucesso () throws Exception{
		this.loadDataSet();
		mockMvc.perform(MockMvcRequestBuilders.delete("/pacientes/{id}",1)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
	}

	public PacienteResponse toPacienteReponse(Paciente paciente){
		return PacienteResponse.builder()
				.nome(paciente.getNome())
				.sobrenome(paciente.getSobrenome())
				.rg(paciente.getRg())
				.endereco(toEnderecoReponse(paciente.getEndereco()))
				.build();
	}
	public EnderecoResponse toEnderecoReponse(Endereco endereco){
		return EnderecoResponse.builder()
				.rua(endereco.getRua())
				.numero(endereco.getNumero())
				.cidade(endereco.getCidade())
				.estado(endereco.getEstado())
				.build();
	}





}
