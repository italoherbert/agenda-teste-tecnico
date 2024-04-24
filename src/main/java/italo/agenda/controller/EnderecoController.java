package italo.agenda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import italo.agenda.apidoc.endereco.AdicionaEnderecoEndpoint;
import italo.agenda.apidoc.endereco.AlteraEnderecoEndpoint;
import italo.agenda.apidoc.endereco.DeletaEnderecoEndpoint;
import italo.agenda.apidoc.endereco.GetEnderecoEndpoint;
import italo.agenda.apidoc.endereco.GetEnderecoPrincipalEndpoint;
import italo.agenda.apidoc.endereco.ListaEnderecosPorPessoaEndpoint;
import italo.agenda.exception.ErrorException;
import italo.agenda.model.request.endereco.SaveEnderecoRequest;
import italo.agenda.model.response.endereco.EnderecoResponse;
import italo.agenda.service.EnderecoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {
    
    @Autowired
    private EnderecoService enderecoService;

    @AdicionaEnderecoEndpoint
    @PostMapping("/pessoa/{pessoaId}") 
    public ResponseEntity<Object> adiciona( 
            @PathVariable Long pessoaId, 
            @Valid @RequestBody SaveEnderecoRequest request ) throws ErrorException {
        enderecoService.adicionaEndereco( pessoaId, request );
        return ResponseEntity.ok().build();
    }

    @AlteraEnderecoEndpoint
    @PutMapping("/{enderId}")
    public ResponseEntity<Object> altera( 
            @PathVariable Long enderId, 
            @Valid @RequestBody SaveEnderecoRequest request ) throws ErrorException {
        enderecoService.alterEndereco( enderId, request );
        return ResponseEntity.ok().build();
    }

    @GetEnderecoPrincipalEndpoint
    @GetMapping("/pessoa/{pessoaId}/principal")
    public ResponseEntity<Object> getEnderPrincipal( 
            @PathVariable Long pessoaId ) throws ErrorException {
        EnderecoResponse resp = enderecoService.getEnderPrincipal( pessoaId );
        return ResponseEntity.ok( resp );
    }

    @GetEnderecoEndpoint
    @GetMapping("/{enderId}")
    public ResponseEntity<Object> get( 
            @PathVariable Long enderId ) throws ErrorException {
        EnderecoResponse resp = enderecoService.get( enderId );
        return ResponseEntity.ok( resp );
    }

    @ListaEnderecosPorPessoaEndpoint
    @GetMapping("/pessoa/{pessoaId}/porpessoa")
    public ResponseEntity<Object> listaPorPessoa( 
            @PathVariable Long pessoaId ) throws ErrorException {
        List<EnderecoResponse> lista = enderecoService.listaEnderecosPorPessoa( pessoaId );
        return ResponseEntity.ok( lista );
    }

    @DeletaEnderecoEndpoint
    @DeleteMapping("/{enderId}")
    public ResponseEntity<Object> deleta( @PathVariable Long enderId ) throws ErrorException {
        enderecoService.deleta( enderId );
        return ResponseEntity.ok().build();        
    }

}
