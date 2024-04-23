package italo.agenda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import italo.agenda.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
 
    @Query("select e from Endereco e where e.pessoa.id=?1")
    public List<Endereco> listaPorPessoa( Long pessoaId );

    @Modifying
    @Query("update Endereco e set e.principal=false where e.pessoa.id=?1")
    public void setPrincipaisParaFalse( Long pessoaId );

}
