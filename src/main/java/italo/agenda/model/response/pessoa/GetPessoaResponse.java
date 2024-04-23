package italo.agenda.model.response.pessoa;

import java.util.List;

import italo.agenda.model.response.endereco.EnderecoResponse;
import lombok.Data;

@Data
public class GetPessoaResponse {
    
    private PessoaResponse pessoa;

    private List<EnderecoResponse> enderecos;

}
