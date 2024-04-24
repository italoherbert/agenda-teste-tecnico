package italo.agenda.model.request.pessoa;

import java.util.Date;

import italo.agenda.model.request.endereco.SaveEnderecoRequest;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegistroPessoaRequest {
    
    @NotEmpty(message = "{nome.obrigatorio}")
    private String nome;

    @NotNull(message = "{data.de.nascimento.obrigatoria}")    
    private Date dataNascimento;

    @NotNull(message = "{endereco.obrigatorio}")
    private SaveEnderecoRequest endereco;

}
