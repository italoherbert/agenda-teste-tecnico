package italo.agenda.components.builder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import italo.agenda.model.Endereco;
import italo.agenda.model.Pessoa;
import italo.agenda.model.request.pessoa.RegistroPessoaRequest;
import italo.agenda.model.request.pessoa.SavePessoaRequest;
import italo.agenda.model.response.endereco.EnderecoResponse;
import italo.agenda.model.response.pessoa.GetPessoaResponse;
import italo.agenda.model.response.pessoa.PessoaResponse;

@Component
public class PessoaBuilder {
    
    @Autowired
    private EnderecoBuilder enderecoBuilder;

    public void loadBean( Pessoa pessoa, RegistroPessoaRequest request ) {
        pessoa.setNome( request.getNome() );
        pessoa.setDataNascimento( request.getDataNascimento() );
        
        Endereco ender = enderecoBuilder.novoBean( pessoa );
        enderecoBuilder.loadBean( ender, request.getEndereco() );
        pessoa.getEnderecos().add( ender ); 
    }

    public void loadBean( Pessoa pessoa, SavePessoaRequest request ) {
        pessoa.setNome( request.getNome() );
        pessoa.setDataNascimento( request.getDataNascimento() );        
    }

    public void loadResp( PessoaResponse resp, Pessoa pessoa ) {
        resp.setId( pessoa.getId() );
        resp.setNome( pessoa.getNome() );
        resp.setDataNascimento( pessoa.getDataNascimento() );
    }

    public void loadResp( GetPessoaResponse resp, Pessoa pessoa ) {
        PessoaResponse presp = this.novoResp();
        loadResp( presp, pessoa );
        resp.setPessoa( presp );
                  
        List<Endereco> enderecos = pessoa.getEnderecos();
        
        for( Endereco ender : enderecos ) {            
            EnderecoResponse eresp = enderecoBuilder.novoResp();
            enderecoBuilder.loadResp( eresp, ender );

            resp.getEnderecos().add( eresp );
        }
    }

    public List<GetPessoaResponse> loadResps( List<Pessoa> pessoas ) {
        List<GetPessoaResponse> respList = new ArrayList<>();

        for( Pessoa p : pessoas ) {
            GetPessoaResponse resp = this.novoGetResp();
            this.loadResp( resp, p );
            respList.add( resp );
        }
        return respList;
    }

    public Pessoa novoBean() {
        Pessoa pessoa = new Pessoa();        
        pessoa.setEnderecos( new ArrayList<>() );
        return pessoa;
    }

    public PessoaResponse novoResp() {
        return new PessoaResponse();        
    }

    public GetPessoaResponse novoGetResp() {
        GetPessoaResponse resp = new GetPessoaResponse();
        resp.setEnderecos( new ArrayList<>() );
        return resp;
    }

}
