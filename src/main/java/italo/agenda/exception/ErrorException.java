package italo.agenda.exception;

public class ErrorException extends Exception {
    
    private String errorChave;
    private String[] errorParams;

    public ErrorException( String chave, String... params ) {
        this.errorChave = chave;
        this.errorParams = params;
    }

    public String getErrorChave() {
        return errorChave;
    }

    public void setErrorChave(String errorChave) {
        this.errorChave = errorChave;
    }

    public String[] getErrorParams() {
        return errorParams;
    }

    public void setErrorParams(String[] errorParams) {
        this.errorParams = errorParams;
    }

}
