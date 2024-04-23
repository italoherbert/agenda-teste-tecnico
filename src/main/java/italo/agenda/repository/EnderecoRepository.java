package italo.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import italo.agenda.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    
}
