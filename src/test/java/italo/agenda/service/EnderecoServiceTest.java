package italo.agenda.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import italo.agenda.exception.ErrorException;
import italo.agenda.ext.DBSession;
import italo.agenda.model.request.endereco.SaveEnderecoRequest;
import italo.agenda.model.response.endereco.EnderecoResponse;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EnderecoServiceTest {
    
    @Autowired
    private EnderecoService enderecoService;

    @Test
    @DBSession
    public void testAdicionaEnderecoPrincipal() {
        Long pessoaId = 3L;

        SaveEnderecoRequest request = new SaveEnderecoRequest();
        request.setCidade( "ABC" );
        request.setEstado( "AB" );
        request.setPrincipal( true );
        
        try {
            Long enderId = enderecoService.adicionaEndereco( pessoaId, request );

            int count = 0;

            List<EnderecoResponse> enderecos = enderecoService.listaEnderecosPorPessoa( pessoaId );
            for( EnderecoResponse ender : enderecos ) {                
                if ( ender.getId() == enderId ) {
                    assertTrue( ender.isPrincipal() );
                    count++;
                } else {
                    assertFalse( ender.isPrincipal() );
                }
            }

            assertEquals( count, 1 );

            EnderecoResponse enderResp = enderecoService.getEnderPrincipal( pessoaId );
            assertNotNull( enderResp );
            assertEquals( enderResp.getId(), enderId );
            assertTrue( enderResp.isPrincipal() );
        } catch ( ErrorException e ) {
            e.printStackTrace();
            fail( e.getErrorChave() );
        }
    }

    @Test
    @DBSession
    public void testAdicionaEnderecoSecundario() {
        Long pessoaId = 3L;

        SaveEnderecoRequest request = new SaveEnderecoRequest();
        request.setCidade( "ABC" );
        request.setEstado( "AB" );
        request.setPrincipal( false );
        
        try {
            Long enderId = enderecoService.adicionaEndereco( pessoaId, request );

            int countEnderPrincipais = 0;

            List<EnderecoResponse> enderecos = enderecoService.listaEnderecosPorPessoa( pessoaId );
            for( EnderecoResponse ender : enderecos ) {                
                if ( ender.getId() == enderId )
                    assertFalse( ender.isPrincipal() );                                

                if ( ender.isPrincipal() )
                    countEnderPrincipais++;
            }

            assertEquals( countEnderPrincipais, 1 );
        } catch ( ErrorException e ) {
            e.printStackTrace();
            fail( e.getErrorChave() );
        }
    }

    @Test
    @DBSession
    public void testAlterEnder() {
        Long enderId = 3L;

        SaveEnderecoRequest request = new SaveEnderecoRequest();
        request.setCidade( "ABC" );
        request.setEstado( "AB" );
        request.setPrincipal( true );
        
        try {
            enderecoService.alterEndereco( enderId, request );

            EnderecoResponse enderResp = enderecoService.get( enderId );
            assertNotNull( enderResp );

            Long pessoaId = enderResp.getPessoaId();

            int countEnderPrincipais = 0;

            List<EnderecoResponse> enderecos = enderecoService.listaEnderecosPorPessoa( pessoaId );
            for( EnderecoResponse ender : enderecos ) {                
                if ( ender.getId() == enderId )
                    assertTrue( ender.isPrincipal() );                                

                if ( ender.isPrincipal() )
                    countEnderPrincipais++;
            }

            assertEquals( countEnderPrincipais, 1 );  

            try {
                request.setPrincipal( false );
                enderecoService.alterEndereco( enderId, request );              
                fail( "Deveria lançar exceção. Pessoa sem endereço principal" );
            } catch ( ErrorException e ) {

            }
        } catch ( ErrorException e ) {
            e.printStackTrace();
            fail( e.getErrorChave() );
        }
    }

    @Test
    @DBSession
    public void testGetEndereco() {
        Long enderId = 3L;

        try {
            EnderecoResponse ender = enderecoService.get( enderId );
            assertEquals( ender.getId(), enderId );
        } catch ( ErrorException e ) {
            e.printStackTrace();
            fail( e.getErrorChave() );
        }
    }

    @Test
    @DBSession
    public void testGetEnderPrincipal() {
        Long pessoaId = 3L;

        SaveEnderecoRequest request = new SaveEnderecoRequest();
        request.setCidade( "ABC" );
        request.setEstado( "AB" );
        request.setPrincipal( true );
        
        try {
            Long enderId = enderecoService.adicionaEndereco( pessoaId, request );            

            EnderecoResponse enderResp = enderecoService.getEnderPrincipal( pessoaId );
            assertNotNull( enderResp );
            assertEquals( enderResp.getId(), enderId );
            assertTrue( enderResp.isPrincipal() );
        } catch ( ErrorException e ) {
            e.printStackTrace();
            fail( e.getErrorChave() );
        }
    }

    @Test
    @DBSession
    public void testGeral() {
        Long pessoaId = 2L;
        
        try {            
            List<EnderecoResponse> lista = enderecoService.listaEnderecosPorPessoa( pessoaId );
            int quant = lista.size();

            SaveEnderecoRequest request = new SaveEnderecoRequest();
            request.setPrincipal( true );

            Long enderId = enderecoService.adicionaEndereco( pessoaId , request );

            lista = enderecoService.listaEnderecosPorPessoa( pessoaId );
            assertEquals( lista.size(), quant+1 );

            try {
                enderecoService.deleta( enderId );
                fail( "Deveria lançar exceção porque esse endereço está como principal." );
            } catch ( ErrorException e ) {

            }            
        } catch ( ErrorException e ) {
            e.printStackTrace();
            fail( e.getErrorChave() );
        }
    }

    @Test
    @DBSession
    public void testDeleta() {
        Long enderId = 5L;

        try {
            EnderecoResponse ender = enderecoService.get( enderId );
            assertNotNull( ender );

            Long pessoaId = ender.getPessoaId();

            List<EnderecoResponse> lista = enderecoService.listaEnderecosPorPessoa( pessoaId );
            assertFalse( lista.isEmpty() );
            
            int quant = lista.size();

            enderecoService.deleta( enderId );

            lista = enderecoService.listaEnderecosPorPessoa( pessoaId );
            assertEquals( lista.size(), quant-1 );
        } catch ( ErrorException e ) {
            e.printStackTrace();
            fail( e.getErrorChave() );
        }
    }

}
