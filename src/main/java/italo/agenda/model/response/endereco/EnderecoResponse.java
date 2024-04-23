package italo.agenda.model.response.endereco;

import lombok.Data;

@Data
public class EnderecoResponse {
    
    private Long id;

    private String logradouro;

    private String cep;

    private String numero;

    private String cidade;
    
    private String estado;

    private boolean principal;

}
