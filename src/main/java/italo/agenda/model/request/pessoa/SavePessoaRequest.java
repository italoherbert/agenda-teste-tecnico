package italo.agenda.model.request.pessoa;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SavePessoaRequest {
    
    @NotEmpty(message = "{nome.obrigatorio}")
    private String nome;

    @NotEmpty(message = "{data.de.nascimento.obrigatoria}")    
    private Date dataNascimento;

}
