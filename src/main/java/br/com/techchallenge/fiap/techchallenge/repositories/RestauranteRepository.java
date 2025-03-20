package br.com.techchallenge.fiap.techchallenge.repositories;


import br.com.techchallenge.fiap.techchallenge.entities.Cardapio;
import br.com.techchallenge.fiap.techchallenge.entities.Endereco;
import br.com.techchallenge.fiap.techchallenge.entities.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long>, JpaSpecificationExecutor<Restaurante> {

    @Query("SELECT r.endereco FROM Restaurante r WHERE r.idRestaurante = :id")
    Endereco findEnderecoByRestauranteId(@Param("id") Long id);
}
