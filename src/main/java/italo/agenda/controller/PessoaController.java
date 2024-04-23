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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import italo.agenda.exception.ErrorException;
import italo.agenda.model.request.pessoa.RegistroPessoaRequest;
import italo.agenda.model.request.pessoa.SavePessoaRequest;
import italo.agenda.model.response.pessoa.GetPessoaResponse;
import italo.agenda.service.PessoaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {
    
    @Autowired
    private PessoaService pessoaService;

    @PostMapping("/")
    public ResponseEntity<Object> registra( 
            @Valid @RequestBody RegistroPessoaRequest request ) throws ErrorException {
        pessoaService.registra(request);        
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{pessoaId}")
    public ResponseEntity<Object> altera(
            @PathVariable Long pessoaId, 
            @Valid @RequestBody SavePessoaRequest request ) throws ErrorException {
        
        pessoaService.altera( pessoaId, request );
        return ResponseEntity.ok().build();         
    }

    @GetMapping("/{pessoaId}")    
    public ResponseEntity<Object> get( @PathVariable Long pessoaId ) throws ErrorException {
        GetPessoaResponse resp = pessoaService.get( pessoaId );
        return ResponseEntity.ok( resp );        
    }

    @GetMapping("/lista")    
    public ResponseEntity<Object> lista() throws ErrorException {
        List<GetPessoaResponse> lista = pessoaService.lista();
        return ResponseEntity.ok( lista );        
    }

    @GetMapping("/filtra")
    public ResponseEntity<Object> filtraPorNome(
            @Valid @NotEmpty(message = "nome.obrigatorio") 
                @RequestParam("nome") String nomeLike ) throws ErrorException {
        List<GetPessoaResponse> lista = pessoaService.listaPorNome( nomeLike );
        return ResponseEntity.ok( lista ); 
    }

    @DeleteMapping("/{pessoaId}")
    public ResponseEntity<Object> deleta(
            @PathVariable Long pessoaId ) throws ErrorException {
        
        pessoaService.deleta( pessoaId );
        return ResponseEntity.ok().build();         
    }

}
