package italo.agenda.service;

import static org.junit.Assert.assertEquals;
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
    public void testGetEndereco() {
        
    }

    @Test
    public void testEndereco() {
        Long pessoaId = 2L;
        
        try {            
            List<EnderecoResponse> lista = enderecoService.listaEnderecosPorPessoa( pessoaId );
            int quant = lista.size();

            SaveEnderecoRequest request = new SaveEnderecoRequest();
            request.setPrincipal( true );

            enderecoService.adicionaEndereco( pessoaId , request );

            lista = enderecoService.listaEnderecosPorPessoa( pessoaId );
            assertEquals( lista.size(), quant+1 );

            enderecoService.deleta( pessoaId );

            lista = enderecoService.listaEnderecosPorPessoa( pessoaId );
            assertEquals( lista.size(), quant );
        } catch ( ErrorException e ) {
            e.printStackTrace();
            fail( e.getErrorChave() );
        }
    }

}
