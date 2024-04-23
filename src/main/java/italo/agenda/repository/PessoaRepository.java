package italo.agenda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import italo.agenda.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query("select count(*)>0 from Pessoa where lower(nome)=lower(?1)") 
    public boolean existePorNome( String nome );

    @Query("select p from Pessoa p where lower(p.nome) like lower(?1)")
    public List<Pessoa> filtraPorNome( String nomeLike );

}
