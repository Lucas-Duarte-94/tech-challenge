package br.com.techchallenge.fiap.techchallenge.repositories;

import br.com.techchallenge.fiap.techchallenge.entities.Cardapio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardapioRepository extends JpaRepository<Cardapio, Long>, JpaSpecificationExecutor<Cardapio> {

//    @Query("SELECT c FROM Cardapio c WHERE c.restaurante.idRestaurante = :id")
//    Optional<Cardapio> findByRestaurante_Id(@Param("id") Long id);
}
