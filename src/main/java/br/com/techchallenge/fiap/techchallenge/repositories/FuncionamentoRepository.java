package br.com.techchallenge.fiap.techchallenge.repositories;

import br.com.techchallenge.fiap.techchallenge.entities.Funcionamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionamentoRepository extends JpaRepository<Funcionamento, Long>, JpaSpecificationExecutor<Funcionamento> {

    List<Funcionamento> findByRestaurante_IdRestaurante(Long restauranteIdRestaurante);
}
