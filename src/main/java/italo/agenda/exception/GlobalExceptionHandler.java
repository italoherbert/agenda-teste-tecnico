package italo.agenda.exception;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import italo.agenda.model.response.ErroResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<Object> handleErrorException( ErrorException e ) {
        int status = 400;
        try {
            String message = messageSource.getMessage( e.getErrorChave(), e.getErrorParams(), Locale.getDefault() );
            return ResponseEntity.status( status ).body( new ErroResponse( message ) );
        } catch ( NoSuchMessageException e2 ) {
            return ResponseEntity.status( status ).body( new ErroResponse( e2.getMessage() ) );
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> trataMensagemErroException( MethodArgumentNotValidException e ) {						
		BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();

        if ( error == null ) {
            String msg = messageSource.getMessage( "erro.de.validacao", null, Locale.getDefault() );
		    return ResponseEntity.status( 400 ).body( new ErroResponse( msg ) );
        }

		String mensagem = error.getDefaultMessage();
		return ResponseEntity.status( 400 ).body( new ErroResponse( mensagem ) );
	}

}
