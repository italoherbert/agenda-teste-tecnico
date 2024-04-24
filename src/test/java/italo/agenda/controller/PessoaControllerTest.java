package italo.agenda.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

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
import italo.agenda.model.request.pessoa.RegistroPessoaRequest;
import italo.agenda.model.request.pessoa.SavePessoaRequest;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PessoaControllerTest {
       
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
    public void testRegistro() {
        SaveEnderecoRequest enderReq = new SaveEnderecoRequest();
        enderReq.setPrincipal( true );

        RegistroPessoaRequest request = new RegistroPessoaRequest();
        request.setNome( "aaaooo" );
        request.setDataNascimento( new Date() );
        request.setEndereco( enderReq );

        try {
            mockMvc.perform( 
                post("/api/pessoas/")
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
    public void testAlter() { 
        Long pessoaId = 2L;
        
        SavePessoaRequest request = new SavePessoaRequest();
        request.setNome( "aaaooo" );
        request.setDataNascimento( new Date() );

        try {
            mockMvc.perform( 
                put( "/api/pessoas/"+pessoaId )
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
    public void testGet() { 
        Long pessoaId = 2L;
               
        try {
            mockMvc.perform( 
                get( "/api/pessoas/"+pessoaId ) )
                .andDo( print() ) 
                    .andExpect( status().is( 200 ) );
        } catch ( Exception e ) {
            e.printStackTrace();
            fail( e.getMessage() );
        }
    }

    @Test
    @DBSession
    public void testLista() {                
        try {
            mockMvc.perform( 
                get( "/api/pessoas/lista" ) )
                .andDo( print() ) 
                    .andExpect( status().is( 200 ) );
        } catch ( Exception e ) {
            e.printStackTrace();
            fail( e.getMessage() );
        }
    }

    @Test
    @DBSession
    public void testFiltraPorNome() {                
        try {
            mockMvc.perform( 
                get( "/api/pessoas/filtra" )
                    .param( "nome", "a" ) ) 
                .andDo( print() ) 
                    .andExpect( status().is( 200 ) );
        } catch ( Exception e ) {
            e.printStackTrace();
            fail( e.getMessage() );
        }
    }

    @Test
    @DBSession
    public void testDeleta() {                
        Long pessoaId = 3L;
        try {
            mockMvc.perform( 
                delete( "/api/pessoas/"+pessoaId ) )
                .andDo( print() ) 
                    .andExpect( status().is( 200 ) );
        } catch ( Exception e ) {
            e.printStackTrace();
            fail( e.getMessage() );
        }
    }
    
}
