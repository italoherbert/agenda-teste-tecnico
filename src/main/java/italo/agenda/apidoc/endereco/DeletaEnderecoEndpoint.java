package italo.agenda.apidoc.endereco;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import italo.agenda.configuration.OpenAPIConfiguration;
import italo.agenda.model.response.ErroResponse;

@Operation(
	summary = "Responsável por deletar um endereço no sistema pelo ID." )
@ApiResponses(value= {
	@ApiResponse( 		
		responseCode = "200",
		description = "Endereço deletada com êxito.",
		content = @Content),	
	@ApiResponse(
		responseCode = "400",
		description = OpenAPIConfiguration.ERRO_400_MSG,
		content=@Content(					
			mediaType = "application/json", 
			schema = @Schema(implementation = ErroResponse.class)))
})
public @interface DeletaEnderecoEndpoint {
    
}
