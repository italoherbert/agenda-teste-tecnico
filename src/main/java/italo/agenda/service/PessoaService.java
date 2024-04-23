package italo.agenda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import italo.agenda.components.builder.PessoaBuilder;
import italo.agenda.exception.ErrorCode;
import italo.agenda.exception.ErrorException;
import italo.agenda.model.Pessoa;
import italo.agenda.model.request.pessoa.RegistroPessoaRequest;
import italo.agenda.model.request.pessoa.SavePessoaRequest;
import italo.agenda.model.response.pessoa.GetPessoaResponse;
import italo.agenda.repository.PessoaRepository;

@Service
public class PessoaService {
    
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private PessoaBuilder pessoaBuilder;

    public void registra( RegistroPessoaRequest request ) throws ErrorException {
        String nome = request.getNome();

        boolean existe = pessoaRepository.existePorNome( nome );
        if ( existe )
            throw new ErrorException( ErrorCode.PESSOA_JA_EXISTE );

        Pessoa pessoa = pessoaBuilder.novoBean();
        pessoaBuilder.loadBean( pessoa, request );
        pessoaRepository.save( pessoa ); 
    }

    public void altera( Long pessoaId, SavePessoaRequest request ) throws ErrorException {
        String nome = request.getNome();

        Optional<Pessoa> pessoaOp = pessoaRepository.findById( pessoaId );
        if ( !pessoaOp.isPresent() )
            throw new ErrorException( ErrorCode.PESSOA_NAO_ENCONTRADA );

        Pessoa pessoa = pessoaOp.get();    
        if ( !nome.equalsIgnoreCase( pessoa.getNome() ) ) {
            boolean existe = pessoaRepository.existePorNome( nome );
            if ( existe )
                throw new ErrorException( ErrorCode.PESSOA_JA_EXISTE );
        }

        pessoaBuilder.loadBean( pessoa, request );
        pessoaRepository.save( pessoa );
    }

    @Transactional(readOnly = true)
    public GetPessoaResponse get( Long pessoaId ) throws ErrorException {
        Optional<Pessoa> pessoaOp = pessoaRepository.findById( pessoaId );
        if ( !pessoaOp.isPresent() )
            throw new ErrorException( ErrorCode.PESSOA_NAO_ENCONTRADA );

        Pessoa pessoa = pessoaOp.get();

        GetPessoaResponse resp = pessoaBuilder.novoGetResp();
        pessoaBuilder.loadResp( resp, pessoa );
        return resp;
    }

    @Transactional(readOnly = true)
    public List<GetPessoaResponse> lista() throws ErrorException {
        List<Pessoa> pessoas = pessoaRepository.findAll();        
        return pessoaBuilder.loadResps( pessoas ); 
    }

    @Transactional(readOnly = true)
    public List<GetPessoaResponse> listaPorNome( String nomeLike ) throws ErrorException {
        String nlike = "%"+nomeLike+"%";

        List<Pessoa> pessoas = pessoaRepository.filtraPorNome( nlike );        
        return pessoaBuilder.loadResps( pessoas );
    }

    public void deleta( Long pessoaId ) throws ErrorException {
        Optional<Pessoa> pessoaOp = pessoaRepository.findById( pessoaId );
        if ( !pessoaOp.isPresent() )
            throw new ErrorException( ErrorCode.PESSOA_NAO_ENCONTRADA );

        pessoaRepository.deleteById( pessoaId ); 
    }

}
