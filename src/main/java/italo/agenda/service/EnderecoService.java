package italo.agenda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import italo.agenda.components.builder.EnderecoBuilder;
import italo.agenda.exception.ErrorCode;
import italo.agenda.exception.ErrorException;
import italo.agenda.model.Endereco;
import italo.agenda.model.Pessoa;
import italo.agenda.model.request.endereco.SaveEnderecoRequest;
import italo.agenda.model.response.endereco.EnderecoResponse;
import italo.agenda.repository.EnderecoRepository;
import italo.agenda.repository.PessoaRepository;
import jakarta.transaction.Transactional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EnderecoBuilder enderecoBuilder;

    @Transactional
    public Long adicionaEndereco( Long pessoaId, SaveEnderecoRequest request ) throws ErrorException {
        Optional<Pessoa> pessoaOp = pessoaRepository.findById( pessoaId );
        if ( !pessoaOp.isPresent() )
            throw new ErrorException( ErrorCode.PESSOA_NAO_ENCONTRADA );

        Pessoa pessoa = pessoaOp.get();

        System.out.println( "PRIN= "+request.isPrincipal() );
        if ( request.isPrincipal() )      
            enderecoRepository.setPrincipaisParaFalse( pessoa.getId() );        
        
        Endereco ender = enderecoBuilder.novoBean( pessoa );        
        enderecoBuilder.loadBean( ender, request ); 
        enderecoRepository.save( ender );
        
        return ender.getId();
    }

    @Transactional
    public void alterEndereco( Long enderecoId, SaveEnderecoRequest request ) throws ErrorException {
        Optional<Endereco> enderOp = enderecoRepository.findById( enderecoId );
        if ( !enderOp.isPresent() )
            throw new ErrorException( ErrorCode.ENDERECO_NAO_ENCONTRADO );

        Endereco ender = enderOp.get();

        if ( request.isPrincipal() && !ender.isPrincipal() ) {      
            Pessoa pessoa = ender.getPessoa();
            enderecoRepository.setPrincipaisParaFalse( pessoa.getId() );
        }

        enderecoBuilder.loadBean( ender, request );  
        enderecoRepository.save( ender );
    }

    public EnderecoResponse get( Long enderecoId ) throws ErrorException {
        Optional<Endereco> enderecoOp = enderecoRepository.findById( enderecoId );
        if ( !enderecoOp.isPresent() ) 
            throw new ErrorException( ErrorCode.ENDERECO_NAO_ENCONTRADO );

        Endereco ender = enderecoOp.get();

        EnderecoResponse resp = enderecoBuilder.novoResp();
        enderecoBuilder.loadResp( resp, ender );
        return resp;
    }

    public List<EnderecoResponse> listaEnderecosPorPessoa( Long pessoaId ) throws ErrorException {
        List<Endereco> enderecos = enderecoRepository.listaPorPessoa( pessoaId );     
        return enderecoBuilder.buildResps( enderecos );
    }

    public void deleta( Long enderecoId ) throws ErrorException {
        Optional<Endereco> enderOp = enderecoRepository.findById( enderecoId );
        if ( !enderOp.isPresent() )
            throw new ErrorException( ErrorCode.ENDERECO_NAO_ENCONTRADO );

        enderecoRepository.deleteById( enderecoId ); 
    }

}
