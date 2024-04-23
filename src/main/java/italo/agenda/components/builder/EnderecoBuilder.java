package italo.agenda.components.builder;

import org.springframework.stereotype.Component;

import italo.agenda.model.Endereco;
import italo.agenda.model.Pessoa;
import italo.agenda.model.request.endereco.SaveEnderecoRequest;
import italo.agenda.model.response.endereco.EnderecoResponse;

@Component
public class EnderecoBuilder {
    
    public void loadBean( Endereco endereco, SaveEnderecoRequest request ) {
        endereco.setLogradouro( request.getLogradouro() );
        endereco.setCep( request.getCep() );
        endereco.setNumero( request.getNumero() ); 
        endereco.setCidade( request.getCidade() );
        endereco.setEstado( request.getEstado() ); 
    }

    public void loadResp( EnderecoResponse resp, Endereco ender ) {
        resp.setId( ender.getId() );
        resp.setLogradouro( ender.getLogradouro() );
        resp.setCep( ender.getCep() );
        resp.setNumero( ender.getNumero() );
        resp.setCidade( ender.getCidade() );
        resp.setEstado( ender.getEstado() ); 
    }

    public Endereco novoBean( Pessoa pessoa ) {
        Endereco ender = new Endereco();
        ender.setPessoa( pessoa ); 
        return ender;
    }

    public EnderecoResponse novoResp() {
        return new EnderecoResponse();
    }

}
