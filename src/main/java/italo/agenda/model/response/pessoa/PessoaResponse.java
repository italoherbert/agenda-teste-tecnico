package italo.agenda.model.response.pessoa;

import java.util.Date;

import lombok.Data;

@Data
public class PessoaResponse {
    
    private Long id;

    private String nome;

    private Date dataNascimento;

}
