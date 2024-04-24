package italo.agenda.apidoc.pessoa;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import italo.agenda.configuration.OpenAPIConfiguration;
import italo.agenda.model.response.ErroResponse;
import italo.agenda.model.response.pessoa.PessoaResponse;

@Operation(
	summary = "Responsável pela obtenção de dados de uma pessoa" )
@ApiResponses(value= {
	@ApiResponse( 		
		responseCode = "200",
		description = "Pessoa retornada com sucesso.",
			content = {@Content(					
				mediaType = "application/json", 
				schema = @Schema(implementation = PessoaResponse.class))}),	
	@ApiResponse(
		responseCode = "400",
		description = OpenAPIConfiguration.ERRO_400_MSG,
		content=@Content(					
			mediaType = "application/json", 
			schema = @Schema(implementation = ErroResponse.class)))
})
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface GetPessoaEndpoint {
    
}
