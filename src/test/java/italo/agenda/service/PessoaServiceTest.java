package italo.agenda.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import italo.agenda.exception.ErrorException;
import italo.agenda.ext.DBSession;
import italo.agenda.model.request.endereco.SaveEnderecoRequest;
import italo.agenda.model.request.pessoa.RegistroPessoaRequest;
import italo.agenda.model.request.pessoa.SavePessoaRequest;
import italo.agenda.model.response.pessoa.GetPessoaResponse;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PessoaServiceTest {
    
    @Autowired
    private PessoaService pessoaService;

    @Test
    @DBSession
    public void testRegistraPessoa() {
        SaveEnderecoRequest enderReq = new SaveEnderecoRequest();
        enderReq.setPrincipal( true );

        RegistroPessoaRequest request = new RegistroPessoaRequest();
        request.setNome( "zzz" );
        request.setDataNascimento( new Date() );
        request.setEndereco( enderReq );

        try {
            pessoaService.registra( request );            
        } catch (ErrorException e) {
            e.printStackTrace();
            fail( e.getErrorChave() ); 
        }

        try {
            pessoaService.registra( request );
            fail( "Deveria lançar exceção sobre nome já existir registrado." );
        } catch (ErrorException e) {

        }

        try {
            request.setNome( "zzzzzz" );
            enderReq.setPrincipal( false );
            pessoaService.registra( request );
            fail( "Deveria lançar exceção sobre marcar o único endereço como não principal." );
        } catch ( ErrorException e ) {
            
        }
        
    }

    @Test
    @DBSession
    public void testPessoa() {
        try {
            Long id = 2L;
            String novoNome = "aaa";

            List<GetPessoaResponse> pessoas = pessoaService.lista();            

            int quant = pessoas.size();

            GetPessoaResponse pessoa = pessoaService.get( id );
            assertNotNull( pessoa );
            assertNotNull( pessoa.getEnderecos() );
            assertNotNull( pessoa.getPessoa() );
            assertEquals( id, pessoa.getPessoa().getId() );

            SavePessoaRequest request = new SavePessoaRequest();
            request.setNome( novoNome );
            request.setDataNascimento( new Date() );

            pessoaService.altera( id, request );

            pessoa = pessoaService.get( id );
            assertNotNull( pessoa );
            assertNotNull( pessoa.getPessoa() );
            assertEquals( pessoa.getPessoa().getNome(), novoNome );
                        
            pessoaService.deleta( id );
            
            pessoas = pessoaService.lista();
            assertEquals( quant-1, pessoas.size() );

            pessoa = null;
            try {
                pessoa = pessoaService.get( id );
                fail( "Deveria não encontrar a pessoa porque ela já foi removida." );
            } catch ( ErrorException e ) {
                assertNull( pessoa );
            }            

            try {
                pessoaService.deleta( id );
                fail( "Não deveria ser possível remover essa pessoa porque ela já foi removida." );
            } catch ( ErrorException e ) {

            }
        } catch ( ErrorException e ) {
            e.printStackTrace();
            fail( e.getErrorChave() );
        }
    }

    @Test
    @DBSession
    public void testFiltroDePessoas() {
        String nomeLike = "a";

        try {
            pessoaService.listaPorNome( nomeLike );                        
        } catch ( ErrorException e ) {
            e.printStackTrace();
            fail( e.getErrorChave() );
        }            
    }

    @Test
    @DBSession
    public void testDeletaPessoas() {
        try {
            List<GetPessoaResponse> pessoas = pessoaService.lista();
            for( GetPessoaResponse p : pessoas )
                pessoaService.deleta( p.getPessoa().getId() );
                
            pessoas = pessoaService.lista();
            assertTrue( pessoas.isEmpty() );
        } catch ( ErrorException e ) {
            e.printStackTrace();
            fail( e.getErrorChave() );
        }
    }

}
