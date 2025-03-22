package br.com.techchallenge.fiap.techchallenge.repositories;


import br.com.techchallenge.fiap.techchallenge.entities.Endereco;
import br.com.techchallenge.fiap.techchallenge.entities.Restaurante;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long>, JpaSpecificationExecutor<Restaurante> {

    @Query("SELECT r.endereco FROM Restaurante r WHERE r.idRestaurante = :id")
    Endereco findEnderecoByRestauranteId(@Param("id") Long id);

    Page<Restaurante> findAll(Pageable pageable);

    @Modifying
    @Transactional
    @Query("UPDATE Restaurante r SET r.endereco = null WHERE r.endereco.id = :id")
    void updateEnderecoToNull(@Param("id") Long id);
}
