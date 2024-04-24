package italo.agenda.model.request.pessoa;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SavePessoaRequest {
    
    @NotEmpty(message = "{nome.obrigatorio}")
    private String nome;

    @NotNull(message = "{data.de.nascimento.obrigatoria}")    
    private Date dataNascimento;

}
