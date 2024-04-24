package italo.agenda.configuration;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;

@Configuration
@OpenAPIDefinition( 
	servers = {
       @Server(url = "http://localhost:8080", description = "Servidor padrão")
    }
) 
public class OpenAPIConfiguration {
    
    public final static String ERRO_400_MSG = "Erro de validação ou no servidor";
    
}
