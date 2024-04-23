package italo.agenda.model.request.endereco;

import lombok.Data;

@Data
public class SaveEnderecoRequest {
        
    private String logradouro;

    private String cep;

    private String numero;

    private String cidade;

    private String estado;

    private boolean principal;

}
