package italo.agenda.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import italo.agenda.ext.DBSession;
import italo.agenda.model.request.endereco.SaveEnderecoRequest;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EnderecoControllerTest {
    
    @Autowired
	private WebApplicationContext context;
	
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders
				.webAppContextSetup( context )
				.build();								
	}

    @Test
    @DBSession
    public void testAdicionaEndereco() {
        Long pessoaId = 2L;

        SaveEnderecoRequest request = new SaveEnderecoRequest();
        request.setCidade( "abc" );
        request.setEstado( "ABC" );
        request.setPrincipal( true );

        try {
            mockMvc.perform( 
                post("/api/enderecos/"+pessoaId )
                    .contentType( MediaType.APPLICATION_JSON )
                    .content( objectMapper.writeValueAsBytes( request ) ) )
                .andDo( print() ) 
                    .andExpect( status().is( 200 ) );
        } catch ( Exception e ) {
            e.printStackTrace();
            fail( e.getMessage() );
        }
    }

    @Test
    @DBSession
    public void testAlteraEndereco() {
        Long enderId = 2L;

        SaveEnderecoRequest request = new SaveEnderecoRequest();
        request.setCidade( "abc" );
        request.setEstado( "ABC" );
        request.setPrincipal( true );

        try {
            mockMvc.perform( 
                put("/api/enderecos/"+enderId )
                    .contentType( MediaType.APPLICATION_JSON )
                    .content( objectMapper.writeValueAsBytes( request ) ) )
                .andDo( print() ) 
                    .andExpect( status().is( 200 ) );
        } catch ( Exception e ) {
            e.printStackTrace();
            fail( e.getMessage() );
        }
    }

    @Test
    @DBSession
    public void testGetEnderPrincipal() {
        Long pessoaId = 2L;

        try {
            mockMvc.perform( 
                get("/api/enderecos/principal/"+pessoaId ) )
                .andDo( print() ) 
                    .andExpect( status().is( 200 ) );
        } catch ( Exception e ) {
            e.printStackTrace();
            fail( e.getMessage() );
        }
    }

    @Test
    @DBSession
    public void testGet() {
        Long enderId = 2L;

        try {
            mockMvc.perform( 
                get("/api/enderecos/"+enderId ) )
                .andDo( print() ) 
                    .andExpect( status().is( 200 ) );
        } catch ( Exception e ) {
            e.printStackTrace();
            fail( e.getMessage() );
        }
    }

    @Test
    @DBSession
    public void testListaPorPessoa() {
        Long pessoaId = 2L;

        try {
            mockMvc.perform( 
                get("/api/enderecos/porpessoa/"+pessoaId ) )
                .andDo( print() ) 
                    .andExpect( status().is( 200 ) );
        } catch ( Exception e ) {
            e.printStackTrace();
            fail( e.getMessage() );
        }
    }

    @Test
    @DBSession
    public void testDelete() {
        Long enderId = 2L;

        try {
            mockMvc.perform( 
                delete("/api/enderecos/"+enderId ) )
                .andDo( print() ) 
                    .andExpect( status().is( 200 ) );
        } catch ( Exception e ) {
            e.printStackTrace();
            fail( e.getMessage() );
        }
    }


}
