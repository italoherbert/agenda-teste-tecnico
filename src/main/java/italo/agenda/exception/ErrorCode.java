package italo.agenda.exception;

public interface ErrorCode {
    
    public final static String ERRO_DE_VALIDACAO = "erro.de.validacao";

    public final static String NOME_OBRIGATORIO = "nome.obrigatorio";
    public final static String DATA_NASC_OBRIGATORIA = "data.de.nascimento.obrigatoria";
    public final static String ENDERECO_OBRIGATORIO = "endereco.obrigatorio";
    
    public final static String PESSOA_JA_EXISTE = "pessoa.ja.existe";
    public final static String PESSOA_NAO_ENCONTRADA = "pessoa.nao.encontrada";
    public final static String PESSOA_SEM_ENDERECO_PRINCIPAL = "pessoa.sem.endereco.principal";

    public final static String ENDERECO_NAO_ENCONTRADO = "endereco.nao.encontrado";

}
